package com.turbo21;

import java.util.ArrayList;

public class Bullseye extends Item {

    PlayActivity playActivity;

    public Bullseye(PlayActivity activity) {
        super("Bullseye", "Gives bonus points if you end with 19-21.", 100);
        this.playActivity = activity;
    }

    @Override
    public void UseItem(int score) {
        switch(score) {
            case 19:
                playActivity.applyScoreMultipliers(score, 1.5);
                break;
            case 20:
                playActivity.applyScoreMultipliers(score, 2);
                break;
            case 21:
                playActivity.applyScoreMultipliers(score, 3);
                break;
        }
    }

}
