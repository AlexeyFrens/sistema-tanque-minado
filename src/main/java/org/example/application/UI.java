package org.example.application;

import org.example.game.Color;
import org.example.game.GameMatch;
import org.example.game.GamePosition;
import org.example.game.GameTankPiece;
import org.example.game.pieces.BombPiece;
import org.example.game.pieces.HeavyPiece;
import org.example.game.pieces.LongReachPiece;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {

    // Reference
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // Reference
    // https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static GamePosition readGamePosition(Scanner scanner){
        try {
            String input = scanner.nextLine();
            char column = input.charAt(0);
            int row = Integer.parseInt(input.substring(1));
            return new GamePosition(column, row);
        }catch (RuntimeException e){
            throw new InputMismatchException("Error reading GamePosition. Valid values are from a1 to i9.");
        }
    }

    public static void printMatch(GameMatch match, List<GameTankPiece> attacked){
        printBoard(match.getPieces());
        System.out.println();
        organizeLifePrint(match.getGamePieces());
        System.out.println();
        printAttackedPieces(attacked);
        System.out.println();
        System.out.println("Turn: " + match.getTurn());
        if(match.isNotGameOver()) {
            System.out.println("Waiting player: " + match.getCurrentPlayer());
        }else{
            System.out.println("\nEND GAME");
            if(match.winnerGame() == Color.YELLOW){
                System.out.println(ANSI_GREEN + "EMPATE" + ANSI_RESET);
            }else if(match.winnerGame() == Color.BLUE){
                System.out.println("Winner: " + ANSI_BLUE + "BLUE" + ANSI_RESET);
            }else{
                System.out.println("Winner: " + ANSI_RED + "RED" + ANSI_RESET);
            }

        }
    }

    public static void printBoard(GameTankPiece[][] pieces) {
        for(int i = 0; i < pieces.length; i++) {
            if(i >= 6){
                System.out.print("0" + (15 - i) + " ");
            }else{
                System.out.print((15 - i) + " ");
            }

            for(int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("   a b c d e f g h i j k l m n o");
    }


    public static void printBoard(GameTankPiece[][] pieces, boolean[][] possibilities) {
        for(int i = 0; i < pieces.length; i++) {
            if(i >= 6){
                System.out.print("0" + (15 - i) + " ");
            }else{
                System.out.print((15 - i) + " ");
            }

            for(int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibilities[i][j]);
            }
            System.out.println();
        }
        System.out.println("   a b c d e f g h i j k l m n o");
    }

    public static void printPiece(GameTankPiece piece, boolean background) {
        if(background){
            System.out.print(ANSI_WHITE_BACKGROUND);
        }
        if(piece == null) {
            System.out.print("-" + ANSI_RESET);
        }else{
            if(piece.getColor() == Color.BLUE){
                System.out.print(ANSI_BLUE + piece + ANSI_RESET);
            }else if(piece.getColor() == Color.RED){
                System.out.print(ANSI_RED + piece + ANSI_RESET);
            }else{
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printAttackedPieces(List<GameTankPiece> attacked){
        List<GameTankPiece> red = attacked.stream().filter(x -> x.getColor() == Color.RED).toList();
        List<GameTankPiece> blue = attacked.stream().filter(x -> x.getColor() == Color.BLUE).toList();

        System.out.println("Attacked Pieces:");
        System.out.print("Red: ");
        System.out.print(ANSI_RED);
        System.out.println(Arrays.toString(red.toArray()));
        System.out.print(ANSI_RESET);

        System.out.print("Blue: ");
        System.out.print(ANSI_BLUE);
        System.out.println(Arrays.toString(blue.toArray()));
        System.out.print(ANSI_RESET);

    }

    private static void organizeLifePrint(List<GameTankPiece> pieces){
        List<GameTankPiece> red = pieces.stream().filter(x -> x.getColor() == Color.RED).toList();
        List<GameTankPiece> blue = pieces.stream().filter(x -> x.getColor() == Color.BLUE).toList();

        System.out.println("Tanks life:");
        System.out.print("Red pieces: ");
        System.out.print(ANSI_RED);
        printPiecesLife(red);
        System.out.print(ANSI_RESET);
        System.out.println();
        System.out.println();

        System.out.print("Blue pieces: ");
        System.out.print(ANSI_BLUE);
        printPiecesLife(blue);
        System.out.print(ANSI_RESET);
        System.out.println();
    }

    private static void printPiecesLife(List<GameTankPiece> pieceColor){
        for(GameTankPiece p : pieceColor){
            if(p instanceof BombPiece){
                System.out.print("Bomb - " + p.getLife() + " ");
            }else if(p instanceof HeavyPiece){
                System.out.print("Heavy - " + p.getLife() + " ");
            }else if(p instanceof LongReachPiece){
                System.out.print("LongReach - " + p.getLife() + " ");
            }
        }
    }
}
