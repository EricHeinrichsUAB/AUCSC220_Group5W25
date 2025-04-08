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

public class HandLostActivity extends AppCompatActivity {

    private Button handLostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_hand_lost);

        handLostButton = findViewById(R.id.handLostButton);

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
