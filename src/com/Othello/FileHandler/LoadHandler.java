package com.Othello.FileHandler;

import com.Othello.FileHandler.Interfaces.ILoadHandler;
import com.Othello.Game.History;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadHandler implements ILoadHandler{
    public History deserializeHistory(String file){
        History history = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try{
         fin = new FileInputStream(file);
         ois = new ObjectInputStream(fin);
         history = (History)ois.readObject();
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            }

        }
        return  history;
    }
}
