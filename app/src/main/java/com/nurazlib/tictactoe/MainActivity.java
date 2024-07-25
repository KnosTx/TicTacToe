package com.nurazlib.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean playerXTurn = true;
    private Button[][] buttons = new Button[3][3];
    private int[][] board = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int id = getResources().getIdentifier("button" + (i * 3 + j + 1), "id", getPackageName());
                buttons[i][j] = findViewById(id);
                final int finalI = i;
                final int finalJ = j;
                buttons[i][j].setOnClickListener(v -> onButtonClick(finalI, finalJ));
            }
        }

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void onButtonClick(int i, int j) {
        if (board[i][j] != 0) {
            return;
        }

        if (playerXTurn) {
            buttons[i][j].setText("X");
            board[i][j] = 1;
        } else {
            buttons[i][j].setText("O");
            board[i][j] = 2;
        }

        if (checkWin()) {
            Toast.makeText(this, (playerXTurn ? "X" : "O") + " wins!", Toast.LENGTH_LONG).show();
            disableButtons();
        } else if (isBoardFull()) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
        } else {
            playerXTurn = !playerXTurn;
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
                return true;
            }
        }
        return (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) ||
               (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        playerXTurn = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }
}
