package com.turbo21;

public class DoubleDown extends Item {

    PlayActivity playActivity;

    public DoubleDown(PlayActivity activity) {
        super("DoubleDown", "Player can double the stakes before hitting.", 200);
        this.playActivity = activity;
    }

    public void UseItem(int score) {
        //
    }
}
