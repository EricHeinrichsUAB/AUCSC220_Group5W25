package com.turbo21;

import static java.util.Map.entry;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Card {
    public String Rank;
    public String Suit;
    public int Value;

    // Using the initialization method described here:
    // https://stackoverflow.com/questions/6802483/how-to-directly-initialize-a-hashmap-in-a-literal-way
    private static final Map<String, Integer> ValueLookup = Map.ofEntries(
            entry("2", 2),
            entry("3", 3),
            entry("4", 4),
            entry("5", 5),
            entry("6", 6),
            entry("7", 7),
            entry("8", 8),
            entry("9", 9),
            entry("10", 10),
            entry("Jack", 10),
            entry("Queen", 10),
            entry("King", 10),
            entry("Ace", 11)
    );
    public Card(String rank, String suit) {
        this.Rank = rank;
        this.Suit = suit;
        this.Value = ValueLookup.get(rank);
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.Rank, this.Suit);
    }
}
