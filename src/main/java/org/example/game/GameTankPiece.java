package org.example.game;

import org.example.boardGame.Board;
import org.example.boardGame.Piece;
import org.example.boardGame.Position;

public abstract class GameTankPiece extends Piece {
    private Color color;
    protected int life;

    protected int movementLimit;

    protected int reach;
    protected int reloadTime;
    protected int damage;

    public GameTankPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    protected boolean isThereOpponentPiece(Position position){
        GameTankPiece piece = (GameTankPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != color;
    }
}
