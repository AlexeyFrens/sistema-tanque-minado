package org.example.game;

import org.example.boardGame.Board;
import org.example.boardGame.Piece;

public class TankPiece extends GameTankPiece {
    public TankPiece(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "T";
    }
}
