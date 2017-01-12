package com.erezbiox1.skypebot3.Commands;

import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.participants.Participant;

/**
 * Created by Erezbiox1 on 12/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public abstract class Command {

    private String name;

    public Command(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void onCommand(Participant sender, Command command, Chat chat, String Label, String[] args);

}
