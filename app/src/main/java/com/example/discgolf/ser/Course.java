package com.example.discgolf.ser;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

    String name;
    int amountOfHoles;
    ArrayList<Fairway> savedPars;
    ArrayList<Player> players;

    public Course(String name, int amountOfHoles, ArrayList<Fairway> savedPars, ArrayList<Player> players) {
        this.name = name;
        this.amountOfHoles = amountOfHoles;
        this.savedPars = savedPars;
        this.players = players;
    }

    public Course() {
        this.name = "Create a new course";
    }

    public Course(String name, int amountOfHoles, ArrayList<Fairway> savedPars) {
        this.name = name;
        this.amountOfHoles = amountOfHoles;
        this.savedPars = savedPars;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAmountOfHoles() {
        return amountOfHoles;
    }
    public void setAmountOfHoles(int amountOfHoles) {
        this.amountOfHoles = amountOfHoles;
    }
    public ArrayList<Fairway> getSavedPars() {
        return savedPars;
    }
    public void setSavedPars(ArrayList<Fairway> savedPars) {
        this.savedPars = savedPars;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
