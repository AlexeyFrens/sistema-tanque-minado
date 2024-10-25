package org.example.application;

import org.example.game.GameException;
import org.example.game.GameMatch;
import org.example.game.GamePosition;
import org.example.game.GameTankPiece;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GameMatch match = new GameMatch();

        // for now, it will stay true until the game logic is done
        while(true){

            try {
                UI.clearScreen();
                UI.printBoard(match.getPieces());
                System.out.println();
                System.out.print("Source: ");
                GamePosition source = UI.readGamePosition(scanner);

                boolean[][] possibleMoves = match.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(match.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                GamePosition target = UI.readGamePosition(scanner);

                GameTankPiece capturedPiece = match.performGameMove(source, target);
            }catch (GameException | InputMismatchException e){
                System.out.println(e.getMessage());
                System.out.println("\npress enter to continue");
                scanner.nextLine();
            }
        }
    }
}