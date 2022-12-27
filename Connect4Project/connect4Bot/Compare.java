public class Compare {
    public int k = 0;
    public static int[] boardFree = new int[42];

    public static int compare(String[] boardSolid, String[] boardSolidold) {
        for (int i = 0; i < boardSolid.length; i++) {
            if (boardSolid[i] == "X" && boardFree[i] == 0) {
                if (!boardSolid[i].equals(boardSolidold[i])) {
                    if (i == 0 || i == 7 || i == 14 || i == 21 || i == 28 || i == 35) {
                        boardFree[i] = 1;
                        return 1;
                    }
                    if (i == 1 || i == 8 || i == 15 || i == 22 || i == 29 || i == 36) {
                        boardFree[i] = 1;
                        return 2;
                    }
                    if (i == 2 || i == 9 || i == 16 || i == 23 || i == 30 || i == 37) {
                        boardFree[i] = 1;
                        return 3;
                    }
                    if (i == 3 || i == 10 || i == 17 || i == 24 || i == 31 || i == 38) {
                        boardFree[i] = 1;
                        return 4;
                    }
                    if (i == 4 || i == 11 || i == 18 || i == 25 || i == 32 || i == 39) {
                        boardFree[i] = 1;
                        return 5;
                    }
                    if (i == 5 || i == 12 || i == 19 || i == 26 || i == 33 || i == 40) {
                        boardFree[i] = 1;
                        return 6;
                    }
                    if (i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41) {
                        boardFree[i] = 1;
                        return 7;
                    }
                    return 0;
                }
            }
        }
        return 0;
    }
}
