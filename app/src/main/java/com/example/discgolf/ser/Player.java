package com.example.discgolf.ser;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    String name;
    int totalScore = 0;
    ArrayList<Fairway> fairways;

    public Player(String name, ArrayList<Fairway> fairways) {
        this.name = name;
        this.fairways = fairways;
        for ( Fairway fairway : fairways ) {
            if ( fairway.getThrowsTaken() != 0 ) {
                totalScore += fairway.getThrowsTaken() - (fairway.getPar() + 2);
            }
        }
    }

    public String getName() {
        return name;
    }
    public ArrayList<Fairway> getFairways() {
        return fairways;
    }
    public Integer getTotalScore() {
        return totalScore;
    }
}
