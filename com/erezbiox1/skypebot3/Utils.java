package com.erezbiox1.skypebot3;

import com.erezbiox1.skypebot3.Core.Main;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.formatting.Message;
import com.samczsun.skype4j.formatting.Text;
import com.samczsun.skype4j.participants.Participant;

import java.awt.*;
import java.util.Random;

/**
 * Created by Erezbiox1 on 11/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class Utils {

    private static final char[] randomLetters = { 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9'};

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static String randomPassword(int length){

        int index;
        String result = "";

        for(int i = 0; i != length; i++){
            index = randInt(0, randomLetters.length - 1);
            result += randomLetters[index];
        }

        return result;
    }

    public static void sendToAllChats(String message){
        Main.skypeChatMap.forEach((skype,chat) -> {
            try {
                chat.sendMessage(message);
            } catch (ConnectionException e) {
                connectionError();
            }
        });
    }

    public static void sendToAllChats(String message, Color color){
        Main.skypeChatMap.forEach((skype,chat) -> {
            try {
                chat.sendMessage(Message.create().with(Text.rich(message).withColor(color)));
            } catch (ConnectionException e) {
                connectionError();
            }
        });
    }

    public static void sendMessage(String message, Chat chat){
        try {
            chat.sendMessage(message);
        } catch (ConnectionException e) {
            connectionError();
        }
    }

    public static void sendAllBut(Chat fromChat, Participant sender, String message){
        Main.skypeChatMap.forEach((skype, chat) -> {
            if(fromChat.equals(chat)) return;
            sendMessage(sender.getDisplayName() + ": " + message , chat);
        });
    }

    public static void logout(){
        System.out.println("Shutting down...");
        Main.skypeChatMap.forEach((skype, chat) -> {
            try {
                skype.logout();
            } catch (ConnectionException ignored) {} // Doesn't matter.
        });
        System.exit(0);
    }

    public static void connectionError(){
        System.err.println("No connection to the server.");
        //System.exit(1);
    }

    public static void connectionError(String chat){
        System.err.println("No connection to the server. ( " + chat + " ).");
        //System.exit(1);
    }

}
