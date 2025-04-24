package com.turbo21;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class HandLostActivity extends AppCompatActivity {

    private Button handLostButton;
    private TextView roundsPlayedText;
    private TextView cardsDrawnText;
    private TextView finalScoreText;

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
                Intent intent = new Intent(HandLostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        setContentView(R.layout.screen_hand_lost);

        handLostButton = findViewById(R.id.handLostButton);
        roundsPlayedText = findViewById(R.id.textRoundsPlayed);
        cardsDrawnText = findViewById(R.id.textCardsDrawn);
        finalScoreText = findViewById(R.id.textFinalScore);

        roundsPlayedText.setText(String.format("Rounds Played: %s", GameManager.RoundNumber));
        cardsDrawnText.setText(String.format("Cards Drawn: %s", GameManager.Player.cardsDrawn));
        finalScoreText.setText(String.format("Final Score: %s", GameManager.Player.overallScore));

        handLostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to home screen
                Intent intent = new Intent(HandLostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
