package com.turbo21;

import java.util.ArrayList;

public class Shop {

    public ArrayList<Item> Items;

    public Shop() {
        ArrayList<Item> Items = new ArrayList<>(3);
    }

    public void SellItem(int intZeroToTwo) { //changed to void for now
        //return Items[intZeroToTwo];
    }

    public void RefreshItems() {

    }


}