package com.turbo21;

import java.util.ArrayList;

public class BasePlayer {
    public ArrayList<Card> cards;
    public int score;

    public BasePlayer() {
        this.cards = new ArrayList<>();
    }

    public void takeCard() {
        Card card = GameManager.Deck.DrawCard();
        this.takeCard(card);
    }

    public void takeCard(Card card) {
        this.cards.add(card);

        this.handleAces();
        this.score = this.countScore();
    }

    /**
     Looks for Aces in the player's cards and adjusts their value to either 1 or 11, whichever is
     the most beneficial. For example, a hand consisting of a King and an Ace is 21, and a hand
     consisting of a King, a Queen, and an Ace is also 21.
     */
    private void handleAces() {
        ArrayList<Card> aces = new ArrayList<>();

        // Gets every Ace in the Player's hand
        for (Card card : this.cards) {
            if (card.Rank.equals("Ace")) aces.add(card);
        }

        while (!aces.isEmpty()) {
            int processingScore = this.countScore();

            if (processingScore > 21) {
                aces.get(0).Value = 1;
            }

            aces.remove(0);
        }
    }

    private int countScore() {
        int sum = 0;
        for (Card card : this.cards) {
            sum += card.Value;
        }

        return sum;
    }
}
