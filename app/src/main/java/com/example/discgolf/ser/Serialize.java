package com.example.discgolf.ser;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize {

    public static final Serialize instance = new Serialize();
    // Data -> File
    public void serializeData(Context c, String fileName, Object data) {
        try {
            FileOutputStream fout = c.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oOut = new ObjectOutputStream(fout);
            oOut.writeObject(data);
            oOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // File -> Data
    public <T> T deSerializeData(Context c, String fileName) {
        try {
            FileInputStream fIn = c.openFileInput(fileName);
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            T data = (T) oIn.readObject();
            oIn.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}