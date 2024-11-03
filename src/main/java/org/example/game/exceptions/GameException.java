package org.example.game.exceptions;

import org.example.boardGame.BoardException;

public class GameException extends BoardException {
    public GameException(String message) {
        super(message);
    }
}
