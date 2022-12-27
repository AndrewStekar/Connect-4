public class gameRule {
    /**
* This method checks if a winning combination is present on the board
*
* @param player  String to indicate which player is being checked ("X" or "O")
* @param board[] string array of all player tiles on board
* @return boolean true if win is detected, false if no win is present
*/

public static boolean checkWin(String player, String board[]) {

    int playerTiles[] = new int[21];// array to store one player's board positions (index)
    int playerChipCount = 0; // number of one player's chips on the board
    boolean check1 = false;// used to check for 3 other chips in a line
    boolean check2 = false;
    boolean check3 = false;
    
    // creating sub-array of what index each player's piece is on
    for (int i = 0; i < board.length; i++) {// looping through array
    if (player == board[i]) {// identifying player's chips
    playerTiles[playerChipCount] = i;// storing chip positions to array
    playerChipCount++;// counting number of player's chips on board
    }
    }
    
    // checking for win conditions
    for (int i = 0; i < playerChipCount; i++) {// checking every possible lowest nunber to form a line
    
    // checking for downward diagonal lines
    check1 = false;
    check2 = false;
    check3 = false;
    if (playerTiles[i] == 0 || playerTiles[i] == 1 || playerTiles[i] == 2 || playerTiles[i] == 3
    || playerTiles[i] == 7 || playerTiles[i] == 8 || playerTiles[i] == 9 || playerTiles[i] == 10
    || playerTiles[i] == 14 || playerTiles[i] == 15 || playerTiles[i] == 16 || playerTiles[i] == 17) {
    for (int j = 0; j < playerChipCount; j++) {
    if (playerTiles[j] == playerTiles[i] + 8) {
    check1 = true;
    }
    if (playerTiles[j] == playerTiles[i] + 16) {
    check2 = true;
    }
    if (playerTiles[j] == playerTiles[i] + 24) {
    check3 = true;
    }
    }
    if (check1 == true && check2 == true && check3 == true) {
    return true;
    }
    }
    // checking for upward diagonal lines
    check1 = false;
    check2 = false;
    check3 = false;
    if (playerTiles[i] == 3 || playerTiles[i] == 4 || playerTiles[i] == 5 || playerTiles[i] == 6
    || playerTiles[i] == 12 || playerTiles[i] == 13 || playerTiles[i] == 14 || playerTiles[i] == 15
    || playerTiles[i] == 17 || playerTiles[i] == 18 || playerTiles[i] == 19 || playerTiles[i] == 20) {
    for (int j = 0; j < playerChipCount; j++) {
    if (playerTiles[j] == playerTiles[i] + 6) {
    check1 = true;
    }
    if (playerTiles[j] == playerTiles[i] + 12) {
    check2 = true;
    }
    if (playerTiles[j] == playerTiles[i] + 18) {
    check3 = true;
    }
    }
    if (check1 == true && check2 == true && check3 == true) {
    return true;
    }
    }
    // checking for vertical lines
    check1 = false;
    check2 = false;
    check3 = false;
    if (playerTiles[i] <= 20) {
    for (int j = 0; j < playerChipCount; j++) {
    if (playerTiles[j] == playerTiles[i] + 7) {
    check1 = true;
    }
    if (playerTiles[j] == playerTiles[i] + 14) {
    check2 = true;
    }
    if (playerTiles[j] == playerTiles[i] + 21) {
    check3 = true;
    }
    }
    if (check1 == true && check2 == true && check3 == true) {
    return true;
    }
    }
    // checking for horizontal lines
    check1 = false;
    check2 = false;
    check3 = false;
    if (playerTiles[i] == 0 || playerTiles[i] == 1 || playerTiles[i] == 2 || playerTiles[i] == 3
    || playerTiles[i] == 7 || playerTiles[i] == 8 || playerTiles[i] == 9 || playerTiles[i] == 10
    || playerTiles[i] == 14 || playerTiles[i] == 15 || playerTiles[i] == 16 || playerTiles[i] == 17
    || playerTiles[i] == 21 || playerTiles[i] == 22 || playerTiles[i] == 23 || playerTiles[i] == 24
    || playerTiles[i] == 28 || playerTiles[i] == 29 || playerTiles[i] == 30 || playerTiles[i] == 31
    || playerTiles[i] == 35 || playerTiles[i] == 36 || playerTiles[i] == 37 || playerTiles[i] == 38) {
    for (int j = 0; j < playerChipCount; j++) {
    if (playerTiles[j] == playerTiles[i] + 1) {
    check1 = true;
    }
    if (playerTiles[j] == playerTiles[i] + 2) {
    check2 = true;
    }
    if (playerTiles[j] == playerTiles[i] + 3) {
    check3 = true;
    }
    }
    if (check1 == true && check2 == true && check3 == true) {
    return true;
    }
    }
    
    }
    return false;// no win found
    }
}
