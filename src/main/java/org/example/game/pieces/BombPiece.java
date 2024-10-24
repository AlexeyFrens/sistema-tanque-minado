package org.example.game.pieces;

import org.example.boardGame.Board;
import org.example.game.Color;
import org.example.game.GameTankPiece;

public class BombPiece extends GameTankPiece {
    public BombPiece(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return matrix;
    }
}
