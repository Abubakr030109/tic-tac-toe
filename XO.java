import java.util.Scanner;

public class XO {
    private static boolean gameOver = false;
    private static boolean player1 = true; // player1's turn
    private static boolean userErrors = false;

    private static int X = 0;
    private static int O = 0;

    private static final String EMPTY_ROW = "   |   |   ";
    private static final String X_CELL1 = " X |   |   ";
    private static final String X_CELL2 = "   | X |   ";
    private static final String X_CELL3 = "   |   | X ";
    private static final String O_CELL1 = " O |   |   ";
    private static final String O_CELL2 = "   | O |   ";
    private static final String O_CELL3 = "   |   | O ";

    private static String row1 = EMPTY_ROW;
    private static String row2 = EMPTY_ROW;
    private static String row3 = EMPTY_ROW;

    private static final int COL1 = 1;
    private static final int COL2 = 5;
    private static final int COL3 = 9;

    public static void main(String[] args) {
        displayBoard();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers from 1 to 9");

        while (!gameOver) {
            if (player1) {
                System.out.print("X's turn: ");
                X = scanner.nextInt();
                XLogic();
            } else {
                System.out.print("O's turn: ");
                O = scanner.nextInt();
                OLogic();
            }

            System.out.println();
            displayBoard();
            System.out.println();

            if (!userErrors) {
                if (player1) // Alternate between players
                    player1 = false;
                else
                    player1 = true;
            } else {
                userErrors = false;
            }

            boardAnalysis();

        }
        scanner.close();
    }

    private static void XLogic() {
        if (X > 9 || X < 1) {
            System.out.println("Error: Number is out of range");
            userErrors = true;
            return;
        }
        switch (X) {
            case 1:
                setBoardState(3, X_CELL1);
                break;
            case 2:
                setBoardState(3, X_CELL2);
                break;
            case 3:
                setBoardState(3, X_CELL3);
                break;
            case 4:
                setBoardState(2, X_CELL1);
                break;
            case 5:
                setBoardState(2, X_CELL2);
                break;
            case 6:
                setBoardState(2, X_CELL3);
                break;
            case 7:
                setBoardState(1, X_CELL1);
                break;
            case 8:
                setBoardState(1, X_CELL2);
                break;
            case 9:
                setBoardState(1, X_CELL3);
                break;
        }
    }

    private static void OLogic() {
        if (O > 9 || O < 1) {
            System.out.println("Error: Number is out of range");
            userErrors = true;
            return;
        }
        switch (O) {
            case 1:
                setBoardState(3, O_CELL1);
                break;
            case 2:
                setBoardState(3, O_CELL2);
                break;
            case 3:
                setBoardState(3, O_CELL3);
                break;
            case 4:
                setBoardState(2, O_CELL1);
                break;
            case 5:
                setBoardState(2, O_CELL2);
                break;
            case 6:
                setBoardState(2, O_CELL3);
                break;
            case 7:
                setBoardState(1, O_CELL1);
                break;
            case 8:
                setBoardState(1, O_CELL2);
                break;
            case 9:
                setBoardState(1, O_CELL3);
                break;
        }
    }

    private static void setBoardState(int rowNum, String newRow) {
        String oldRow;
        switch (rowNum) { // Checks ROW NUMBERS
            case 1:
                oldRow = row1;
                row1 = validateMove(row1, newRow);
                if (oldRow.equals(row1)) {
                    System.out.println("Error: Cell is already occupied");
                    userErrors = true;
                }
                break;
            case 2:
                oldRow = row2;
                row2 = validateMove(row2, newRow);
                if (oldRow.equals(row2)) {
                    System.out.println("Error: Cell is already occupied");
                    userErrors = true;
                }
                break;
            case 3:
                oldRow = row3; // " X | O | "
                row3 = validateMove(row3, newRow); // " X | X | "
                if (oldRow.equals(row3)) {
                    System.out.println("Error: Cell is already occupied");
                    userErrors = true;
                }
        }
    }

    private static String validateMove(String row, String newRow) {
        if (row.charAt(COL1) == ' ' && newRow.charAt(COL1) != ' ')
            row = row.substring(0, COL1) + newRow.charAt(COL1) + row.substring(COL1 + 1);
        //
        else if (row.charAt(COL2) == ' ' && newRow.charAt(COL2) != ' ')
            row = row.substring(0, COL2) + newRow.charAt(COL2) + row.substring(COL2 + 1);
        //
        else if (row.charAt(COL3) == ' ' && newRow.charAt(COL3) != ' ')
            row = row.substring(0, COL3) + newRow.charAt(COL3) + row.substring(COL3 + 1);
        return row;
    }

    // ------------------------------------------

    private static void displayBoard() {
        System.out.println(row1);
        System.out.println("-----------");
        System.out.println(row2);
        System.out.println("-----------");
        System.out.println(row3);
    }

    // ------------------------------------------

    private static void boardAnalysis() {
        final String X_ROW = " X | X | X ";
        final String O_ROW = " O | O | O ";

        if (row1.equals(X_ROW) || row2.equals(X_ROW) || row3.equals(X_ROW) || columWin('X') || diagonalWin('X')) {
            System.out.println("X Wins!!!");
            gameOver = true;
            return;
        }

        if (row1.equals(O_ROW) || row2.equals(O_ROW) || row3.equals(O_ROW) || columWin('O') || diagonalWin('O')) {
            System.out.println("O Wins!!!");
            gameOver = true;
            return;
        }

        boolean isAllCellsFilled = (row1.charAt(COL1) != ' ' && row1.charAt(COL2) != ' ' && row1.charAt(COL3) != ' ')
                && (row2.charAt(COL1) != ' ' && row2.charAt(COL2) != ' ' && row2.charAt(COL3) != ' ')
                && (row3.charAt(COL1) != ' ' && row3.charAt(COL2) != ' ' && row3.charAt(COL3) != ' ');

        if (isAllCellsFilled) {
            System.out.println("It's a tie");
            gameOver = true;
        }
    }

    private static boolean diagonalWin(char playerPiece) {
        return (row1.charAt(COL1) == playerPiece && row2.charAt(COL2) == playerPiece
                && row3.charAt(COL3) == playerPiece)
                || (row1.charAt(COL3) == playerPiece && row2.charAt(COL2) == playerPiece
                        && row3.charAt(COL1) == playerPiece);
    }

    private static boolean columWin(char playerPiece) {
        boolean samePiecesInColum; // Is there any colum with all the same pieces in it

        samePiecesInColum = (row1.charAt(COL1) == playerPiece && row2.charAt(COL1) == playerPiece
                && row3.charAt(COL1) == playerPiece) ||
                (row1.charAt(COL2) == playerPiece && row2.charAt(COL2) == playerPiece
                        && row3.charAt(COL2) == playerPiece)
                || (row1.charAt(COL3) == playerPiece && row2.charAt(COL3) == playerPiece
                        && row3.charAt(COL3) == playerPiece);

        return samePiecesInColum;
    }
}