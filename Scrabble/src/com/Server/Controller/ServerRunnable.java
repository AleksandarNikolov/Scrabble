package com.Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * The server runnable
 */
public class ServerRunnable implements Runnable{
    /**
     * All the active named player sessions
     */
    private final Map<String, PlayerSession> sessions;
    /**
     * All the game queues with incomplete games
     */
    private final Map<Integer, List<String>> gameQueues;
    /**
     * Semaphore used to sync game requests with queue supervising
     */
    private final Semaphore gameRequest;
    /**
     * The socket on which this server listens for connections
     */
    private ServerSocket socket;
    /**
     * The port on which this server will open
     */
    private final int port;
    /**
     * A list of all the active games
     */
    private final List<Thread> games;

    /**
     * Instantiates a new server
     */
    public ServerRunnable(int port)
    {
        this.sessions = Collections.synchronizedMap(new HashMap<>());
        this.gameQueues = Collections.synchronizedMap(new HashMap<>());
        this.gameRequest = new Semaphore(0);
        this.games = new ArrayList<>();
        this.port = port;
    }

    @Override
    public void run() {
        openServerSocket();

        Thread connectionsThread = new Thread(() -> listenForConnections());
        Thread gameQueuesHandlerThread = new Thread(() -> superviseQueues());

        try {
            connectionsThread.start();
            gameQueuesHandlerThread.start();

            connectionsThread.join();
            gameQueuesHandlerThread.join();
            for(Thread t : games)
            {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the server socket
     */
    private void openServerSocket() {
        System.out.println("Server starting...");
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("...server accepting connections!");
    }

    /**
     * Actively listens for connections
     */
    private void listenForConnections()
    {
        while(true)
        {
            try {
                Socket s = socket.accept();
                SocketConnector socketConnector = new SocketConnector(s);
                System.out.println("New Connection!");
                PlayerSession session = new PlayerSession(socketConnector);
                putPlayerInLobby(session);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }