package com.Othello.FileHandler;

import com.Othello.FileHandler.Interfaces.ISaveHandler;
import com.Othello.Game.History;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveHandler implements ISaveHandler {
    public void serializeHistory(History history, String file){
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try{
            fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(history);
            System.out.println("File saved!");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(fout != null){
                try{
                    fout.close();
                }catch (IOException ioex){
                    ioex.printStackTrace();
                }
            }
            if(oos != null){
                try{
                    oos.close();
                } catch (IOException ioex){
                    ioex.printStackTrace();
                }
            }
        }
    }
}

