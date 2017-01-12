package com.erezbiox1.skypebot3.Commands.Commands;

import com.erezbiox1.skypebot3.Commands.Command;
import com.erezbiox1.skypebot3.Core.Admins;
import com.erezbiox1.skypebot3.Core.Main;
import com.erezbiox1.skypebot3.Utils;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.participants.Participant;

import java.util.Objects;

/**
 * Created by Erezbiox1 on 12/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class loginCommand extends Command{

    //See if the given password match the main password, if it is, make the sender an admin.

    public loginCommand() {
        super("login");
    }

    @Override
    public void onCommand(Participant sender, Command command, Chat chat, String Label, String[] args) {
        if(Admins.isAdmin(sender)){
            Utils.sendMessage("You are already an admin.", chat);
            return;
        }

        if(args == null || args.length != 1){
            Utils.sendMessage("Invalid Arguments", chat);
            return;
        }

        if(Objects.equals(args[0], Main.botPassword)){
            Admins.add(sender);
            Utils.sendMessage("Logged on successfully.", chat);
        }else Utils.sendMessage("Invalid Login.", chat);
    }
}