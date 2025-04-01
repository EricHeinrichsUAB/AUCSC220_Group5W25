package com.turbo21;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.screen_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playButton = findViewById(R.id.playButton);

        // OnClickListener
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create an Intent to start the PlayActivity
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intent);

            }//onClick
        });//setOnClickListener

    }//onCreate()
}