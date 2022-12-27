package connectMiniMax;

import java.io.*;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

public class Text implements View {

	static SerialPort chosenPort;

	File inFile = new File("playerInput.txt");// reads the player input from text file
	File outFile = new File("computerMove.txt");// stores the player's chosen move
	BufferedWriter writer;// declaring writer object
	int prev = 0;// setting previous index to 0
	int input;// player move input
	String line;// reading player input

	// Displays current board
	public void display(State state) {

		char[][] board = state.getBoard();

		for (int row = state.ROWS - 1; row > -1; row--) {
			System.out.print(row + " |");
			for (int column = 0; column < state.COLS; column++) {
				System.out.print(board[row][column]);
				System.out.print(" ");
			}
			System.out.println("\n");
		}

		System.out.print("  ");
		for (int column = 1; column <= state.COLS; column++) {
			System.out.print(" " + column);
		}
		System.out.println("\n");
	}

	// Asks user for move
	public int getUserMove(State state) throws FileNotFoundException, InterruptedException {
		Scanner freader = new Scanner(inFile);// initializing a scanner object
		int column = 0;

		System.out.println();
		System.out.println("Please pick a column");

// Insert code for players column here

		while (true) {
			freader = new Scanner(inFile);
			try {
				line = freader.nextLine();
				freader.close();
				writer = new BufferedWriter(new FileWriter(inFile));
				break;
			} catch (Exception e) {
				continue;
			}
			

		}
		int move = Integer.parseInt(line);
		return move - 1;

	}

	// Reports the move that a player has made.
	public void reportMove(int chosenMove, String name) throws IOException {
		System.out.println("\n" + "Computer" + " chooses the column " + (chosenMove + 1));

		// computer move output to text file
		writer = new BufferedWriter(new FileWriter(outFile));
		writer.write(chosenMove + 1 + "");// writing the computer move to a text file
		writer.close();

	}

	// Ask the user the question and return the answer as an int
	public int getIntAnswer(String question) {
		int answer = 0;
		boolean valid = false;

		// Ask question
		System.out.println(question + " ");

		while (!valid) {
			try {
				answer = input - 1;
				valid = true;
			} catch (NumberFormatException ex) {
				reportToUser("Error: " + ex + " Please enter an integer");
				valid = false;
			}
		}

		return answer;

	}

	// Convery the dedicated message to user
	public void reportToUser(String message) {
		System.out.println(message);
	}

	// Ask question and return answer
	public String getAnswer(String question) {
		System.out.println(question + " ");
		String s = String.valueOf(input - 1);

		return s;

	}

}