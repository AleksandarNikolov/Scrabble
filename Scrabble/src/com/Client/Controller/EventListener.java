package com.Client.Controller;

import packets.AddConnectionPacket;
import packets.RemoveConnectionPacket;

public class EventListener {
	
	public void received(Object p) {
		if(p instanceof AddConnectionPacket) {
			AddConnectionPacket packet = (AddConnectionPacket)p;
			ConnectionHandler.players.put(packet.name,new Connection(0, packet.name, null));
			System.out.println(packet.id + " has connected");
		}else if(p instanceof RemoveConnectionPacket) {
			RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
			System.out.println("Connection: " + packet.id + " has disconnected");
			ConnectionHandler.players.remove(packet.name);
		}
	}

}