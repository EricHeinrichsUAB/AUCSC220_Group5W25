package com.turbo21;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasePlayerTest {
    @Test
    public void TestDrawCard() {
        // Testing for giving cards to the player and counting score properly
        BasePlayer testPlayer1 = new BasePlayer();

        Card testCard1 = new Card("Ace", "Spades");
        testPlayer1.drawCard(testCard1);
        assertEquals(testCard1, testPlayer1.cards.get(0));
        assertEquals(11, testPlayer1.actualScore);

        // Testing the same as above with more cards
        Card testCard2 = new Card("Ace", "Diamonds");
        testPlayer1.drawCard(testCard2);
        assertEquals(testCard2, testPlayer1.cards.get(1));
        assertEquals(12, testPlayer1.actualScore);

        // Testing taking cards in a different order to ensure aces are being handled properly
        BasePlayer testPlayer2 = new BasePlayer();
        testPlayer2.drawCard(new Card("Queen", "Diamonds"));
        testPlayer2.drawCard(new Card("6", "Clubs"));
        testPlayer2.drawCard(new Card("Ace", "Hearts"));
        assertEquals(17, testPlayer2.actualScore);

        // Testing drawing a card directly from the deck
        BasePlayer testPlayer3 = new BasePlayer();
        assertThrows(IndexOutOfBoundsException.class, () -> testPlayer3.cards.get(0));
        testPlayer3.drawCard();
        assertNotNull(testPlayer3.cards.get(0));
    }

    @Test
    public void TestUpdateScore() {
        BasePlayer testPlayer1 = new BasePlayer();

        // Testing that scores are initialized properly
        assertEquals(0, testPlayer1.actualScore);
        assertEquals(0, testPlayer1.displayedScore);

        // Testing that scores are updated independently
        Card testCard1 = new Card("Ace", "Spades");
        testCard1.toggleHidden();
        testPlayer1.drawCard(testCard1);
        testPlayer1.updateScore();

        assertEquals(11, testPlayer1.actualScore);
        assertEquals(0, testPlayer1.displayedScore);
    }

    @Test
    public void TestResetScore() {
        BasePlayer testPlayer1 = new BasePlayer();

        testPlayer1.drawCard();

        assertNotEquals(0, testPlayer1.actualScore);
        assertNotNull(testPlayer1.cards.get(0));

        testPlayer1.resetScore();

        assertEquals(0, testPlayer1.actualScore);
        assertThrows(IndexOutOfBoundsException.class, () -> testPlayer1.cards.get(0));
    }
}
