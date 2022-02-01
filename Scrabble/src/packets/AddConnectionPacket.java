package packets;

import java.io.Serializable;

import application.Score;

public class AddConnectionPacket implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public int id;
	public String name;
	public int totalScore;
	public Score score;

}