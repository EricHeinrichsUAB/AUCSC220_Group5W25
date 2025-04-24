package com.turbo21;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameManagerTest {
    @Test
    public void TestStartGame() {
        GameManager.StartGame();

        // Testing that the dealer, player, and round numbers are initialized properly
        assertNotNull(GameManager.Dealer);
        assertNotNull(GameManager.Player);
        assertEquals(1, GameManager.RoundNumber);
    }

    @Test
    public void TestStartRound() {
        GameManager.StartGame();
        int previousRoundNumber = GameManager.RoundNumber;

        // Makes the scores non-zero
        GameManager.Dealer.drawCard();
        GameManager.Player.drawCard();

        GameManager.StartRound();

        // Testing that scores are reset, the turn order is reset, and that the round number
        // was incremented properly
        assertEquals(0, GameManager.Dealer.actualScore);
        assertEquals(0, GameManager.Player.actualScore);
        assertFalse(GameManager.IsDealersTurn);
        assertTrue(GameManager.RoundNumber > previousRoundNumber);
    }

    @Test
    public void TestIsStillDealersTurn() {
        GameManager.StartGame();

        Card card1 = new Card("Ace", "Spades");
        Card card2 = new Card("King", "Spades");

        // Dealer score = 11, Player score = 10
        GameManager.Dealer.drawCard(card1);
        GameManager.Player.drawCard(card2);

        // Testing Dealer score > Player score
        assertFalse(GameManager.IsStillDealersTurn());

        // Reset scores
        GameManager.StartRound();

        // Dealer score = 11, Player Score = 21
        GameManager.Dealer.drawCard(card1);
        GameManager.Player.drawCard(card1);
        GameManager.Player.drawCard(card2);

        // Testing !(Dealer score > Player score) && (Dealer score < 18)
        assertTrue(GameManager.IsStillDealersTurn());
        GameManager.StartRound();

        // Dealer score = 20, Player score = 21
        GameManager.Dealer.drawCard(card2);
        GameManager.Dealer.drawCard(card2);
        GameManager.Player.drawCard(card1);
        GameManager.Player.drawCard(card2);

        // Testing !(Dealer Score > Player Score) && !(Dealer Score < 18)
        assertFalse(GameManager.IsStillDealersTurn());
    }
}
