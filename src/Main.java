import javax.swing.SwingUtilities;
import view.*;
import Model.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HexagonalGrid hexagonalGrid = new HexagonalGrid();
            hexagonalGrid.setVisible(true);
            Init_Jeu jeu = new Init_Jeu();
            Filtre5 filtre5 = new Filtre5(hexagonalGrid);
            Filtre6 filtre6 = new Filtre6(hexagonalGrid);

            int numeroTour = 1;
            Joueur joueur = jeu.getJoueurs()[0];
            filtre5.updateTourAndPlayer(numeroTour, joueur);
            filtre6.showTemporaryMessage("Message temp 1", 2000);
        });
    }
}

