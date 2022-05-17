package com.example.discgolf.ser;

import com.example.discgolf.ser.Fairway;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    String name;
    ArrayList<Fairway> fairways;

}
