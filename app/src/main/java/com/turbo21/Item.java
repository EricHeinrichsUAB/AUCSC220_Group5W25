package com.turbo21;

public abstract class Item {

    public String name;
    public String description;
    public int cost;

    public Item(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
    public abstract void UseItem();
}
