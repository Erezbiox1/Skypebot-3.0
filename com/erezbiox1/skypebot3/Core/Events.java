package com.erezbiox1.skypebot3.Core;

import com.erezbiox1.skypebot3.Events.onMessage;
import com.samczsun.skype4j.events.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Erezbiox1 on 11/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class Events {

    static List<Listener> events = new ArrayList<>(
            Arrays.asList(
                    new onMessage()
            )
    );

    public static void registerEvents(){
        Main.skypes.forEach(skype -> {
            for(Listener listener: events){
                skype.getEventDispatcher().registerListener(listener);
            }
        });
    }


}
