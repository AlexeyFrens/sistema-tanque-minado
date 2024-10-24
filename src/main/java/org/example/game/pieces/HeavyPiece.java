package org.example.game.pieces;

import org.example.boardGame.Board;
import org.example.game.Color;
import org.example.game.GameTankPiece;

public class HeavyPiece extends GameTankPiece {

    public HeavyPiece(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "H";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return matrix;
    }
}
