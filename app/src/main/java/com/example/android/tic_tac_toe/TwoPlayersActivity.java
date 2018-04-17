package com.example.android.tic_tac_toe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

@SuppressWarnings("ALL") public class TwoPlayersActivity extends AppCompatActivity implements View.OnClickListener {

    private final Button[][] buttons = new Button[3][3];

    private boolean playerOneTurn, playerTwoTurn;

    private int roundCount;

    private int playerOnePoints,playerTwoPoints;

    private TextView playerOne, playerTwo, scorePlayerOne, scorePlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        playerOne = findViewById(R.id.playerOne);
        playerTwo = findViewById(R.id.playerTwo);

        scorePlayerOne = findViewById(R.id.sbPlayerOne);
        scorePlayerTwo = findViewById(R.id.sbPlayerTwo);

        Intent in = getIntent();
        //noinspection deprecation
        final String playerOneName = Objects.requireNonNull(in.getExtras()).getString("playerOneName");
        //noinspection deprecation
        final String playerTwoName = in.getExtras().getString("playerTwoName");
        playerOne.setText(playerOneName);
        playerTwo.setText(playerTwoName);


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

                scorePlayerOne.setText("" + playerOnePoints);
                scorePlayerTwo.setText("" + playerTwoPoints);

            }
        });

        java.util.Random generator = new java.util.Random();
        int rand = generator.nextInt(2) + 1;

        if(rand == 1){
            playerOneTurn = true;
            playerTwoTurn = false;
            Toast.makeText(this, playerOneName + " makes the first move!", Toast.LENGTH_SHORT).show();
        } else {
            playerOneTurn = false;
            playerTwoTurn = true;
            Toast.makeText(this, playerTwoName + " makes the first move!", Toast.LENGTH_SHORT).show();
        }

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

        Intent in = getIntent();
        //noinspection deprecation
        final String playerOneName = Objects.requireNonNull(in.getExtras()).getString("playerOneName");
        playerTwo.setText(playerOneName);

        Toast.makeText(this, playerOneName + " Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void playerTwoWins() {

        playerTwoPoints++;

        Intent in = getIntent();
        //noinspection deprecation
        final String playerTwoName = Objects.requireNonNull(in.getExtras()).getString("playerTwoName");
        playerTwo.setText(playerTwoName);

        Toast.makeText(this, playerTwoName + " Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("SetTextI18n")
    private void updatePointsText() {

        scorePlayerOne.setText("" + playerOnePoints);
        scorePlayerTwo.setText("" + playerTwoPoints);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        Intent in = getIntent();
        //noinspection deprecation
        final String playerOneName = Objects.requireNonNull(in.getExtras()).getString("playerOneName");
        //noinspection deprecation
        final String playerTwoName = in.getExtras().getString("playerTwoName");
        playerOne.setText(playerOneName);
        playerTwo.setText(playerTwoName);

        roundCount = 0;
        playerOneTurn = true;

        java.util.Random generator = new java.util.Random();
        int rand = generator.nextInt(2) + 1;

        if(rand == 1){
            playerOneTurn = true;
            playerTwoTurn = false;
            Toast.makeText(this, playerOneName + " makes the first move!", Toast.LENGTH_SHORT).show();
        } else {
            playerOneTurn = false;
            playerTwoTurn = true;
            Toast.makeText(this, playerTwoName + " makes the first move!", Toast.LENGTH_SHORT).show();
        }

    }
}
