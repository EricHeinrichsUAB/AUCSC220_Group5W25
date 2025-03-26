package com.turbo21;

import java.util.ArrayList;

public class Player extends BasePlayer {
    public int Money;
    public int OverallScore;
    public ArrayList<Item> Items;

    public Player() {
        super();
        this.Money = 0;
        this.OverallScore = 0;
        this.Items = new ArrayList<>(3);
    }

    /**
     * Applies any modifiers to the specified amount of money and then gives it to the player
     * @param amount the base amount of money to be given to the Player
     */
    public void addMoney(int amount, String[] args) {
        // Handle any modifiers here

        this.Money += amount;
    }

    /**
     * Adds the specified item to the Player's item list
     * @param item the item to be added
     */
    public void addItem(Item item) {
        this.Items.add(item);
    }

}
