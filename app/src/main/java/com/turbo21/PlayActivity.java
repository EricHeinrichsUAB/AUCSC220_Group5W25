package com.turbo21;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    private ImageView chip5, chip10, chip25, chip50, chip100;
    private Button hitButton, standButton;
    public int bet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen); // Set the play screen layout

        chip5 = findViewById(R.id.chip5);
        chip10 = findViewById(R.id.chip10);
        chip25 = findViewById(R.id.chip25);
        chip50 = findViewById(R.id.chip50);
        chip100 = findViewById(R.id.chip100);

        hitButton = findViewById(R.id.hitButton);
        standButton = findViewById(R.id.standButton);

        // ok, game starts from here
        // player decides how much to bet
        // the player and dealer are dealt random cards from the deck
        // player's two cards are face up, while dealers' has one up, one down
        // player gets to press hit or stand
        // hit -> draw card -> increase sum of hand
        // stand -> do nothing -> dealer's turn
        // calculate score
        // dealer reveals hole card
        // dealer hits or stands
        // new round

        // if a chip is pressed
        chip5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // want all the chips to slide away??
                bet = 5;

                // Create ObjectAnimator to animate translationX (horizontal movement)
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(chip5, "translationX", 0f, 500f);
                animator1.setDuration(1000);
                animator1.setInterpolator(new AccelerateDecelerateInterpolator()); //interpolator makes animation smoother
                animator1.start();

                // animate deck shuffling


                // animate card distribution
            }
        });//chip5.OnClickListener


        // player can hit or stand
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deck stack pops a card -> card spawns on Deck -> animation??
                GameManager.DoHitButton();
            }
        });//hitButton.setOnClickListener

        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.DoStandButton();
            }
        });//standButton.setOnClickListener


        // calculate score

        // dealer reveals hole card
        // dealer's turn to hit or stand

        // back to player's turn -> next round/loop

    }//onCreate
}

