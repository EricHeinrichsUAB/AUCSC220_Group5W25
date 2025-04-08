package com.turbo21;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;

public class HandWonActivity extends AppCompatActivity {

    private Button quitButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_hand_won);

        quitButton = findViewById(R.id.quitButton);
        nextButton = findViewById(R.id.nextButton);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to home screen
                Intent intent = new Intent(HandWonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to play screen
                Intent intent = new Intent(HandWonActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });
    }
}
