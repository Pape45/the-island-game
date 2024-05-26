import javax.swing.SwingUtilities;
import view.*;
import Model.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HexagonalGrid hexagonalGrid = new HexagonalGrid();
                hexagonalGrid.setVisible(true);

                Init_Jeu jeu = new Init_Jeu();
                Filtre5 filtre = new Filtre5();

                int numeroTour = 1;
                Joueur joueur = jeu.getJoueurs()[0];

                filtre.filtre_5(hexagonalGrid, numeroTour, joueur);
            }
        });
    }
}
