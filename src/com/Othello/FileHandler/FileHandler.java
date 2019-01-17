package com.Othello.FileHandler;

import com.Othello.FileHandler.Interfaces.ILoadHandler;
import com.Othello.FileHandler.Interfaces.ISaveHandler;
import com.Othello.Game.History;

import java.io.File;

public class FileHandler {
    private ILoadHandler _loadHanler;
    private ISaveHandler _saveHandler;
    private final String _saveDirName = "saves";
    private File _savesDir;
    private static FileHandler _fileHandlerInstance = null;

    public static FileHandler getFileHandlerInstance(){
        if(_fileHandlerInstance == null)
            _fileHandlerInstance = new FileHandler();
        return _fileHandlerInstance;
    }

    private FileHandler(){
        _loadHanler = new LoadHandler();
        _saveHandler = new SaveHandler();
        _savesDir  = new File(_saveDirName);
        try {

            if(!_savesDir.exists())
                _savesDir.mkdir();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void saveGame(History history,String file){
        _saveHandler.serializeHistory(history, file);
    }
    public History getSavedGame(String file){
        return _loadHanler.deserializeHistory(file);
    }
}
