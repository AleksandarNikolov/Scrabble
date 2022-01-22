package client;

import application.Player;
import packets.AddPlayerPacket;
import packets.RemovePlayerPacket;

public class EventListener {
	
	public void recieved(Object p) {
		if(p instanceof AddPlayerPacket) {
			AddPlayerPacket packet = (AddPlayerPacket)p;
			PlayerHandler.players.put(packet.id, new Player(packet.id,packet.name,packet.score));
			System.out.println(packet.name + "Has joined the game");
			
		}else if(p instanceof RemovePlayerPacket) {
			RemovePlayerPacket packet = (RemovePlayerPacket)p;
			System.out.println(PlayerHandler.players.get(packet.id).name + "has left the game");
			PlayerHandler.players.remove(packet.id);
			
		}
	}

}
