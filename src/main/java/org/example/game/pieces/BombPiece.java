package org.example.game.pieces;

import org.example.boardGame.Board;
import org.example.boardGame.Position;
import org.example.game.Color;
import org.example.game.GameTankPiece;

public class BombPiece extends GameTankPiece {
    public BombPiece(Board board, Color color) {
        super(board, color);
        movementLimit = 1;
        reach = 4;
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

    @Override
    public boolean[][] possibleShots() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        int n = 1;

        // Above
        p.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= reach) {
            matrix[p.getRow()][p.getColumn()] = false;
            p.setRow(p.getRow() - 1);
            n++;
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        n = 1;

        // Left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= reach) {
            matrix[p.getRow()][p.getColumn()] = false;
            p.setColumn(p.getColumn() - 1);
            n++;
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        n = 1;

        // Right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= reach) {
            matrix[p.getRow()][p.getColumn()] = false;
            p.setColumn(p.getColumn() + 1);
            n++;
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        n = 1;

        // Below
        p.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && n <= reach) {
            matrix[p.getRow()][p.getColumn()] = false;
            p.setRow(p.getRow() + 1);
            n++;
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        return matrix;
    }
}
