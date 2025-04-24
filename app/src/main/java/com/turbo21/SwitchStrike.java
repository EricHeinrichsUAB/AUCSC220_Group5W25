package com.turbo21;

import java.util.ArrayList;

public class SwitchStrike extends Item {

    ArrayList<Card> newPlayerHand;
    ArrayList<Card> newDealerHand;

    public SwitchStrike() {
        super("SwitchStrike", "Swaps the player's and dealer's hands.", 300);
    }

    public void UseItem(ArrayList<Card> dealerHand, ArrayList<Card> playerHand) {
        // Switches the two hands
        ArrayList<Card> tempHand = new ArrayList<>(dealerHand);
        dealerHand.clear();
        dealerHand.addAll(playerHand);
        playerHand.clear();
        playerHand.addAll(tempHand);

        newDealerHand = dealerHand;
        newPlayerHand = playerHand;
    }
}
