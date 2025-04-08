package com.turbo21;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PlayActivity extends AppCompatActivity {
    private ConstraintLayout gameScreen;
    private Button hitButton, standButton;
    private LinearLayout playerHand;
    private LinearLayout dealerHand;
    private TextView playerScore;
    private TextView dealerScore;
    private BasePlayer player = GameManager.Player;
    private BasePlayer dealer = GameManager.Dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen); // Set the play screen layout

        gameScreen = findViewById(R.id.gameScreen);

        hitButton = findViewById(R.id.hitButton);
        standButton = findViewById(R.id.standButton);

        playerHand = findViewById(R.id.playerHand);
        dealerHand = findViewById(R.id.dealerHand);

        playerScore = findViewById(R.id.playerScore);
        dealerScore = findViewById(R.id.dealerScore);

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

        // player can hit or stand
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Don't allow pressing the button when it's the dealer's turn
                if (GameManager.IsDealersTurn) return;

                addCardToHand(playerHand, player);
                updateScore(playerScore, player);

                if (player.score > 21) endRound();
            }
        });//hitButton.setOnClickListener

        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GameManager.IsDealersTurn) return;

                GameManager.IsDealersTurn = true;
                doDealersTurn();
            }
        });//standButton.setOnClickListener

        // drawing the initial 2 cards for each player
        // Alternating between each player to replicate actual blackjack
        addCardToHand(playerHand, player);
        addCardToHand(dealerHand, dealer);
        addCardToHand(playerHand, player);
        addCardToHand(dealerHand, dealer);

        updateScore(playerScore, player);
        updateScore(dealerScore, dealer);


        // calculate score

        // dealer reveals hole card
        // dealer's turn to hit or stand

        // back to player's turn -> next round/loop

    }//onCreate

    private void doDealersTurn() {
        while (GameManager.IsStillDealersTurn()) {
            addCardToHand(dealerHand, dealer);
            updateScore(dealerScore, dealer);
        }
        GameManager.IsDealersTurn = false;
        endRound();
    }

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

    private void addCardToHand(LinearLayout hand, BasePlayer currentPlayer) {
        // Creating a new card to move to the player's hand
        // Adapted from:
        // https://stackoverflow.com/questions/2994494/how-do-i-create-an-imageview-in-java-code-within-an-existing-layout
        ImageView newCard = new ImageView(PlayActivity.this);
        Card cardData = currentPlayer.drawCard();

        // Creating the card's visuals
        int id = getResourceId(PlayActivity.this, cardData.FileName);
        newCard.setImageResource(id);

        hand.addView(newCard);
    }

    private void updateScore(TextView score, BasePlayer currentPlayer) {
        String newScore = String.format("Score: %s", currentPlayer.score);
        score.setText(newScore);
    }

    private void endRound() {
        Intent intent;
        boolean playerWon = didPlayerWin();
        System.out.println(playerWon);

        if (playerWon) {

            // Triggering the phone to vibrate, using the implementation described here
            // https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate-with-different-frequency
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else {
                vibrator.vibrate(100);
            }

            intent = new Intent(PlayActivity.this, HandWonActivity.class);
        }
        else {
            intent = new Intent(PlayActivity.this, HandLostActivity.class);
        }

        startActivity(intent);
    }

    private boolean didPlayerWin() {
        if (player.score > 21){
            return false;
        }
        else if (dealer.score > 21){
            return true;
        }
        else{
            return player.score >= dealer.score;
        }
    }


    /*
    Using the implementation described here:
    https://stackoverflow.com/questions/16369814/how-to-access-the-drawable-resources-by-name-in-android#comment23457549_16369892
     */
    private int getResourceId(Context context, String name) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(name, "drawable", context.getPackageName());
        return id;
    }
}

