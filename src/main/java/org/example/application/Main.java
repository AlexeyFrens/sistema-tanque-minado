package org.example.application;

import org.example.game.GameMatch;

public class Main {
    public static void main(String[] args) {
        GameMatch match = new GameMatch();
        UI.printBoard(match.getPieces());
    }
}