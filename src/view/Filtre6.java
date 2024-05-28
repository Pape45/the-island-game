package view;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Filtre6 {
    private HexagonalGrid hexagonalGrid; // Stockage de la référence
    private Timer messageTimer;         // Timer interne

    public Filtre6(HexagonalGrid hexagonalGrid) {
        this.hexagonalGrid = hexagonalGrid;
    }

    public void showTemporaryMessage(String message, int durationMs) {
        if (messageTimer != null && messageTimer.isRunning()) {
            messageTimer.stop();
        }

        messageTimer = new Timer(durationMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hexagonalGrid.showTemporaryMessage(message, durationMs);
            }
        });

        messageTimer.setRepeats(false);
        messageTimer.start();
    }
}
