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
import android.widget.TextView;
import android.util.Log;
import android.graphics.Color;


public class ShopActivity extends AppCompatActivity {

    private Button backButton2;
    private Button SwitchStrikeBuyButton;
    private Button BullseyeBuyButton;
    private Button DoubleDownBuyButton;
    private Player player = GameManager.Player;

    private Shop shop;
    private TextView itemBoughtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.screen_shop);

        // Initialize player if not already initialized
        if (GameManager.Player == null) {
            GameManager.Player = new Player(); // Ensure Player constructor matches your implementation
        }
        player = GameManager.Player;

        shop = new Shop();

        backButton2 = findViewById(R.id.backButton2);
        SwitchStrikeBuyButton = findViewById(R.id.SwitchStrikeBuyButton);
        BullseyeBuyButton = findViewById(R.id.BullseyeBuyButton);
        DoubleDownBuyButton = findViewById(R.id.DoubleDownBuyButton);
        itemBoughtText = findViewById(R.id.itemBoughtText);


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
        e.g. player.Items = [Bullseye, SwitchStrike, DoubleDown]
         */
        BullseyeBuyButton.setOnClickListener(v -> {
            player.subtractMoney(100);
            Item boughtItem = shop.SellItem(1);
            player.addItem(boughtItem);
            String itemAsString = boughtItem.toString();  // outputs "Bullseye"

            String message = "Bought: " + itemAsString +
                    "\nMoney left: " + player.money +
                    "\nInventory: " + player.items;

            itemBoughtText.setText(message);
            itemBoughtText.setTextColor(Color.parseColor("red"));
            itemBoughtText.setVisibility(View.VISIBLE);
        });

        DoubleDownBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

            }
        });

    }

}
