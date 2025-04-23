package com.turbo21;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class SettingsActivity extends AppCompatActivity {

    private Button backButton;
    private Button musicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.screen_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Settings), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (!BackgroundMusicService.isRunning) {
            startService(new Intent(this, BackgroundMusicService.class));
        }

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SettingsActivity
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);

            }//onClick
        });//setOnClickListener
        ToggleButton musicButton = findViewById(R.id.toggleButtonMusic);
        musicButton.setChecked(BackgroundMusicService.isRunning);

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BackgroundMusicService.isRunning) {
                    stopService(new Intent(SettingsActivity.this, BackgroundMusicService.class));
                } else {
                    startService(new Intent(SettingsActivity.this, BackgroundMusicService.class));
                }
            }//onClick

        });//setOnClickListener
    };//onCreate

}
