
package com.turbo21;

import java.util.Random;

public class GameManager {
    public static int RoundNumber;
    public static boolean IsDealersTurn;
    public static Random RandomNumberGenerator = new Random();
    public static Deck Deck = new Deck();
    private static Shop Shop;
    public static BasePlayer Dealer;
    public static Player Player;
    public static String Difficulty = "medium";


    /**
     * Initializes game variables for the start of a new game, then transitions the view to
     * the main game activity and begins the primary game loop
     */
    public static void StartGame() {
        RoundNumber = 0;
        Dealer = new BasePlayer();
        Player = new Player();

        StartRound();
    }

    public static void StartRound() {
        RoundNumber++;
        IsDealersTurn = false;

        Dealer.resetScore();
        Player.resetScore();
    }


    /**
     * Handles all of the logic for the Dealer's turn
     */
    public static boolean IsStillDealersTurn() {
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

        boolean stillDealersTurn;


        int dealerDifficultyInt;

        switch (Difficulty) {
            case "easy":
                dealerDifficultyInt = 16;
                break;
            case "hard":
                dealerDifficultyInt = 18;
                break;
            default: /* medium is default */
                dealerDifficultyInt = 17;
                break;
        }

        if (Dealer.actualScore > Player.actualScore) {
            stillDealersTurn = false;
        }
        else if (Dealer.actualScore < dealerDifficultyInt) {
            stillDealersTurn = true;
        }
        else {
            stillDealersTurn = false;
        }

        return stillDealersTurn;
    }
}

