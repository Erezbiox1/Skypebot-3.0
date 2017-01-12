package com.erezbiox1.skypebot3.Commands.Commands;

import com.erezbiox1.skypebot3.Commands.Command;
import com.erezbiox1.skypebot3.Utils;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.participants.Participant;

/**
 * Created by Erezbiox1 on 12/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class helpCommand extends Command {


    public helpCommand() {
        super("help");
    }

    @Override
    public void onCommand(Participant sender, Command command, Chat chat, String Label, String[] args) {
        Utils.sendMessage("~~~~~~~~~\nBioBot V3.0\n~~~~~~~~~\n!help\n!Pizza", chat);
    }
}
