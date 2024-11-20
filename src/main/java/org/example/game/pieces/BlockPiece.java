package org.example.game.pieces;

import org.example.boardGame.Board;
import org.example.game.Color;
import org.example.game.GameTankPiece;

public class BlockPiece extends GameTankPiece {
    public BlockPiece(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[0][];
    }

    @Override
    public boolean[][] possibleShots() {
        return new boolean[0][];
    }

    @Override
    public String toString() {
        return "#";
    }
}
