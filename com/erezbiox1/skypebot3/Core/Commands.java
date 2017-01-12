package com.erezbiox1.skypebot3.Core;

import com.erezbiox1.skypebot3.Commands.Command;
import com.erezbiox1.skypebot3.Commands.Commands.helpCommand;
import com.erezbiox1.skypebot3.Commands.Commands.loginCommand;
import com.erezbiox1.skypebot3.Commands.Commands.stopCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Erezbiox1 on 11/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class Commands {

    static List<Command> commands = new ArrayList<>(
            Arrays.asList(
                    new helpCommand(),
                    new stopCommand(),
                    new loginCommand()
            )
    );

    public static void registerEvents(){
        for(Command command: commands) {
            Main.commandManager.registerCommand(command);
        }
    }

}
