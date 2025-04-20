package com.turbo21;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShopActivity extends AppCompatActivity {

    private Button backButton2;
    private Button SwitchStrikeBuyButton;
    private Button BullseyeBuyButton;
    private Button DoubleDownBuyButton;
    private Player player = GameManager.Player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.screen_shop);

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Settings), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         */

        backButton2 = findViewById(R.id.backButton2);
        SwitchStrikeBuyButton = findViewById(R.id.SwitchStrikeBuyButton);
        BullseyeBuyButton = findViewById(R.id.BullseyeBuyButton);
        DoubleDownBuyButton = findViewById(R.id.DoubleDownBuyButton);


        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SettingsActivity
                Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        SwitchStrikeBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

            }
        });

        /*
        - Player - subtract money from Player
        - Player - add item to list of Player's items (BullseyeCount++)
        - Shop - ?
         */
        BullseyeBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.subtractMoney(5);
                //player.addItem(Bullseye);
            }
        });

        DoubleDownBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

            }
        });

    }

}
