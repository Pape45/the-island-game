package Controller;

import Model.Barque;
import Model.Explorateur;
import Model.PlateauJeu;
import view.CombinedGrid3;

public class Partie {

    public static boolean isInit=false;

    public Partie() {
        // Constructor logic here, if needed
    }

    public static boolean isPieceInit(){
        return isInit;
    }

    public void start() throws InterruptedException {
        // Create and display the hexagonal grid
        PlateauJeu plateauDeJeu = new PlateauJeu();
        CombinedGrid3 hexagonalGrid = new CombinedGrid3(plateauDeJeu);

        // Initialize the game board
        hexagonalGrid.setVisible(true);


        // Create the Tour controller instance
        Tour tourController = new Tour(hexagonalGrid);

        Explorateur.init_pos_explorateurs(plateauDeJeu);
        Barque.init_barques(plateauDeJeu);
        isInit=true;
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
