package com.turbo21;

import java.util.ArrayList;

public class Bullseye extends Item {

    double multiplier;

    public Bullseye() {
        super("Bullseye", "Gives bonus points if you end with 19-21.", 100);
    }

    public void UseItem(int finalScore) {
        switch(finalScore) {
            case 19:
                multiplier = 1.5;
                break;
            case 20:
                multiplier = 2;
                break;
            case 21:
                multiplier = 3;
                break;
        }
    }

}
