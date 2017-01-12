package com.erezbiox1.skypebot3.Core;

import com.erezbiox1.skypebot3.Utils;
import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NotParticipatingException;

/**
 * Created by Erezbiox1 on 11/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class Builder {

    //todo - remake this class.
    // This is the very first version of this code, so yeah, It's messy... Most of the code here is just for testing.

    public static void build(String[] args){

        if(!(args.length > 2 )){
            System.err.println("Invalid arguments. Please enter bot name, admin password, and chat Id's.");
            System.exit(1);
        }

        Main.botName = args[0];
        Main.botPassword = args[1];

        for(String arg: args){
            if(!(arg == Main.botName || arg == Main.botPassword)) {
                Skype skype = new SkypeBuilder(Main.botName).withAllResources().withChat(arg).build();
                Main.skypeTempChatMap.put(skype, arg);
                Main.skypes.add(skype);
            }
        }

    }

    public static void login(){
        Main.skypes.forEach(skype -> {
            try {
                skype.login();
                skype.subscribe();
            } catch (InvalidCredentialsException | NotParticipatingException ignored) {} catch (ConnectionException e) {
                System.err.println("No connection to the server.");
                System.exit(1);
            }
        });
    }

    public static void loadChats(){
        Main.skypeTempChatMap.forEach((skype, chat) -> {
            Main.skypeChatMap.put(skype, getChat(chat, skype));
        });
        Main.skypeTempChatMap = null; // Just to save some memory, doesn't really matter...
    }

    private static Chat getChat(String chat, Skype skype){
        try {
            return skype.getOrLoadChat(chat);
        } catch (ChatNotFoundException | ConnectionException e) {
            Utils.connectionError(chat);
            return null; // for real? damn man
        }
    }



}
