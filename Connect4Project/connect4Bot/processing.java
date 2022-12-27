import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class processing {
    public static int j = 0;
    public static String[] boardSolid = new String[42];
    public static String[] boardSolidold = new String[42];
    public static int index;
    public static String[] board1 = new String[42];
    public static String[] board2 = new String[42];
    public static String[] board3 = new String[42];

    public static void load() throws Exception {

        File inFile = new File("pixel_values.txt");
        FileWriter writer = new FileWriter("playerInput.txt");
        writer.write("" + "0");
        writer.close();
        Scanner freader = new Scanner(inFile); 
        int k = 0; // It prints extra line at the end
        String[] board = new String[42];

        while (freader.hasNextLine() == true) // Iterate over each item in list
        {
            freader.nextLine();
            k++;
        }
        freader.close();

        freader = new Scanner(inFile);
        String[] pixels = new String[k];
        for (int i = 0; i < k; i++) {
            pixels[i] = freader.nextLine();
        }
        freader.close();
        for (int i = 0; i < k; i++) {
            if (pixels[i].equals("1.0")) {
                board[i] = "X";
            } else if (pixels[i].equals("-1.0")) {
                board[i] = "O";
            } else {
                board[i] = " ";
            }
        }
        if (Arrays.equals(boardSolid, board) == false) { // If not equal
            if (j == 0) {
                board1 = board;
                j++;
            } else if (j == 1 && Arrays.equals(board1, board) == true) {
                board2 = board;
                j++;
            } else if (j == 2 && Arrays.equals(board2, board) == true) {
                boardSolidold = boardSolid; // Make a class to compare what column changed
                boardSolid = board;
                j = 0;
            } else {
                j = 0;
            }
        }

        if (boardSolid == board) {
            System.out.println("+---+---+---+---+---+---+---+");
            System.out.println("| " + boardSolid[0] + " | " + boardSolid[1] + " | " + boardSolid[2] + " | "
                    + boardSolid[3] + " | " + boardSolid[4] + " | " + boardSolid[5] + " | " + boardSolid[6] + " |");
            System.out.println("+---+---+---+---+---+---+---+");
            System.out.println("| " + boardSolid[7] + " | " + boardSolid[8] + " | " + boardSolid[9] + " | "
                    + boardSolid[10] + " | " + boardSolid[11] + " | " + boardSolid[12] + " | " + boardSolid[13] + " |");
            System.out.println("+---+---+---+---+---+---+---+");
            System.out.println("| " + boardSolid[14] + " | " + boardSolid[15] + " | " + boardSolid[16] + " | "
                    + boardSolid[17] + " | " + boardSolid[18] + " | " + boardSolid[19] + " | " + boardSolid[20] + " |");
            System.out.println("+---+---+---+---+---+---+---+");
            System.out.println("| " + boardSolid[21] + " | " + boardSolid[22] + " | " + boardSolid[23] + " | "
                    + boardSolid[24] + " | " + boardSolid[25] + " | " + boardSolid[26] + " | " + boardSolid[27] + " |");
            System.out.println("+---+---+---+---+---+---+---+");
            System.out.println("| " + boardSolid[28] + " | " + boardSolid[29] + " | " + boardSolid[30] + " | "
                    + boardSolid[31] + " | " + boardSolid[32] + " | " + boardSolid[33] + " | " + boardSolid[34] + " |");
            System.out.println("+---+---+---+---+---+---+---+");
            System.out.println("| " + boardSolid[35] + " | " + boardSolid[36] + " | " + boardSolid[37] + " | "
                    + boardSolid[38] + " | " + boardSolid[39] + " | " + boardSolid[40] + " | " + boardSolid[41] + " |");
            System.out.println("+---+---+---+---+---+---+---+");
            System.out.println("Red win: " + gameRule.checkWin("X", boardSolid));
            System.out.println("Yellow win: " + gameRule.checkWin("O", boardSolid));
            index = Compare.compare(boardSolid, boardSolidold); // Index should be 0 if player O plays, only send index
                                                                // value to
                                                                // arduino if it updates after an O value, that way it
                                                                // will not keep looping
                                                                // and constantly sending new data to motors
            System.out.println("Index: " + index);
            writer = new FileWriter("playerInput.txt");
            writer.write("" + index);
            writer.close();
            Thread.sleep(300);

        }
    }
}
