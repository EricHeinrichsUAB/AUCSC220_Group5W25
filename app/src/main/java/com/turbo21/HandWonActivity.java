package com.turbo21;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class HandWonActivity extends AppCompatActivity {

    private Button playButton;
    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.screen_hand_won);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.handWon), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playButton = findViewById(R.id.playButton);
        menuButton = findViewById(R.id.menuButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the PlayActivity
                Intent intent = new Intent(HandWonActivity.this, PlayActivity.class);
                startActivity(intent);
            } //onClick
        }); //setOnClickListener

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SettingsActivity
                Intent intent = new Intent(HandWonActivity.this, MainActivity.class);
                startActivity(intent);
                } //onClick
            }); //setOnClickListener
    }


}
