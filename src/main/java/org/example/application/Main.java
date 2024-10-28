package org.example.application;

import org.example.game.*;

import javax.swing.text.html.Option;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GameMatch match = new GameMatch();

        // for now, it will stay true until the game logic is done
        while (true) {

            try {
                UI.clearScreen();
                UI.printMatch(match);
                System.out.println();
                System.out.println("Movement Round");
                System.out.println();
                System.out.print("Source: ");
                GamePosition source = UI.readGamePosition(scanner);

                boolean[][] possibleMoves = match.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(match.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                GamePosition target = UI.readGamePosition(scanner);

                match.performGameMove(source, target);

                if (!match.isThereAnyPossibleShot()) {
                    System.out.println("No available shots for this turn. Moving to the next player's turn...");
                    System.out.println("\nPress enter to continue");
                    scanner.nextLine();
                    match.nextTurn();
                } else {

                    while (true) {
                        try {
                            UI.clearScreen();
                            UI.printMatch(match);
                            System.out.println("\nAttack Round\n");
                            System.out.print("Source attack: ");
                            GamePosition sourceAttack = UI.readGamePosition(scanner);

                            boolean[][] possibleShots = match.possibleShots(sourceAttack);

                            UI.clearScreen();
                            UI.printBoard(match.getPieces(), possibleShots);

                            System.out.print("\nTarget attack: ");
                            GamePosition targetAttack = UI.readGamePosition(scanner);

                            Optional<GameTankPiece> piece = match.performGameShot(sourceAttack, targetAttack);
                            break;
                        } catch (ShotException e){
                            System.out.println(e.getMessage());
                            System.out.println("\nPress enter to continue");
                            scanner.nextLine();
                        }
                    }
                }

            } catch (GameException | InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("\npress enter to continue");
                scanner.nextLine();
            }
        }
    }
}