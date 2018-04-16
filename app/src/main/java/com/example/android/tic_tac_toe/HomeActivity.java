package com.example.android.tic_tac_toe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addListenerOnButton();

    }

    private void addListenerOnButton() {

        final Context context = this;

        Button onePlayer = findViewById(R.id.onePlayer);
        Button twoPlayers = findViewById(R.id.twoPlayers);

        onePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OnePlayerActivity.class);
                startActivity(intent);
            }
        });

        twoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, NameActivity.class);
                startActivity(intent);
            }
        });

    }
}
