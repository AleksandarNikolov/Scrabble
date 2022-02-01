package com.Server.Controller;

import packets.AddConnectionPacket;
import packets.RemoveConnectionPacket;

public class EventListener {
	
	public void received(Object p,Connection connection) {
		if(p instanceof AddConnectionPacket) {
			AddConnectionPacket packet = (AddConnectionPacket)p;
			packet.id = connection.id;
			System.out.println("Connection: " + packet.id + " " + packet.name + " has connected");
			for(int i=0; i<ConnectionHandler.players.size(); i++) {
				Connection c = ConnectionHandler.players.get(i);
				if(c != connection) {
					c.sendObject(packet);
				}
			}
			
		}else if(p instanceof RemoveConnectionPacket) {
			RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
			System.out.println("Connection: " + packet.id + " " + packet.name + " has disconnected");
			ConnectionHandler.players.get(packet.id).close();
			ConnectionHandler.players.remove(packet.id);
		}
	}

}