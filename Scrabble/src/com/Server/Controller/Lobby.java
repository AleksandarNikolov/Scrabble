package com.Server.Controller;

import com.Client.Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lobby {
    ArrayList<Player> playerList = new ArrayList<>();
    Map<String, Player> playerIpMap = new HashMap<>();


    public void addPlayer(String ip, Player player) {
        playerList.add(player);
        playerIpMap.put(ip, player);
    }

    public void displayIpMap() {
        for (String s : playerIpMap.keySet()) {
            System.out.println(s);
        }
    }

}
