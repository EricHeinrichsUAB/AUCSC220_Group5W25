package com.turbo21;

import java.util.ArrayList;

public class BasePlayer {
    public ArrayList<Card> cards;
    public int score;

    public BasePlayer() {

    }

    public void takeCard() {
        Card card = GameManager.Deck.DrawCard();
        this.cards.add(card);

        // Special logic for Aces since they can be 11's or 1's
        // Whichever is more beneficial

        this.score += card.Value;
    }

    private void handleAce() {

    }
}
