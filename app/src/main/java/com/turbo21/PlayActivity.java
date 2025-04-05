package com.turbo21;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.constraintlayout.widget.ConstraintLayout;


import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    private ImageView chip5, chip10, chip25, chip50, chip100;
    private Button hitButton, standButton;
    public int bet;
    private GameManager GameManager = new GameManager();;
    private ConstraintLayout gameScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen); // Set the play screen layout

        GameManager.StartGame();
        gameScreen = findViewById(R.id.gameScreen);

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

                spawnNewImageView();
            }
        });//chip5.OnClickListener

        // player can hit or stand
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deck stack pops a card -> card spawns on Deck -> animation??
                GameManager.DoHitButton();
            }
        });

        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.DoStandButton();
            }
        });


        // calculate score

        // dealer reveals hole card
        // dealer's turn to hit or stand
        //GameManager.DoDealersTurn();

        // back to player's turn -> next round/loop

    }//onCreate

    // animate deck shuffling
    // animate card distribution
    // imagine table is empty except for deck
    // call shuffle function -> pops 4 cards
    // spawns 4 cards and distributes them
    // cards flip
    private void spawnNewImageView() {
        // Create and configure ImageView dynamically
        final ImageView newImageView = new ImageView(PlayActivity.this);
        newImageView.setImageResource(R.drawable.clubs1); // Example card image
        newImageView.setLayoutParams(new ConstraintLayout.LayoutParams(200, 200));

        // Position the ImageView initially outside the screen
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) newImageView.getLayoutParams();
        params.leftMargin = -200; // Initial off-screen position
        params.topMargin = 300; // Adjust position
        newImageView.setLayoutParams(params);

        // Add the ImageView to the layout
        gameScreen.addView(newImageView);

        // Animate the ImageView
        ObjectAnimator animator = ObjectAnimator.ofFloat(newImageView, "translationX", -200f, 600f);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

}

