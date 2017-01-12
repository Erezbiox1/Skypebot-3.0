package com.erezbiox1.skypebot3.Core;

import com.samczsun.skype4j.participants.Participant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erezbiox1 on 11/01/2017.
 * (C) 2016 Erez Rotem All Rights Reserved.
 */
public class Admins {

    public static List<Participant> admins = new ArrayList<>();

    public static void add(Participant user){
        admins.add(user);
    }

    public static boolean isAdmin(Participant user){
        return admins.contains(user);
    }

}
