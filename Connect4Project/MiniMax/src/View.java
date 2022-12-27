package connectMiniMax;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface View {

// Displays current board
	public void display (State state);

// Asks user for move
	public int getUserMove(State state) throws FileNotFoundException, InterruptedException;

// Reports move
	public void reportMove (int chosenMove, String name) throws IOException;

// Get int answer from user
	public int getIntAnswer (String question);

// Message to user
	public void reportToUser(String message);

// Ask question & return answer
	public String getAnswer(String question);
}
