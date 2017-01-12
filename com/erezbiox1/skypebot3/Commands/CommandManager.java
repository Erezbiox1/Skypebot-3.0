package com.erezbiox1.skypebot3.Commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erezbiox1 on 12/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class CommandManager {

    private Map<String, Command> commands;

    public CommandManager(){
        this.commands = new HashMap<String, Command>();
    }

    public void registerCommand(Command command){
        commands.put(command.getName(), command);
    }

    public Command getCommand(String name){
        return commands.get(name);
    }

}
