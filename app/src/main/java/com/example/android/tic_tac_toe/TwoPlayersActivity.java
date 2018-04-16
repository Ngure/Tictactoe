package com.example.android.tic_tac_toe;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TwoPlayersActivity extends AppCompatActivity implements View.OnClickListener {

    private final Button[][] buttons = new Button[3][3];

    private boolean playerOneTurn = true;

    private int roundCount;

    private int playerOnePoints;
    private int playerTwoPoints;

    private TextView playerOne;
    private TextView playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        playerOne = findViewById(R.id.playerOne);
        playerTwo = findViewById(R.id.playerTwo);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                playerOnePoints = 0;
                playerTwoPoints = 0;

                playerOne.setText("Player 1: " + playerOnePoints);
                playerTwo.setText("Player 2: " + playerTwoPoints);

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (playerOneTurn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (playerOneTurn) {
                playerOneWins();
            } else {
                playerTwoWins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            playerOneTurn = !playerOneTurn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        return field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("") || field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("");

    }

    private void playerOneWins() {
        playerOnePoints++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void playerTwoWins() {
        playerTwoPoints++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    @SuppressLint("SetTextI18n")
    private void updatePointsText() {
        playerOne.setText("Player 1: " + playerOnePoints);
        playerTwo.setText("Player 2: " + playerTwoPoints);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        playerOneTurn = true;

    }
}
