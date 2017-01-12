package com.erezbiox1.skypebot3.Commands.Commands;

import com.erezbiox1.skypebot3.Commands.Command;
import com.erezbiox1.skypebot3.Core.Admins;
import com.erezbiox1.skypebot3.Utils;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.participants.Participant;

/**
 * Created by Erezbiox1 on 12/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class stopCommand extends Command {

    //Check if the sender is an admin, if he is logout.

    public stopCommand() {
        super("stop");
    }

    @Override
    public void onCommand(Participant sender, Command command, Chat chat, String Label, String[] args) {
        if(Admins.isAdmin(sender))
            Utils.logout();
        else Utils.sendMessage("Insufficient permission.", chat);
    }
}
