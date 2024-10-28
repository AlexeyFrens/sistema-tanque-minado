package org.example.application;

import org.example.game.Color;
import org.example.game.GameMatch;
import org.example.game.GamePosition;
import org.example.game.GameTankPiece;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {

    // Reference
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
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

    public static void printMatch(GameMatch match){
        printBoard(match.getPieces());
        System.out.println();
        System.out.println("Turn: " + match.getTurn());
        System.out.println("Waiting player: " + match.getCurrentPlayer());
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
            System.out.print(ANSI_CYAN_BACKGROUND);
        }
        if(piece == null) {
            System.out.print("-" + ANSI_RESET);
        }else{
            if(piece.getColor() == Color.BLUE){
                System.out.print(ANSI_BLUE + piece + ANSI_RESET);
            }else{
                System.out.print(ANSI_RED + piece + ANSI_RESET);
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
}
