package com.turbo21;

import java.util.ArrayList;

public class Shop {

    public ArrayList<Item> Items;

    public Shop() {
        ArrayList<Item> Items = new ArrayList<>(3);
    }

    public Item SellItem(int intZeroToTwo) {
        return Items.get(intZeroToTwo);
    }

    public void RefreshItems () {
        //
    }


}