package com.turbo21;

import java.util.Random;
import java.util.Stack;

public class Deck {
    private Stack<Card> Cards;
    private int Count;

    private final String[] Ranks = {"2", "3", "4", "5", "6", "7", "8","9",
                                    "10","Jack", "Queen", "King", "Ace"};

    private final String[] Suits = {"Spades", "Hearts", "Diamonds", "Clubs"};

    public Deck() {
        this.Count = 52;
        this.Cards = new Stack<>();


        // Creating cards of each rank and suit and adding it to the Deck
        for (String suit : Suits) {
            for (String rank : Ranks) {
                Card card = new Card(rank, suit);
                this.Cards.push(card);
            }
        }

        this.Shuffle();
    }

    /**
     * Removes the top Card from the Deck and returns it
     * @return the top Card from the Deck
     */
    public Card DrawCard() {
        this.Count -= 1;
        return this.Cards.pop();
    }

    /**
     * Randomizes the order of Cards within the Deck
     */
    public void Shuffle() {

        Card[] cardsShuffled = this.RemoveCardsFromStack();

        // Swaps the card at index i with another card at a randomly generated index
        for (int i = 0; i < cardsShuffled.length; i++) {
            int randInt = GameManager.RandomNumberGenerator.nextInt(cardsShuffled.length);

            Card card1 = cardsShuffled[i];
            Card card2 = cardsShuffled[randInt];

            cardsShuffled[i] = card2;
            cardsShuffled[randInt] = card1;
        }

        this.AddCardsToStack(cardsShuffled);
    }

    /**
     * Removes all cards from the stack and returns them as an array
     * @return an array of Cards in reverse order from the stack
     */
    private Card[] RemoveCardsFromStack() {
        Card[] cards = new Card[this.Cards.size()];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = this.Cards.pop();
        }

        return cards;
    }

    /**
     * Adds Cards from the input array to the stack
     * @param cards the array of Cards to add to the stack
     */
    private void AddCardsToStack(Card[] cards) {
        for (Card card : cards) {
            this.Cards.push(card);
        }
    }

    @Override
    public String toString() {
        return this.Cards.toString();

    }
}
