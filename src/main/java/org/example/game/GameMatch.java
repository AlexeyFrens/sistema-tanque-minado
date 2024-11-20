package org.example.game;

import org.example.boardGame.Board;
import org.example.boardGame.Piece;
import org.example.boardGame.Position;
import org.example.game.exceptions.GameException;
import org.example.game.exceptions.ShotException;
import org.example.game.pieces.BlockPiece;
import org.example.game.pieces.BombPiece;
import org.example.game.pieces.HeavyPiece;
import org.example.game.pieces.LongReachPiece;

import java.util.ArrayList;
import java.util.List;

public class GameMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;

    private List<GameTankPiece> piecesOnTheBoard = new ArrayList<>();
    private List<GameTankPiece> gamePieces = new ArrayList<>();

    public GameMatch() {
        board = new Board(15, 15);
        turn = 1;
        currentPlayer = Color.RED;
        initialSetup();
        startTimeThread();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public List<GameTankPiece> getGamePieces() {
        return gamePieces;
    }

    public GameTankPiece[][] getPieces() {
        GameTankPiece[][] mat = new GameTankPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (GameTankPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    private GameTankPiece[][] getBluePieces() {
        GameTankPiece[][] mat = new GameTankPiece[board.getRows()][board.getColumns()];
        GameTankPiece piece;

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                piece = (GameTankPiece) board.piece(i, j);

                if (piece != null && piece.getColor() == Color.BLUE) {
                    mat[i][j] = piece;
                }
            }
        }

        return mat;
    }

    private GameTankPiece[][] getRedPieces() {
        GameTankPiece[][] mat = new GameTankPiece[board.getRows()][board.getColumns()];
        GameTankPiece piece;

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                piece = (GameTankPiece) board.piece(i, j);

                if (piece != null && piece.getColor() == Color.RED) {
                    mat[i][j] = piece;
                }
            }
        }

        return mat;
    }

    private void startTimeThread() {
        Thread timerThread = new Thread(() -> {
            try{
                Thread.sleep(3 * 60 * 1000);
                refactorBoard();
                System.out.println("Time out! Reduced battlefield");
            }catch (InterruptedException e){
                System.out.println("Interrupted timer");
            }
        });

        timerThread.setDaemon(true);
        timerThread.start();
    }

    private void refactorBoard(){
        for(int i = 0; i < board.getRows(); i++){
            for(int j = 0; j < board.getColumns(); j++){
                if((i <= 2 || i >= 12) || (j <= 2 || j >= 12)){
                    if(board.thereIsAPiece(new Position(i, j))){
                        board.removePiece(new Position(i, j));
                    }
                    board.placePiece(new BlockPiece(board, Color.YELLOW), new Position(i, j));
                }
            }
        }
    }

    public boolean[][] possibleMoves(GamePosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public boolean[][] possibleShots(GamePosition sourcePosition) {
        try {
            Position position = sourcePosition.toPosition();
            validateSourcePositionToShot(position);
            return board.piece(position).possibleShots();
        } catch (Exception e) {
            throw new ShotException(e.getMessage());
        }
    }

    public void performGameMove(GamePosition sourcePosition, GamePosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);
        makeMove(source, target);
    }

    private void makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        board.placePiece(p, target);
    }

    public GameTankPiece performGameShot(GamePosition sourcePosition, GamePosition targetPosition) {
        try {
            Position source = sourcePosition.toPosition();
            Position target = targetPosition.toPosition();

            validateSourcePositionToShot(source);
            validateTargetPositionToShot(source, target);
            Piece attackedPiece = shoot(target);

            if (isNotGameOver()) {
                nextTurn();
            }

            return (GameTankPiece) attackedPiece;
        } catch (Exception e) {
            throw new ShotException(e.getMessage());
        }
    }

    private Piece shoot(Position target) {
        GameTankPiece attackedPiece = (GameTankPiece) board.piece(target);

        if (attackedPiece.getLife() == 1) {
            attackedPiece = (GameTankPiece) board.removePiece(target);
            attackedPiece.decrementLife();

            piecesOnTheBoard.remove(attackedPiece);
        } else {
            attackedPiece.decrementLife();
        }

        return attackedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new GameException("There is no piece on source position");
        }
        if (currentPlayer != ((GameTankPiece) board.piece(position)).getColor()) {
            throw new GameException("The chosen piece is not yours");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new GameException("There is no possible moves for the chosen piece");
        }
    }

    private void validateSourcePositionToShot(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ShotException("There is no piece on source position");
        }
        if (currentPlayer != ((GameTankPiece) board.piece(position)).getColor()) {
            throw new ShotException("The chosen piece is not yours");
        }
        if (!board.piece(position).isThereAnyPossibleShot()) {
            throw new ShotException("There is no opponent to attack");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new GameException("The chosen piece can't move to target position");
        }
    }

    private void validateTargetPositionToShot(Position source, Position target) {
        if (!board.piece(source).possibleShot(target)) {
            throw new ShotException("The chosen piece can't shoot to target position");
        }
    }

    public boolean isThereAnyPossibleShot() {
        GameTankPiece[][] pieces = (currentPlayer == Color.RED) ? getRedPieces() : getBluePieces();
        GameTankPiece piece;

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                piece = pieces[i][j];
                if (piece != null && piece.isThereAnyPossibleShot()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void nextTurn() {
        turn++;

        // if the current player is red then the color will be blue, else the color will be red
        currentPlayer = (currentPlayer == Color.RED) ? Color.BLUE : Color.RED;
    }

    public boolean isNotGameOver() {
        List<GameTankPiece> bluePieces = piecesOnTheBoard.stream().filter(x -> x.getColor() == Color.BLUE).toList();
        List<GameTankPiece> redPieces = piecesOnTheBoard.stream().filter(x -> x.getColor() == Color.RED).toList();

        return !bluePieces.isEmpty() && !redPieces.isEmpty();
    }

    private void placeNewPiece(char column, int row, GameTankPiece piece) {
        board.placePiece(piece, new GamePosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
        gamePieces.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('d', 1, new BombPiece(board, Color.RED));
        placeNewPiece('h', 1, new HeavyPiece(board, Color.RED));
        placeNewPiece('l', 1, new LongReachPiece(board, Color.RED));

        placeNewPiece('d', 15, new BombPiece(board, Color.BLUE));
        placeNewPiece('h', 15, new HeavyPiece(board, Color.BLUE));
        placeNewPiece('l', 15, new LongReachPiece(board, Color.BLUE));
    }
}
