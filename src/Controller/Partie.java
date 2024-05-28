package Controller;

import Model.Explorateur;
import Model.PlateauJeu;
import view.HexagonalGrid;

public class Partie {
    public Partie() {
        // Constructor logic here, if needed
    }
    public void start() throws InterruptedException {
        // Create and display the hexagonal grid
        HexagonalGrid hexagonalGrid = new HexagonalGrid();

        // Initialize the game board
        PlateauJeu plateauDeJeu = new PlateauJeu();


        
        // Create the Tour controller instance
        Tour tourController = new Tour(hexagonalGrid);

        Explorateur.init_pos_explorateurs(plateauDeJeu);

        // Start the game loop
        while (true) {
            try {
                // Execute a turn
                tourController.tour(plateauDeJeu);

                // Optional: Add some condition to break the loop, like checking for end of the game
                // For example:
                // if (plateauDeJeu.isGameOver()) {
                //     break;
                // }

            } catch (InterruptedException e) {
                // Handle the interruption
                e.printStackTrace();
            }
        }

        // Optionally: Display game over message or perform any cleanup

    }
}
