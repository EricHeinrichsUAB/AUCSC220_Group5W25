package com.turbo21;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button settingsButton;
    private Button shopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Hides the title and navigation bar
        // Taken directly from https://stackoverflow.com/questions/30812606/how-to-hide-navigation-bar-in-android-app
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Disables the back button
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                return;
            }
        });

        setContentView(R.layout.screen_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /* Check if music is enabled in shared preferences */
        boolean musicEnabled = getSharedPreferences("prefs", MODE_PRIVATE)
                .getBoolean("music_enabled", true); /* true by default */

        /* If music is enabled and not running, start the background music service */
        if (musicEnabled && !BackgroundMusicService.isRunning) {
            startService(new Intent(this, BackgroundMusicService.class));
        }
        GameManager.Difficulty = getSharedPreferences("prefs", MODE_PRIVATE)
                .getString("difficulty", "medium");

        playButton = findViewById(R.id.playButton);
        settingsButton = findViewById(R.id.settingsButton);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Create an Intent to start the PlayActivity
                Source: https://stackoverflow.com/questions/7991393/how-to-switch-between-screens
                 */
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intent);

                GameManager.StartGame();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SettingsActivity
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}
