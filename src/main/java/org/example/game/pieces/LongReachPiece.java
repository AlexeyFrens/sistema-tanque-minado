package org.example.game.pieces;

import org.example.boardGame.Board;
import org.example.boardGame.Position;
import org.example.game.Color;
import org.example.game.GameTankPiece;

public class LongReachPiece extends GameTankPiece {

    private final int movementLimit = 1;

    public LongReachPiece(Board board, Color color) {
        super(board, color);
    }

    public int getMovementLimit() {
        return movementLimit;
    }

    @Override
    public String toString() {
        return "L";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        int n = 1;

        // Above
        p.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= movementLimit) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
            n++;
        }

        n = 1;

        // Left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= movementLimit) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
            n++;
        }

        n = 1;

        // Right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= movementLimit) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
            n++;
        }

        n = 1;

        // Below
        p.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= movementLimit) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
            n++;
        }

        n = 1;

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
