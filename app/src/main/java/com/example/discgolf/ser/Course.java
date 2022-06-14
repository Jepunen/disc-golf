package com.example.discgolf.ser;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Course implements Serializable {

    String name;
    int amountOfHoles;
    ArrayList<Fairway> savedPars;
    ArrayList<Player> players;
    String date;
    boolean isExpanded;

    public Course(String name, int amountOfHoles, ArrayList<Fairway> savedPars, ArrayList<Player> players) {
        this.name = name;
        this.amountOfHoles = amountOfHoles;
        this.savedPars = savedPars;
        this.players = players;
        Calendar cal = Calendar.getInstance(); // Save as object created
        @SuppressLint("SimpleDateFormat") SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        this.date = date.format(cal.getTime());
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
    public boolean isExpanded() {
        return isExpanded;
    }
    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
    public String getDate() {
        return this.date;
    }
}
