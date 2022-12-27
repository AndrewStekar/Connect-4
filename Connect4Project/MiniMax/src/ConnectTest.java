package connectMiniMax;

import java.io.IOException;

public class ConnectTest extends PlayerMove {
	private int depth;  // depth to search at

	// my weights
	// need to be public/package for testing static evaluation func in Connect4Game.java
	public static final int[] HOW_GOOD = {0, 2, 10^2, 10^3, 10^8}; // index is # of unblocked four-in-row potentials

	// the closer a piece is to the center, the more 4-in-row permutations available.
	// i.e.., generally center piece is most valuable
	private static final int[] movesByCol = { 3, 4, 2, 5, 1, 6, 0 }; 

// Creates computer player with given name (not needed)
	public ConnectTest(String name, int depth){
		super(name);
		this.depth = depth;
	}

	@Override
	public int getMove(State state, View view) throws IOException {
		// First copy the game instance
		ConnectGame stateCopy = new ConnectGame(state.getPlayerNum(), state.getPlayers(), state.getBoard(), evaluate(state), movesDone(state));

		// pick the move
		// start alpha-beta with neg and pos infinities
		Move chosenMoveObj = pickMove(stateCopy, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE, view);
		int chosenMove = chosenMoveObj.move;

		view.reportMove(chosenMove, state.getPlayerToMove().getName());

		return chosenMove;
	} 

	// Uses "tree search" with alpha-beta pruning to find move
	private static Move pickMove(ConnectGame state, int depth, int low, int high, View view){
		Move[] movesArray; // order of moves

		// grab the available moves, sorted by value
		movesArray = checkMoves(state);

		// dummy move that will be replaced with evaluation
		Move bestMove = new Move(-Integer.MAX_VALUE, -10); 


		// Use alpha-beta pruning to pick the move
		for (int i = 0; i < 7 && bestMove.value < high; i++){
			// grab the move from list
			int column = movesArray[i].move;

			if (state.isValidMove(column)){
				Move currentMove;

				// grab value of current position to restore later
				int evalValue = state.grabEvalValue();

				state.makeMove(column);

//				 testing
								view.display(state);
								System.out.println("===============");
								System.out.println("Position Eval # :" + evaluate(state));
								System.out.println("===============");


				if (state.gameIsOver()){
					// Is game over because board is full?
					if (state.isFull()){
						currentMove = new Move(0, column); // assign value of 0
					} 

					// if it's comp's turn, then this must be a win scenario
					currentMove = new Move(HOW_GOOD[4], column);

				} 
				// keep going if depth available
				else if (depth >= 1){

					// Switch player perspective
					// Reduce depth by 1
					currentMove = pickMove(state, depth - 1, -high, -low, view);

					// transfer values back while changing perspective
					currentMove.value = (currentMove.value * -1);
					currentMove.move = column;

				} else { 
					currentMove = new Move(state.grabEvalValue(), column);
				}

				// Is the current move better than what we've found so far?
				if (currentMove.value > bestMove.value){
					bestMove = currentMove; // replace
					low = Math.max(bestMove.value, low); // update the achievable lower bound value
				}

				// undo move before trying next move
				state.undoMove(column, evalValue);
			}

		}

		return bestMove;
	}


	/**
	 * Check the move list for their associated values
	 * Then sort them by value
	 * 
	 * @param state the current state of the game
	 * @return an array of moves sorted by their values
	 */
	private static Move[] checkMoves(ConnectGame state){
		int stateEval; // evaluation of current state based on unblocked 4 in rows
		Move[] movesArray = new Move[ConnectGame.COLS];

		stateEval = state.grabEvalValue();

		// go through each column in move list
		for (int i = 0; i < ConnectGame.COLS; i++){
			int theMove = movesByCol[i];

			movesArray[i] = new Move(-Integer.MAX_VALUE, theMove);
			if (state.isValidMove(theMove)){
				// try the move
				state.makeMove(theMove);

				// now evaluate the new state and store value to check against later
				movesArray[i].value = state.grabEvalValue();

				// undo the state before checking again
				state.undoMove(theMove, stateEval);
			} 
		}

		// sort the move lists by values
		for (int i = 1; i < ConnectGame.COLS; i++){
			for (int compare = i; (compare >=1 && movesArray[compare].value > 
			movesArray[compare - 1].value);
					compare--){
				// placeholder to prevent clobbering
				Move placeholder = movesArray[compare];
				movesArray[compare] = movesArray[compare - 1];
				movesArray[compare - 1] = placeholder;
			}

		}

		// new set of moves with updated values
		return movesArray;
	}

	/**
	 * Helper method that counts the moves made
	 * 
	 * @param state the input state of the board
	 * @return the number of moves already made
	 */
	private static int movesDone(State state){
		// count the pieces

		int counter = 0;
		for (int row = 0; row < ConnectGame.ROWS; row++){
			for (int column = 0; column < ConnectGame.COLS; column++){
				if (state.getBoard()[row][column] != ConnectGame.EMPTY) counter++;
			}
		}

		return counter;
	}

	/**
	 * Evaluate position by finding unblocked 4 in a rows
	 * 
	 * @param state the input state of the board
	 * @return a total int evaluation of unblocked four-in-rows for opp and computer
	 */
	public static int evaluate(State state){
		// grab the checker pieces and board
		char opponent = State.CHECKERS[1 - state.getPlayerNum()];
		char player = State.CHECKERS[state.getPlayerNum()];

		char[][] board = state.getBoard();

		// value that evaluates the unblocked four-in-rows
		int totalEvaluation = 0;

		// Evaluate patterns for winning
		//  
		//   . X X . .   => unblocked on both sides so we can connect 4
		//  by placing another piece to become
		//  . X X X .
		for (int checkColumn = 0; checkColumn < 3; checkColumn ++){
			// if 0 is empty, followed by 2 of my pieces and two more empty, this is a pattern
			if (board[0][checkColumn] == State.EMPTY &&
					board[0][checkColumn + 1] == player && 
					board[0][checkColumn + 2] == player &&
					board[0][checkColumn + 3] == State.EMPTY && 
					board[0][checkColumn + 4] == State.EMPTY){
				totalEvaluation += HOW_GOOD[3];
			} else if (board[0][checkColumn] == State.EMPTY &&
					board[0][checkColumn + 1] == State.EMPTY &&
					board[0][checkColumn + 2] == player &&
					board[0][checkColumn + 3] == player &&
					board[0][checkColumn + 4] == State.EMPTY){
				totalEvaluation += HOW_GOOD[3];
			}
		}


		// Evaluate unblocked verticals
		// all potential ver 4-in-row start from at most from row 2
		for (int column = 0; column < ConnectGame.COLS; column++){
			for (int row = 0; row < 3; row++){
				int compCount = 0;
				int oppCount = 0;

				for (int checkRow = row; checkRow < row + 4; checkRow++){
					if (board[checkRow][column] == player){
						compCount++;
					} else if (board[checkRow][column] == opponent){
						oppCount++;
					}
				}

				totalEvaluation = ConnectGame.applyWeights(oppCount, compCount, totalEvaluation);
			}
		}

		// Evaluate unblocked horizontals
		// all potential hor 4-in-row start from at most from halfway col
		for (int column = 0; column <= 3; column++){
			for(int row = 0; row < ConnectGame.ROWS; row++){
				// counters for computer and opponent
				int compCount = 0;
				int oppCount = 0;

				for (int checkColumn = column; checkColumn < column + 4; checkColumn++){
					// check whose checker it is and increment their counter
					if (board[row][checkColumn] == player){
						compCount++;
					} else if (board[row][checkColumn] == opponent){
						oppCount++;
					}
				}

				totalEvaluation = ConnectGame.applyWeights(oppCount, compCount, totalEvaluation);
			}
		}

		// Evaluate unblocked diagonals (up to right)
		// up to right diagonal start at most from row 2, column 3
		for (int column = 0; column < 4; column++){
			for (int row = 0; row < 3; row++){
				int compCount = 0;
				int oppCount = 0;

				int checkRow = row; // need a checkrow parameter for diag
				for (int checkColumn = column; checkRow < row + 4; checkColumn++){
					if (board[checkRow][checkColumn] == player){
						compCount++;
					} else if (board[checkRow][checkColumn] == opponent){
						oppCount++;
					}

					checkRow++; // adjust for diagonal
				}


				totalEvaluation = ConnectGame.applyWeights(oppCount, compCount, totalEvaluation);
			}
		}

		// Evaluate unblocked diagonals (down to right)
		// down to right diagonal start at most from row 3, column 3
		for (int column = 0; column < 4; column++){
			for (int row = 3; row <= 5; row++){
				int compCount = 0;
				int oppCount = 0;

				int checkRow = row; // need a checkrow parameter for diag
				for (int checkColumn = column; checkColumn < column + 4; checkColumn++){
					if (board[checkRow][checkColumn] == player){
						compCount++;
					} else if (board[checkRow][checkColumn] == opponent){
						oppCount++;
					}

					checkRow--; // adjust for diagonal
				}

				totalEvaluation = ConnectGame.applyWeights(oppCount, compCount, totalEvaluation);
			}
		}

		return totalEvaluation;

	}
}

 