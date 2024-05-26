import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                Filtre5 filtre5 = new Filtre5();
                Filtre6 filtre6 = new Filtre6();

                int numeroTour = 1;
                Joueur joueur = jeu.getJoueurs()[0];

                filtre5.filtre_5(hexagonalGrid, numeroTour, joueur);

                // Test d'un message
                filtre6.filtre_6(hexagonalGrid, "Message temp 1");

                // TEst deuxième message
                // Faudra donc gérer un time
                Timer timer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        filtre6.filtre_6(hexagonalGrid, "Message temp 2");
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
    }
}
