package com.nurazlib.tictactoe;

import java.util.Scanner;

public class App {

    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();

        while (true) {
            printBoard();
            playerMove(scanner);
            if (isWinner()) {
                printBoard();
                System.out.println("Pemain " + currentPlayer + " menang!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("Permainan Seri!");
                break;
            }
            switchPlayer();
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) System.out.print("|");
            }
            System.out.println();
            if (i < SIZE - 1) System.out.println("  -----");
        }
    }

    private static void playerMove(Scanner scanner) {
        int row, col;
        while (true) {
            System.out.println("Pemain " + currentPlayer + ", masukkan baris dan kolom (0, 1, atau 2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("Gerakan tidak valid, coba lagi.");
            }
        }
    }

    private static boolean isWinner() {
        // Cek baris dan kolom
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Cek diagonal
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
