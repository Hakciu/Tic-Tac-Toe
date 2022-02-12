package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //Game results
    public static boolean isWinner(char player, char[][] gameGrid) {
        int sum = 0;
        int sumEnd;
        if (player == 'X') sumEnd = 264;
        else sumEnd = 237;

        //Checking horizontal
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                sum += gameGrid[i][j];
            }
            if (sum == sumEnd) return true;
            sum = 0;
        }

        //Checking vertical
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                sum += gameGrid[j][i];
            }
            if (sum == sumEnd) return true;
            sum = 0;
        }

        //Checking diagonal \
        for (int i = 0, j = 0; i < gameGrid.length; i++, j++) {
            sum += gameGrid[i][j];
        }
        if (sum == sumEnd) return true;
        sum = 0;

        //Checking diagonal /
        for (int i = 0, j = 2; i < gameGrid.length; i++, j--) {
            sum += gameGrid[i][j];
        }
        return sum == sumEnd;
    }
    public static int getNumberOfSymbols(char whatIsCounted, char[][] gameGrid) {
        //Counts the occurrence of a symbol and returns its number
        int count = 0;
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[i][j] == whatIsCounted) count++;
            }
        }
        return count;
    }

    public static void gameDisplay(char[][] gameGrid) {
        System.out.println("---------");
        for (char[] chars : gameGrid) {
            System.out.print("| ");
            for (int j = 0; j < gameGrid.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void addingSymbol(char symbol, char[][] gameGrid) {
        Scanner sc = new Scanner(System.in);
        boolean coordinates = true;
        int n;
        int m;
        do {
            System.out.print("Enter the coordinates: ");
            //Checking if number is an int
            if (!sc.hasNextInt()) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
                continue;
            } else {
                n = sc.nextInt();
                if (!sc.hasNextInt()) {
                    System.out.println("You should enter numbers!");
                    sc.nextLine();
                    continue;
                } else {
                    m = sc.nextInt();
                }
            }

            //Checking if coordinates are good  1 <= x <= 3
            if (n > 3 || n < 1 || m > 3 || m < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            //Checking if cell is occupied
            if (gameGrid[n - 1][m - 1] == ' ') {
                gameGrid[n - 1][m - 1] = symbol;
                coordinates = false;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } while (coordinates);
    }

    public static boolean isGameEnd(char[][] gameGrid) {
        boolean xWinner = isWinner('X', gameGrid);
        boolean oWinner = isWinner('O', gameGrid);
        int _s = getNumberOfSymbols(' ', gameGrid);

        //Results
        if (xWinner && !oWinner) {
            gameDisplay(gameGrid);
            System.out.println("X wins");
            return true;
        } else if (!xWinner && oWinner) {
            gameDisplay(gameGrid);
            System.out.println("O wins");
            return true;
        } else if (!xWinner && !oWinner && _s == 0) {
            gameDisplay(gameGrid);
            System.out.println("Draw");
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        //Creating game grid
        char[][] gameGrid = new char[3][3];
        for (char[] chars : gameGrid) {
            Arrays.fill(chars, ' ');
        }

        boolean gameEnd = false;
        int symNum = 0;
        char symbol;

        do {
            //Display game
            gameDisplay(gameGrid);

            //Setting symbol
            if (symNum % 2 == 0) {
                symbol = 'X';
            } else {
                symbol = 'O';
            }

            //Adding symbol to board
            addingSymbol(symbol, gameGrid);

            //Change symbol
            symNum++;

            //Getting results. If game is over end program;
            if (isGameEnd(gameGrid)) {
                gameEnd = true;
            }

        } while (!gameEnd);
    }
}