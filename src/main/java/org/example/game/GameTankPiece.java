package org.example.game;

import org.example.boardGame.Board;
import org.example.boardGame.Piece;
import org.example.boardGame.Position;
import org.example.game.pieces.BlockPiece;

public abstract class GameTankPiece extends Piece {
    private Color color;
    protected int life;

    protected int movementLimit;

    protected int reach;
    protected int reloadTime;

    public GameTankPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getLife(){
        return life;
    }

    public void decrementLife(){
        life--;
    }

    protected boolean isThereOpponentPiece(Position position){
        if(!getBoard().positionExists(position)){
            return false;
        }
        GameTankPiece piece = (GameTankPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != color && !(piece instanceof BlockPiece);
    }
}
