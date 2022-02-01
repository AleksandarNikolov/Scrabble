package packets;

import java.io.Serializable;

import application.Score;

public class RemovePlayerPacket implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public int id;
	public String name;
	public Score score;
	
}
