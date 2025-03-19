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

        // Creating each card and adding it to the stack
        for (String suit : Suits) {
            for (String rank : Ranks) {
                Card card = new Card(rank, suit);
                this.Cards.push(card);
            }
        }

        this.Shuffle();
    }

    public Card DrawCard() {
        this.Count -= 1;
        return this.Cards.pop();
    }

    public void Shuffle() {

        Card[] cards = this.RemoveCardsFromStack();
        this.Cards.clear();

        for (int i = 0; i < cards.length; i++) {
            int randInt = new Random().nextInt(cards.length);

            Card card1 = (Card) cards[i];
            Card card2 = (Card) cards[randInt];

            cards[i] = card2;
            cards[randInt] = card1;
        }

        this.AddCardsToStack(cards);
    }

    private Card[] RemoveCardsFromStack() {
        Card[] cards = new Card[this.Cards.capacity()];

        for (int i = 0; i < this.Cards.capacity(); i++) {
            cards[i] = this.Cards.pop();
        }

        return cards;
    }

    private void AddCardsToStack(Card[] cards) {
        for (Card card : cards) {
            this.Cards.push(card);
        }
    }
}
