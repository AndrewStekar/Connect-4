package connectMiniMax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

public class Unbeatable {
	
	public static void main(String [] args) throws IOException, InterruptedException{
		
		File outFile = new File("computerMove.txt");// stores the player's chosen move
		BufferedWriter writer;
		Scanner input = new Scanner(System.in);
		String answer = "";
		View view;
		
		if (answer.contains("Text")){
			view = new Text();
		} else {
		 view = new Text();
		}

		PlayerMove [] players = new PlayerMove[2];

		// Initialize the game
		// Computer - for computer

		String playerName = "Computer";
		if (playerName.contains("Computer")){
			int askDepth = 6;
			players[0] = new ConnectTest(playerName, askDepth);
		} else { 
			players[0] = new Human(playerName);
		}

		playerName = "Player";

		 if(playerName == "Player") { 
			players[1] = new Human(playerName);
		}

		 
		 // 1 for player start, 0 for computer start
		ConnectGame state = new ConnectGame(0, players); 

		view.display(state);

		// Hold current game state
		while (!state.gameIsOver()){
			int move = state.getPlayerToMove().getMove(state, view);

			state.makeMove(move);
			view.display(state);
		}

		// The game is over
		// declare the winner!
		view.reportToUser(state.getPlayers()[1 - state.getPlayerNum()].getName() + " won!");
		
		if ((state.getPlayers()[1 - state.getPlayerNum()].getName()).equals("Computer")) {
			writer = new BufferedWriter(new FileWriter(outFile));
			writer.write(9 + "");
			writer.close();
		} else {
			writer = new BufferedWriter(new FileWriter(outFile));
			writer.write(8 + "");
			writer.close();
		}
System.out.println();
	}
}
