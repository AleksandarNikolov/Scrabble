package com.Server;

import com.Server.Controller.Server;
import com.Server.Controller.ServerRunnable;

public class ServerApplication {
    public static void main(String[] args) {
        Server server = new Server(43215);
        Thread lobby = new Thread(() -> server.run());

        lobby.start();
        try {
            lobby.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}