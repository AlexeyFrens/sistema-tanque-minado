package org.example.boardGame;

public abstract class Piece {
    private Board board;
    protected Position position;

    public Piece(Board board) {
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    // This method is used to check possible movement
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public abstract boolean[][] possibleShots();

    public boolean possibleShot(Position position) {
        return possibleShots()[position.getRow()][position.getColumn()];
    }

    // method to check if the piece is stuck

    public boolean isThereAnyPossibleMove(){
        boolean[][] matrix = possibleMoves();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

    // Method to check if the piece can shoot the opponent

    public boolean isThereAnyPossibleShot(){
        boolean[][] matrix = possibleShots();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
