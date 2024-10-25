package org.example.game.pieces;

import org.example.boardGame.Board;
import org.example.boardGame.Position;
import org.example.game.Color;
import org.example.game.GameTankPiece;

public class BombPiece extends GameTankPiece {

    private final int movementLimit = 2;

    public BombPiece(Board board, Color color) {
        super(board, color);
    }

    public int getMovimentLimit() {
        return movementLimit;
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        int n = 1;

        // nw
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= movementLimit) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
            n++;
        }

        n = 1;

        // ne
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= movementLimit) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
            n++;
        }

        n = 1;

        // se
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= movementLimit) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn() + 1);
            n++;
        }

        n = 1;

        // sw
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= movementLimit) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
            n++;
        }

        return matrix;
    }
}