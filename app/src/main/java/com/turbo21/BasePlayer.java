package com.turbo21;

import java.util.ArrayList;

public class BasePlayer {
    public ArrayList<Card> cards;
    public int score;

    public BasePlayer() {
        this.cards = new ArrayList<>();
    }

    /**
     * Draws the top card from the deck and adds it to the Player's hand
     */
    public Card drawCard() {
        Card card = GameManager.Deck.DrawCard();
        this.drawCard(card);
        return card;
    }

    /**
     * Takes a Card as input and adds it to the Player's hand
     * @param card the Card to add to the Player's hand
     */
    public void drawCard(Card card) {
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

    /**
     * Counts the values of all cards in the Player's hand and returns the sum
     * @return the sum of the value of every card in the Player's hand
     */
    private int countScore() {
        int sum = 0;
        for (Card card : this.cards) {
            sum += card.Value;
        }

        return sum;
    }

    /**
     * Resets the Player's score to 0 and removes all cards from their hand
     */
    public void resetScore() {
        this.score = 0;
        this.cards.clear();
    }
}
