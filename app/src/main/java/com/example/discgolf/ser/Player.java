package com.example.discgolf.ser;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    String name;
    ArrayList<Fairway> fairways;

    public Player(String name, ArrayList<Fairway> fairways) {
        this.name = name;
        this.fairways = fairways;
    }

    public String getName() {
        return name;
    }
    public ArrayList<Fairway> getFairways() {
        return fairways;
    }
}
