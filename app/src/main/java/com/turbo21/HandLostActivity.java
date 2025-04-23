package com.turbo21;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
