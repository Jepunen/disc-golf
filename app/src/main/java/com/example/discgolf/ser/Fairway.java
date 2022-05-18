package com.example.discgolf.ser;

import java.io.Serializable;

public class Fairway implements Serializable {

    int par;
    int throwsTaken;
    int length;

    public Fairway(int par) {
        this.par = par;
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
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
}
