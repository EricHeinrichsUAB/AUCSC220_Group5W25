package com.turbo21;

import java.util.ArrayList;

public class Shop {

    public ArrayList<Item> ItemsCatelogue;
    private PlayActivity playActivity;

    public Shop(PlayActivity playActivity) {
        this.playActivity = playActivity;
        ItemsCatelogue = new ArrayList<>(3);
        ItemsCatelogue.add(new SwitchStrike(playActivity));
        ItemsCatelogue.add(new Bullseye());
        ItemsCatelogue.add(new DoubleDown(playActivity));
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