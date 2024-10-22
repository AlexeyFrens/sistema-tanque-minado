package org.example.game;

import org.example.boardGame.Position;

public class GamePosition {
    private char column;
    private int row;

    public GamePosition(char column, int row) {
        if(column < 'a' || column > 'i' || row < 0 || row > 9){
            throw new GameException("Error instantiating GamePosition. Valid values are from a1 to i9.");
        }
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    //converts the game position to a matrix position
    protected Position toPosition(){
        return new Position(9 - row, column - 'a');
    }

    //converts the matrix position to a game position
    protected static GamePosition fromPosition(Position position){
        return new GamePosition((char)('a' - position.getColumn()), 9 - position.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
