package com.erezbiox1.skypebot3.Core;

import com.erezbiox1.skypebot3.Commands.CommandManager;
import com.erezbiox1.skypebot3.Utils;
import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.chat.Chat;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static List<Skype> skypes = new ArrayList<>();
    public static Map<Skype, String> skypeTempChatMap = new HashMap<>();
    public static Map<Skype, Chat> skypeChatMap = new HashMap<>();

    public static String botName;
    public static String botPassword;
    public static CommandManager commandManager;

    public static final String commandIndicator = "!";
    public static final String ignoreIndicator = "###";

    public static void main(String[] args) {

        System.out.println("Building...");
        Builder.build(args);

        System.out.println("Logging in...");
        Builder.login();

        System.out.println("Loading Chats...");
        Builder.loadChats();

        System.out.println("Registering Events...");
        Events.registerEvents();

        System.out.println("Generating password...");
        if(botPassword.equalsIgnoreCase("random"))
            botPassword = Utils.randomPassword(8);

        System.out.println("Done! Password: " + botPassword);

        System.out.println("Registering Commands...");
        commandManager = new CommandManager();
        Commands.registerEvents();

        System.out.println("Sending Message...");
        Utils.sendToAllChats("Hello world, Please enter !help to view commands.", Color.RED);

        System.out.println("Done!");

    }
}
