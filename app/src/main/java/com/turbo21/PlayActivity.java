package com.turbo21;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PlayActivity extends AppCompatActivity {
    private ConstraintLayout gameScreen;
    private Button hitButton, standButton, BullseyeButton, SwitchStrikeButton;
    private LinearLayout playerHand;
    private LinearLayout dealerHand;
    private TextView playerScore;
    private TextView dealerScore;
    private Player player = GameManager.Player;
    private BasePlayer dealer = GameManager.Dealer;
    Bullseye bullseye;
    private double bullseyeMultiplier = 1.0;
    SwitchStrike switchstrike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hides the title and navigation bar
        // Taken directly from https://stackoverflow.com/questions/30812606/how-to-hide-navigation-bar-in-android-app
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Changes the back button to instead send the player back to the main menu
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        setContentView(R.layout.play_screen); // Set the play screen layout

        gameScreen = findViewById(R.id.gameScreen);

        hitButton = findViewById(R.id.hitButton);
        standButton = findViewById(R.id.standButton);
        BullseyeButton = findViewById(R.id.BullseyeButton);
        SwitchStrikeButton = findViewById(R.id.SwitchStrikeButton);

        playerHand = findViewById(R.id.playerHand);
        dealerHand = findViewById(R.id.dealerHand);

        playerScore = findViewById(R.id.playerScore);
        dealerScore = findViewById(R.id.dealerScore);

        // player can hit or stand
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Don't allow pressing the button when it's the dealer's turn
                if (GameManager.IsDealersTurn) return;

                addCardToHand(playerHand, player);
                updateScore(playerScore, player);

                player.cardsDrawn++;

                if (player.actualScore > 21) {
                    GameManager.IsDealersTurn = true;
                    // Delays transitioning to the next screen by 1 second
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        endRound();
                    }, 1000);
                }
            }
        });

        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GameManager.IsDealersTurn) return;

                GameManager.IsDealersTurn = true;
                doDealersTurn();
            }
        });

        // Remember to make it so that this can only be clicked before/after player hit
        BullseyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bullseye = new Bullseye();
                bullseye.UseItem(player.actualScore);
                bullseyeMultiplier = bullseye.multiplier;
            }
        });

        SwitchStrikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchstrike = new SwitchStrike();

                // Remove all cards from each hand
                playerHand.removeAllViews();
                dealerHand.removeAllViews();

                // Switch cards
                switchstrike.UseItem(dealer.cards, player.cards);
                dealer.cards = switchstrike.newDealerHand;
                player.cards = switchstrike.newPlayerHand;

                // Switch scores
                updateScore(dealerScore, dealer);
                updateScore(playerScore, player);

                // Add cards to each hand
                addCardToPlayerHand(playerHand);
                addCardToDealerHand(dealerHand);
            }
        });

        GameManager.StartRound();

        // drawing the initial 2 cards for each player
        // Alternating between each player to replicate actual blackjack
        addCardToHand(playerHand, player);
        addCardToHand(dealerHand, dealer, true);
        addCardToHand(playerHand, player);
        addCardToHand(dealerHand, dealer);

        updateScore(playerScore, player);
        updateScore(dealerScore, dealer);

    }//onCreate

    private void doDealersTurn() {
        Card holeCard = dealer.cards.get(0);
        holeCard.toggleHidden();

        ImageView holeCardView = (ImageView) dealerHand.getChildAt(0);
        int id = getResourceId(PlayActivity.this, holeCard.FileName);
        holeCardView.setImageResource(id);
        updateScore(dealerScore, dealer);

        // Using a handler allows us to run the dealer's AI at a fixed interval, in this case 1 second
        // Adapted from https://stackoverflow.com/questions/41664409/wait-for-5-seconds
        Handler handler = new Handler();
        Runnable dealerFunction = new Runnable() {
            @Override
            public void run() {
                if (GameManager.IsStillDealersTurn()) {
                    addCardToHand(dealerHand, dealer);
                    updateScore(dealerScore, dealer);

                    handler.postDelayed(this, 1000);
                }
                else {
                    handler.postDelayed(() -> {
                        endRound();
                    }, 1000);
                }
            }
        };

        handler.postDelayed(dealerFunction, 1000);
    }

    private void addCardToPlayerHand(LinearLayout hand) {
        // Add player.cards (player's new cards) into playerHand
        for (int i = 0; i < player.cards.size(); i++) {
            ImageView newCard = new ImageView(PlayActivity.this);
            Card cardData = player.cards.get(i); //Card
            int id;

            if (cardData == player.cards.get(0)) {
                cardData.toggleHidden();
            }

            id = getResourceId(PlayActivity.this, cardData.FileName);
            newCard.setImageResource(id);

            hand.addView(newCard);
        }
    }

    private void addCardToDealerHand(LinearLayout hand) {
        for (int i = 0; i < dealer.cards.size(); i++) {
            ImageView newCard = new ImageView(PlayActivity.this);
            Card cardData = dealer.cards.get(i); //Card
            int id;

            id = getResourceId(PlayActivity.this, cardData.FileName);
            newCard.setImageResource(id);

            hand.addView(newCard);
        }
    }

    /**
     * Draws the top card from the deck, specified as either face up or face down, and  adds it to
     * the appropriate player's hand
     * @param hand the player whose hand to add the card to
     * @param currentPlayer the player object to add the card's data to
     * @param hidden whether the card should be drawn face down or not
     */
    private void addCardToHand(LinearLayout hand, BasePlayer currentPlayer, boolean hidden) {
        // Creating a new card to move to the player's hand
        // Adapted from:
        // https://stackoverflow.com/questions/2994494/how-do-i-create-an-imageview-in-java-code-within-an-existing-layout
        ImageView newCard = new ImageView(PlayActivity.this);
        Card cardData = currentPlayer.drawCard();
        int id;

        // Creating the card's visuals
        if (hidden) {
            cardData.toggleHidden();
        }

        id = getResourceId(PlayActivity.this, cardData.FileName);
        newCard.setImageResource(id);

        hand.addView(newCard);
    }

    /**
     * Draws the top card from the deck and adds it to the appropriate player's hand
     * @param hand the player whose hand to add the card to
     * @param currentPlayer the basePlayer object to add the card's data to
     */
    private void addCardToHand(LinearLayout hand, BasePlayer currentPlayer) {
        addCardToHand(hand, currentPlayer, false);
    }

    /**
     * Updates the specified player's score on screen
     * @param score the textView to be updated
     * @param currentPlayer the basePlayer object whose score will be read
     */
    private void updateScore(TextView score, BasePlayer currentPlayer) {
        currentPlayer.updateScore();
        String newScore = String.format("Score: %s", currentPlayer.displayedScore);
        score.setText(newScore);
    }

    /**
     * Transitions to the appropriate screen depending on whether the player won or not
     */
    private void endRound() {
        Intent intent;
        boolean playerWon = didPlayerWin();

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
            applyScoreMultipliers(player.actualScore);

            intent = new Intent(PlayActivity.this, HandWonActivity.class);
        }
        else {
            intent = new Intent(PlayActivity.this, HandLostActivity.class);
        }

        startActivity(intent);
    }

    public void applyScoreMultipliers(int finalScore) {
        finalScore *= bullseyeMultiplier;
        player.OverallScore += finalScore;
    }

    /**
     * Determines whether the player won or not
     * @return a boolean indicating whether the player won
     */
    private boolean didPlayerWin() {
        if (player.actualScore > 21){
            return false;
        }
        else if (dealer.actualScore > 21){
            return true;
        }
        else{
            return player.actualScore >= dealer.actualScore;
        }
    }

    /**
     * Finds the specified resource name and returns it as an id
     * @param context the context whose resources will be searched through
     * @param name the name of the resource
     * @return the id of the resource found
     */
    private int getResourceId(Context context, String name) {
        /*
        Using the implementation described here:
        https://stackoverflow.com/questions/16369814/how-to-access-the-drawable-resources-by-name-in-android#comment23457549_16369892
         */
        Resources resources = context.getResources();
        int id = resources.getIdentifier(name, "drawable", context.getPackageName());
        return id;
    }

}

