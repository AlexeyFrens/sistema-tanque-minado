package org.example.game;

import org.example.boardGame.Board;

public class TankPiece extends GameTankPiece {
    public TankPiece(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "T";
    }
}
