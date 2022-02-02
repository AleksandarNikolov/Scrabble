package com.Client.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import packets.RemovePlayerPacket;

public class Client implements Runnable {
	
	// client variables

	private String host;
	private int port;

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	private boolean running = false;
	private EventListener listener;

	// constructor
	public Client(Socket socket,String username) {
		
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.username = username;
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void listenForMessage() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String msgFromGroupChat;
				while(socket.isConnected()){
					try {
						msgFromGroupChat = bufferedReader.readLine();
						System.out.println(msgFromGroupChat);
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}


	// connect to server
	public void connect() {

		try {
			socket = new Socket(host, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			listener = new EventListener();
			new Thread(this).start();

		} catch (ConnectException e) {
			System.out.println("Unable to connect to server");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// close connection
	public void close() {

		try {
			running = false;
			RemovePlayerPacket packet = new RemovePlayerPacket();
			sendObject(packet); 
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//send data to the server
	public void sendObject(Object packet) {
		
		try {
			out.writeObject(packet);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		try {
			running = true;

			while (running) {

				try {

					Object data = in.readObject();
					listener.received(data);

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SocketException e) {
					close();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}