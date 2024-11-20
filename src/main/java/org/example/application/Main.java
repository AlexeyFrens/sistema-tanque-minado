package org.example.application;

import org.example.game.*;
import org.example.game.exceptions.GameException;
import org.example.game.exceptions.ShotException;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GameMatch match = new GameMatch();

        List<GameTankPiece> attacked = new ArrayList<>();

        while (match.isNotGameOver()) {

            try {
                UI.clearScreen();
                UI.printMatch(match, attacked);
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
                    match.nextTurn();
                } else {

                    while (true) {
                        try {
                            boolean[][] possibleShots = match.possibleShots(target);

                            UI.clearScreen();
                            UI.printBoard(match.getPieces(), possibleShots);

                            System.out.println("\nAttack Round");

                            System.out.print("\nTarget attack: ");
                            GamePosition targetAttack = UI.readGamePosition(scanner);

                            GameTankPiece attackedPiece = match.performGameShot(target, targetAttack);

                            if (attackedPiece != null && attackedPiece.getLife() == 0) {
                                attacked.add(attackedPiece);
                            }

                            break;
                        } catch (ShotException | InputMismatchException e) {
                            System.out.println(e.getMessage());
                            System.out.println("\nPress enter to continue");
                            scanner.nextLine();
                        }
                    }
                }

            } catch (GameException | InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("\nPress enter to continue");
                scanner.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(match, attacked);
    }
}