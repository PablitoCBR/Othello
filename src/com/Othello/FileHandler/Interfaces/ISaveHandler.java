package com.Othello.FileHandler.Interfaces;

import com.Othello.Game.History;

public interface ISaveHandler {
    void serializeHistory(History history, String file);
}
