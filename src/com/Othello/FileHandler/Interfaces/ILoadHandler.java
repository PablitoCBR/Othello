package com.Othello.FileHandler.Interfaces;

import com.Othello.Game.History;

public interface ILoadHandler {
    History deserializeHistory(String file);
}
