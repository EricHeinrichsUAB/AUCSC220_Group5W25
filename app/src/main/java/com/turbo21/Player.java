package com.turbo21;

import java.util.ArrayList;

public class Player extends BasePlayer {
    public int money;
    public int overallScore;
    public int cardsDrawn;
    public ArrayList<Item> items;


    public Player() {
        super();
        this.money = 0;
        this.overallScore = 0;
        this.cardsDrawn = 0;
        this.items = new ArrayList<>(3);
    }

    /**
     * Applies any modifiers to the specified amount of money and then gives it to the player
     * @param amount the base amount of money to be given to the Player
     */
    public void addMoney(int amount, String[] args) {
        // Handle any modifiers here

        this.money += amount;
    }

    public void subtractMoney(int amount) {
        // Handle any modifiers here

        this.Money -= amount;
    }

    /**
     * Adds the specified item to the Player's item list
     * @param item the item to be added
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

}
