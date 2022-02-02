package com.Client;

import java.io.IOException;

public class GameSession implements Runnable {
	
	@Override
    public void run() {
        //START GAME
        String startGameParams = "";
        currentPlayer = -1;
        gameOn = true;

        try {
            for (int i = 0; i < players.size(); i++) {
                int id = i;
                PlayerSession s = players.get(i);
                Thread t = new Thread(() -> {
                    try {
                        listen(s, id);
                    } catch (IOException e) {
                        System.out.println("Connection failure on session(" + s.getName() +"): " + e.getMessage());
                    }
                });
                s.setPlayerThread(t);
                startGameParams += Protocol.UNIT_SEPARATOR + players.get(i).getName();
                t.start();
            }
            broadcast(encodeMessage(Protocol.BasicCommand.STARTGAME.name(),
                    startGameParams.substring(1)));
            for (PlayerSession player : players) {
                String firstTiles = tilePoolController.getTilesFromPool(7);
                player.sendMessage(encodeMessage(Protocol.BasicCommand.NEWTILES.name(), firstTiles));
                player.addTiles(firstTiles);
            }
            synchronized (turn) {
                passTurn();
            }
            // GAME ONGOING

            //Check game status
            do {
                turnEnd.acquire();
            } while (gameOnGoing());

            broadcast(buildGameOver());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error running the game:" + e.getMessage());
        }
        // END GAME
        for(PlayerSession s : players)
        {
            s.endSession();
        }
    }

}
