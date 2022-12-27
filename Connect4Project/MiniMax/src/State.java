package connectMiniMax;

public interface State {
  public final static int ROWS = 6;            // Board height
  public final static int COLS = 7;            // Board width
  public final static char EMPTY = '.';        // Indicate empty place
  public final static char CHECKER0 = 'X';     // Indicate the first player's checker
  public final static char CHECKER1 = 'O';     // Indicate second player's checker
  public final static char [] CHECKERS = {CHECKER0, CHECKER1};
  
// 2d array for the board
  public char [][] getBoard();
  
// Gets player names (not needed)
  public PlayerMove [] getPlayers();
  
// Gets which player is moving
  public int getPlayerNum (); 
  
// Gets current player to move (not needed)
  public PlayerMove getPlayerToMove();

// Is move valid check (not needed)
  public boolean isValidMove(int col);
  
// Make move
  public void makeMove(int col);
  
// Is board full check (not needed)
  public boolean isFull();

// Is game over check (not needed)
  public boolean gameIsOver();
}
