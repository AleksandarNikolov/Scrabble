package com.Client;

import com.Server.SocketConnector;

public class PlayerSession{
	private SocketConnector socketConnector;
	private int score;
	private String tiles;

	/**
	 * Creates a player session
	 * @param socketConnector the socket connector
	 */
	public PlayerSession(SocketConnector socketConnector) {
	    this.socketConnector = socketConnector;
	    this.score = 0;
	    this.tiles = "";
	}
	
}
