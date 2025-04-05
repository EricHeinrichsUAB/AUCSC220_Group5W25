package com.turbo21;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PlayActivity extends AppCompatActivity {
    private ConstraintLayout gameScreen;
    private ImageView chip5, chip10, chip25, chip50, chip100;
    private ImageView topOfDeck;
    private Button hitButton, standButton;
    private LinearLayout playerHand;
    private LinearLayout dealerHand;
    public int bet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen); // Set the play screen layout

        gameScreen = findViewById(R.id.gameScreen);
        topOfDeck = findViewById(R.id.topOfDeck);

        hitButton = findViewById(R.id.hitButton);
        standButton = findViewById(R.id.standButton);

        playerHand = findViewById(R.id.playerHand);

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

        /* if a chip is pressed
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
         */

        // player can hit or stand
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deck stack pops a card -> card spawns on Deck -> animation??
                Card cardData = GameManager.DoHitButton();

                // Creating a new card to move to the player's hand
                // Adapted from:
                // https://stackoverflow.com/questions/2994494/how-do-i-create-an-imageview-in-java-code-within-an-existing-layout
                ImageView newCard = new ImageView(PlayActivity.this);

                // Creating the card's visuals
                int id = getResource(v.getContext(), cardData.FileName);
                newCard.setImageResource(id);
                newCard.bringToFront();

                playerHand.addView(newCard);
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

    // animate deck shuffling
    // animate card distribution
    // imagine table is empty except for deck
    // call shuffle function -> pops 4 cards
    // spawns 4 cards and distributes them
    // cards flip
    private void spawnNewImageView() {
        // Create and configure ImageView dynamically
        final ImageView newImageView = new ImageView(PlayActivity.this);
        newImageView.setImageResource(R.drawable.clubsace); // Example card image
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


    /*
    Using the implementation described here:
    https://stackoverflow.com/questions/16369814/how-to-access-the-drawable-resources-by-name-in-android#comment23457549_16369892
     */
    private int getResource (Context context, String name) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(name, "drawable", context.getPackageName());
        return id;
    }
}

