package connectMiniMax;

import java.io.FileNotFoundException;

public class Human extends PlayerMove {

// Constructs human "profile" with given name
	public Human(String name ) { 
		super(name);
	}
	
	// Overriding same name
	@Override
	public int getMove(State state, View view) throws FileNotFoundException, InterruptedException {
		return view.getUserMove(state);
	} 
	
}