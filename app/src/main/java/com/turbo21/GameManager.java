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

    public static void StartGame() {
        RoundNumber = 1;
        IsDealersTurn = false;
        Dealer = new BasePlayer();
        Player = new Player();

        return;
    }

    private static void DoDealersTurn() {
        // General flow will be as follows:
        // Dealer compares their score to the players -> stop if greater
        // Dealer checks that their score is not over 18 -> stop if it is
        // Dealer draws a card

        // Optionally, have the dealer keep track of every card that has been seen up to this point
        // and have it make its decision based on the probability that it will not go over
    }

    public static void DoHitButton(BasePlayer player) {
        player.drawCard();
    }

    public static void DoStandButton() {
        IsDealersTurn = !IsDealersTurn;

        if (IsDealersTurn) {
            DoDealersTurn();
        }
    }
}
