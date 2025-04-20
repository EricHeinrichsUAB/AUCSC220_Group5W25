package com.turbo21;

public class SwitchStrike extends Item {

    PlayActivity playActivity;

    public SwitchStrike(PlayActivity activity) {
        super("SwitchStrike", "Swaps the player's and dealer's hands.", 300);
        this.playActivity = activity;
    }

    @Override
    public void UseItem(int score) {
        //
    }

}
