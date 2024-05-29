package main;

import Controller.Partie;

public class TheIslandGame {
    public static void main(String[] args) throws InterruptedException {
        // Create a Partie instance and start the game
        Partie partie = new Partie();
        partie.start();
    }
}
