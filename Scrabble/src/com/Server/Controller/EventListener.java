package com.Server.Controller;

import packets.AddConnectionPacket;
import packets.RemoveConnectionPacket;

public class EventListener {
	
	public void received(Object p,Player connection) {
		if(p instanceof AddConnectionPacket) {
			AddConnectionPacket packet = (AddConnectionPacket)p;
			packet.id = connection.id;
			System.out.println("Connection: " + packet.id + " " + packet.name + " has connected");
			for(int i=0; i<PlayerHandler.players.size(); i++) {
				Player c = PlayerHandler.players.get(i);
				if(c != connection) {
					c.sendObject(packet);
				}
			}
			
		}else if(p instanceof RemoveConnectionPacket) {
			RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
			System.out.println("Connection: " + packet.id + " " + packet.name + " has disconnected");
			PlayerHandler.players.get(packet.id).close();
			PlayerHandler.players.remove(packet.id);
		}
	}

}