package com.example.android.tic_tac_toe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        addListenerOnButton();

    }

    private void addListenerOnButton() {

        final Context context = this;

        Button begin = findViewById(R.id.begin);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText playerOneName = findViewById(R.id.playerOneName);
                EditText playerTwoName = findViewById(R.id.playerTwoName);

                Intent intent = new Intent(context, TwoPlayersActivity.class);
                intent.putExtra("playerOneName", playerOneName.getText().toString());
                intent.putExtra("playerTwoName", playerTwoName.getText().toString());
                startActivity(intent);
            }
        });
    }
}