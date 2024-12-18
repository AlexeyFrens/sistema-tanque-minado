package org.example.boardGame;

import org.example.game.pieces.BlockPiece;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be at least one row and column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    // The 2 methods below return the piece that is in a certain position on the board

    public Piece piece(int row, int column) {
        if(!positionExists(row, column)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        if(!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        if(piece(position) == null){
            return null;
        }
        Piece pieceToBeRemoved = piece(position);
        pieceToBeRemoved.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return pieceToBeRemoved;
    }

    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if(!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }
}
