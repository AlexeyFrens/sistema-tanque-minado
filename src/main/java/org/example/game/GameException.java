package org.example.game;

import org.example.boardGame.BoardException;

public class GameException extends BoardException {
    public GameException(String message) {
        super(message);
    }
}
