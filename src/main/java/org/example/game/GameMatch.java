package org.example.game;

import org.example.boardGame.Board;
import org.example.boardGame.Piece;
import org.example.boardGame.Position;
import org.example.game.pieces.BombPiece;
import org.example.game.pieces.HeavyPiece;
import org.example.game.pieces.LongReachPiece;

public class GameMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;

    public GameMatch() {
        board = new Board(15, 15);
        turn = 1;
        currentPlayer = Color.RED;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
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

    public boolean[][] possibleMoves(GamePosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public GameTankPiece performGameMove(GamePosition sourcePosition, GamePosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);

        Piece attackedPiece = makeMove(source, target);

        nextTurn();

        return (GameTankPiece) attackedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece attackedPiece = board.removePiece(target);
        board.placePiece(p, target);

        return attackedPiece;
    }

    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) {
            throw new GameException("There is no piece on source position");
        }
        if(currentPlayer != ((GameTankPiece) board.piece(position)).getColor()) {
            throw new GameException("The chosen piece is not yours");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new GameException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if(!board.piece(source).possibleMove(target)) {
            throw new GameException("The chosen piece can't move to target position");
        }
    }

    private void nextTurn(){
        turn++;

        // if the current player is red then the color will be blue, else the color will be red
        currentPlayer = (currentPlayer == Color.RED) ? Color.BLUE : Color.RED;
    }

    private void placeNewPiece(char column, int row, GameTankPiece piece) {
        board.placePiece(piece, new GamePosition(column, row).toPosition());
    }

    private void initialSetup(){
        placeNewPiece('d', 1, new BombPiece(board, Color.RED));
        placeNewPiece('h', 1, new HeavyPiece(board, Color.RED));
        placeNewPiece('l', 1, new LongReachPiece(board, Color.RED));

        placeNewPiece('d', 15, new BombPiece(board, Color.BLUE));
        placeNewPiece('h', 15, new HeavyPiece(board, Color.BLUE));
        placeNewPiece('l', 15, new LongReachPiece(board, Color.BLUE));
    }
}
