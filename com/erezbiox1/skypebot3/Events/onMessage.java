package com.erezbiox1.skypebot3.Events;

import com.erezbiox1.skypebot3.Commands.Command;
import com.erezbiox1.skypebot3.Commands.CommandManager;
import com.erezbiox1.skypebot3.Core.Main;
import com.erezbiox1.skypebot3.Utils;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.events.EventHandler;
import com.samczsun.skype4j.events.Listener;
import com.samczsun.skype4j.events.chat.message.MessageReceivedEvent;
import com.samczsun.skype4j.formatting.Message;
import com.samczsun.skype4j.participants.Participant;

/**
 * Created by Erezbiox1 on 11/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class onMessage implements Listener {

    //TODO, recode, improve.

    @EventHandler
    public void onMessage(MessageReceivedEvent event){

        //Initializing Vars
        String prefix = Main.commandIndicator;
        CommandManager commandManager = Main.commandManager;
        String ignorePrefix = Main.ignoreIndicator;
        String message = event.getMessage().getContent().asPlaintext();
        Participant sender = event.getMessage().getSender();

        //Testing if the message is a command.
        if(message.startsWith(prefix)){

            executeCommand(message, event.getChat(), message.contains(" "), sender);

        // The message is not a command, send to all.
        }else if(!message.startsWith(ignorePrefix)){

            Utils.sendAllBut(event.getChat(), sender, message);

        }else return; // Message started with the ignore mark, ignoring...
    }

    private void executeCommand(String commandName, Chat chat, boolean args, Participant sender){
        //Initializing vars
        String prefix = Main.commandIndicator;
        CommandManager commandManager = Main.commandManager;
        Command command;
        String[] split = null;

        //If the message have space in in, the command have args, and we need to treat the command differently.
        if(args){

            //Making a array of all of the arguments, the first of them is the command, find the command with that command name.
            split = commandName.replace(prefix, "").split(" ");
            command = commandManager.getCommand(split[0].toLowerCase());

        }else{

            //Get the command with the that label without the indicator mark.
            command = commandManager.getCommand(commandName.replace(prefix, "").toLowerCase());

        }

        //The command is null, there is no such command.
        if (command == null) {

            Utils.sendMessage("Unknown Command. Type !help for a list of commands.", chat);
            return;

        }

        //Check if the command has arguments ( yes, again... ), then execute the command with or without those arguments. Also remove the command from the arguments.
        if(args){

            command.onCommand(sender, command, chat, command.getName(), commandName.replace(prefix + command.getName() + " ", "").split(" "));

        }else{

            command.onCommand(sender, command, chat, command.getName(), new String[0]);

        }
    }

}
