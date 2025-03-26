package com.turbo21;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {
    @Test
    public void TestDrawCard() {
        // Testing that the deck properly returns a card
        Deck testDeck = new Deck();
        Card testCard = new Card("Ace", "Spades");
        assertEquals(testCard.getClass(), testDeck.DrawCard().getClass());
    }

    @Test
    public void TestShuffle() {
        // Testing that the cards are not in the same order after being shuffled
        Deck testDeck = new Deck();
        String firstShuffle = testDeck.toString();
        testDeck.Shuffle();
        String secondShuffle = testDeck.toString();

        assertNotEquals(firstShuffle, secondShuffle);
    }
}
