package org.example.game;

import org.example.boardGame.Board;
import org.example.boardGame.Piece;
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

    public GameTankPiece performGameMove(GamePosition sourcePosition, GamePosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);

        return (GameTankPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        return capturedPiece;
    }

    public void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) {
            throw new GameException("There is no piece on source position");
        }
    }

    private void placeNewPiece(char column, int row, GameTankPiece piece) {
        board.placePiece(piece, new GamePosition(column, row).toPosition());
    }

    private void initialSetup(){
        placeNewPiece('c', 1, new TankPiece(board, Color.RED));
        placeNewPiece('e', 1, new TankPiece(board, Color.RED));
        placeNewPiece('g', 1, new TankPiece(board, Color.RED));

        placeNewPiece('c', 9, new TankPiece(board, Color.BLUE));
        placeNewPiece('e', 9, new TankPiece(board, Color.BLUE));
        placeNewPiece('g', 9, new TankPiece(board, Color.BLUE));
    }
}
