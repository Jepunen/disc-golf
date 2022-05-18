package com.example.discgolf.ser;

import java.io.Serializable;

public class Fairway implements Serializable {

    int fairwayNumber; // 0-17
    int par;
    int throwsTaken;
    int length;

    public Fairway(int par) {
        this.par = par;
    }

    public Fairway(int fairwayNumber, int par, int throwsTaken, int length) {
        this.fairwayNumber = fairwayNumber;
        this.par = par;
        this.throwsTaken = throwsTaken;
        this.length = length;
    }

    public int getPar() {
        return par;
    }
    public void setPar(int par) {
        this.par = par;
    }
    public int getThrowsTaken() {
        return throwsTaken;
    }
    public void setThrowsTaken(int throwsTaken) {
        this.throwsTaken = throwsTaken;
    }
    public void addThrows(int amount) {
        this.throwsTaken = this.throwsTaken + amount;
    }
    public void removeThrows(int amount) {
        this.throwsTaken = this.throwsTaken - amount;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getFairwayNumber() {
        return fairwayNumber;
    }
    public void setFairwayNumber(int fairwayNumber) {
        this.fairwayNumber = fairwayNumber;
    }
}
