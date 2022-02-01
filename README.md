# Scrabble

Authors Aleksandar Nikolov && Andrei Cohan

There are two versions of the game. 
One is a simple single threaded game located in the application folder.
The other is the networked client/server game following the MVC architecture located in the com folder.

To run the simple version:
Run GameTUI from the application folder.
Input name for player1
Input name for player2
Board is printed and a player is chosen at random to being the game.
The tiles of the current player are printed below the board.
There are 3 possible commands to use.
Place, Skip, Exit
Method of use place:
Place word (position of first tile) direction.
Example : Place train H8 H 
Direction  is H for horizontal and V for vertical

To run client/server version:
First run Main in folder com.server
Once it says server has started
Run Main in folder com.client
Use ANNOUNCE playerName to connect to the server using that username
The server and client both connect through local host
This can be changed by changing the string local host into some other ip
To connect a second player: 
Run Main in folder com.client again
Use ANNOUNCE playerName to connect to the server
Note: Please use a different name for each player.
Once both players are connected the game can be played by following the instructions provided in the protocol
