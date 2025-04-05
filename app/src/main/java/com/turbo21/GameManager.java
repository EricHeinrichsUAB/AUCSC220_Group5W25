
package com.turbo21;

import java.util.Random;

public class GameManager {
    private static int RoundNumber;
    private static boolean IsDealersTurn;
    public static Random RandomNumberGenerator = new Random();
    public static Deck Deck = new Deck();
    private static Shop Shop;
    private static BasePlayer Dealer;
    private static Player Player;

    /**
     * Initializes game variables for the start of a new game, then transitions the view to
     * the main game activity and begins the primary game loop
     */
    public static void StartGame() {
        RoundNumber = 1;
        IsDealersTurn = false;
        Dealer = new BasePlayer();
        Player = new Player();
    }


    /**
     * Handles all of the logic for the Dealer's turn
     */
    public static void DoDealersTurn() {
        // General flow will be as follows:
        // Dealer compares their score to the players -> stop if greater
        // Dealer checks that their score is not over 18 -> stop if it is
        // Dealer draws a card

        // Optionally, have the dealer keep track of every card that has been seen up to this point
        // and have it make its decision based on the probability that it will not go over

        // easy CPU
        // when player>CPU (and CPU<18), 30% chance that it will stand
        // when CPU=18, 50% chance that it will hit

        // medium CPU <- default
        // when player>CPU (and CPU<18), 15% chance that it will stand
        // when CPU=18, 30% chance that it will hit

        // hard CPU
        // when player>CPU (and CPU<18), 5% chance that it will stand
        // when CPU=18, 15% chance that it will hit

        if (Dealer.score <= 18) {
            Dealer.drawCard();
        }

    }

    /**
     * Removes the top card from the deck and adds it to the corresponding player's hand,
     * depending on whose turn it is.
     */
    public static void DoHitButton() {
        Player.drawCard();
    }

    /**
     * Ends the current player's turn and switches to the other player
     */
    public static void DoStandButton() {
        IsDealersTurn = !IsDealersTurn;

        if (IsDealersTurn) {
            DoDealersTurn();
        }
    }
}

