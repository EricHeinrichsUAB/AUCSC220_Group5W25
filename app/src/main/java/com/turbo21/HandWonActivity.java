package com.turbo21;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandWonActivity extends AppCompatActivity {
    private TextView roundScore;
    private TextView overallScore;

    private Button quitButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hides the title and navigation bar
        // Taken directly from https://stackoverflow.com/questions/30812606/how-to-hide-navigation-bar-in-android-app
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Changes the back button to instead send the player back to the main menu
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(HandWonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        setContentView(R.layout.screen_hand_won);

        roundScore = findViewById(R.id.roundScore);
        overallScore = findViewById(R.id.overallScore);

        quitButton = findViewById(R.id.quitButton);
        nextButton = findViewById(R.id.nextButton);

        roundScore.setText(String.format("Round Score: %s", GameManager.Player.actualScore));
        overallScore.setText(String.format("Overall Score: %s", GameManager.Player.OverallScore));

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
