package com.turbo21;

import java.util.ArrayList;

public class Shop {

    public ArrayList<Item> ItemsCatelogue;

    public Shop() {
        ItemsCatelogue = new ArrayList<>(3);
        ItemsCatelogue.add(new SwitchStrike());
        ItemsCatelogue.add(new Bullseye());
        //ItemsCatelogue.add(new DoubleDown());
    }

    // Shop distributes the item (it has an unlimited quantity)
    public Item SellItem(int index) {
        if (ItemsCatelogue != null && index >= 0 && index < ItemsCatelogue.size()) {
            return ItemsCatelogue.get(index);
        } else {
            return null;
        }
    }

    public void RefreshItems () {
        //
    }


}