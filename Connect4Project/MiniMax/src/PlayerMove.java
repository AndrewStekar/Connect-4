package connectMiniMax;

import java.io.IOException;

public abstract class PlayerMove {
	private String playerName;
	
// Players name
	public PlayerMove (String name) {
		playerName = name;
	}

// Return players name
	public String getName() {
		return playerName;
	}
	
// Gets and returns players move
	public abstract int getMove(State state, View view) throws IOException, InterruptedException;
}
