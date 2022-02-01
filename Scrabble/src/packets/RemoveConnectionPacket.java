package packets;

import java.io.Serializable;

import application.Score;

public class RemoveConnectionPacket implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public int id;
	public String name;
	public Score score;

}