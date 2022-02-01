package com.Client.Controller;

import application.Score;

public class Connection {
	
	public int id;
	public String name;
	public Score score;
	
	public Connection(int id ,String name ,Score score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Score getScore() {
		return this.score;
	}
	public void setScore(Score score) {
		this.score = score;
	}

}