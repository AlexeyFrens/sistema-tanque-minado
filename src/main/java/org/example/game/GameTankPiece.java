package org.example.game;

import org.example.boardGame.Board;
import org.example.boardGame.Piece;

public abstract class GameTankPiece extends Piece {
    private Color color;

    public GameTankPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
