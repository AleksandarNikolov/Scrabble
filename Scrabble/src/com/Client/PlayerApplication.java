package com.Client;


public class PlayerApplication {
    public static void main(String[] args) {
        PlayerSession player = new PlayerSession();
        player.openConnection("localhost", 43215);

        Thread gamePlay = new Thread(() -> player.run());
        gamePlay.start();
        try {
            gamePlay.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}