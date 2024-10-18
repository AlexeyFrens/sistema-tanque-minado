package org.example.game;

import org.example.boardGame.Board;
import org.example.boardGame.Position;

public class GameMatch {
    private Board board;

    public GameMatch() {
        board = new Board(9, 9);
        initialSetup();
    }

    public GameTankPiece[][] getPieces() {
        GameTankPiece[][] mat = new GameTankPiece[board.getRows()][board.getColumns()];
        for(int i = 0; i < board.getRows(); i++) {
            for(int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (GameTankPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    private void initialSetup(){
        board.placePiece(new TankPiece(board, Color.RED), new Position(8, 1));
        board.placePiece(new TankPiece(board, Color.RED), new Position(8, 4));
        board.placePiece(new TankPiece(board, Color.RED), new Position(8, 7));

        board.placePiece(new TankPiece(board, Color.BLUE), new Position(0, 1));
        board.placePiece(new TankPiece(board, Color.BLUE), new Position(0, 4));
        board.placePiece(new TankPiece(board, Color.BLUE), new Position(0, 7));
    }
}
