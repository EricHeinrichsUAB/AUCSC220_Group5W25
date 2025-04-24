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

        setContentView(R.layout.screen_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Settings), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
                boolean turnOff = BackgroundMusicService.isRunning;

                if (turnOff) {
                    stopService(new Intent(SettingsActivity.this, BackgroundMusicService.class));
                } else {
                    startService(new Intent(SettingsActivity.this, BackgroundMusicService.class));
                }

                /* Reference: https://developer.android.com/reference/android/content/SharedPreferences */
                getSharedPreferences("prefs", MODE_PRIVATE)
                        .edit()
                        .putBoolean("music_enabled", !turnOff)
                        .apply();
            }//onClick

        });//setOnClickListener
        ToggleButton easyButton = findViewById(R.id.easyDifficultyButton);
        ToggleButton mediumButton = findViewById(R.id.mediumDifficultyButton);
        ToggleButton hardButton = findViewById(R.id.hardDifficultyButton);

        View.OnClickListener difficultyListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton selected = (ToggleButton) v;

                /* Difficulty buttons behave like radiogroup, only one can be selected at a time */
                easyButton.setChecked(selected == easyButton);
                mediumButton.setChecked(selected == mediumButton);
                hardButton.setChecked(selected == hardButton);

                String difficulty = "medium";
                if (selected == easyButton) {
                    difficulty = "easy";
                } else if (selected == hardButton) {
                    difficulty = "hard";
                }
                /* Store selected difficulty in shared preferences and GameManager */
                getSharedPreferences("prefs", MODE_PRIVATE)
                        .edit()
                        .putString("difficulty", difficulty)
                        .apply();
                GameManager.Difficulty = difficulty;
            }//onClick

        };//setOnClickListener

        /* Set listeners for difficulty buttons */
        easyButton.setOnClickListener(difficultyListener);
        mediumButton.setOnClickListener(difficultyListener);
        hardButton.setOnClickListener(difficultyListener);

        /* Highlighting the difficulty button that was previously selected */
        String difficultySelected = getSharedPreferences("prefs", MODE_PRIVATE)
                .getString("difficulty", "medium");
        switch (difficultySelected) {
            case "easy":
                easyButton.setChecked(true);
                break;
            case "medium":
                mediumButton.setChecked(true);
                break;
            case "hard":
                hardButton.setChecked(true);
                break;
        }

    }//onCreate

}
