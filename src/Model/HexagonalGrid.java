package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HexagonalGrid extends JFrame {
    private BufferedImage resizedImage;
    private JPanel imagePanel;
    private ArrayList<Hexagon> hexagons;
    private static final int HEX_SIZE = 30;
    private Hexagon hoveredHexagon = null;
    private static final int NEW_IMAGE_WIDTH = 720;
    private static final int NEW_IMAGE_HEIGHT = 620;
    private static final int[] indiceMaxLigne = {6, 9, 10, 9, 10, 11, 10, 11, 10, 9, 10, 9, 6};
    private Position clickedPosition = null;

    // Map pour associer chaque type de tuile à son image
    private Map<Integer, BufferedImage> tileImages = new HashMap<>();

    public HexagonalGrid() {
        setTitle("Hexagonal Grid Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        hexagons = new ArrayList<>();
        imagePanel = new ImagePanel();

        // Charger les images des tuiles
        try {
            tileImages.put(0, ImageIO.read(new File("forest.png")));
            tileImages.put(1, ImageIO.read(new File("mountain.png")));
            tileImages.put(2, ImageIO.read(new File("beach.png")));
            resizedImage = resizeImage(ImageIO.read(new File("theisland.png")), NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagePanel.setPreferredSize(new Dimension(NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT));
        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                choix_case(e);
            }
        });

        imagePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMove(e);
            }
        });

        add(createPlayerPanel(), BorderLayout.NORTH);
        add(new JScrollPane(imagePanel), BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.EAST);
        add(createInfoPanel(), BorderLayout.SOUTH);

        createHexagonalGrid();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private JPanel createPlayerPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(getWidth(), 50));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel playerLabel = new JLabel("Joueur : 1", JLabel.CENTER);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(playerLabel);

        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, getHeight()));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.DARK_GRAY);

        JButton pieceButton = new JButton("Pion");
        JButton saveButton = new JButton("Sauvegarder");

        pieceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(20));
        panel.add(pieceButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(saveButton);

        return panel;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(getWidth(), 100));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.GRAY);

        JButton listButton = new JButton("Liste tuiles en main");
        JButton saveQuitButton = new JButton("Sauver et quitter");

        panel.add(listButton, BorderLayout.CENTER);
        panel.add(saveQuitButton, BorderLayout.EAST);

        return panel;
    }

    private void createHexagonalGrid() {
        hexagons.clear();
        int radius = HEX_SIZE;
        int horiz = (int) (Math.sqrt(3) * radius);
        int vert = 2 * radius;
        int yOffset = 40;

        for (int row = 0; row < indiceMaxLigne.length; row++) {
            int numHexagons = indiceMaxLigne[row] + 1;
            int xOffset = (NEW_IMAGE_WIDTH - (numHexagons - 1) * horiz) / 2;
            int startValue = -numHexagons + 1;
            int increment = 2;

            for (int col = 0; col < numHexagons; col++) {
                int x = xOffset + col * horiz;
                int y = yOffset + row * vert * 3 / 4;
                int value = startValue + col * increment;
                hexagons.add(new Hexagon(new Point(x, y), radius, value));
            }
        }
    }

    private void drawHexagons(Graphics g) {
        for (Hexagon hex : hexagons) {
            if (hex == hoveredHexagon) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawPolygon(hex.getHexagon());

            // Dessiner l'image de la tuile dans l'hexagone
            int typeTuile = getTypeTuileAt(hex.getPosition()); // Obtenez le type de tuile à cette position
            if (typeTuile != -1) { // Vérifiez si la tuile existe
                BufferedImage tileImage = tileImages.get(typeTuile); // Obtenez l'image correspondante
                if (tileImage != null)
                {
                    // Calculez les coordonnées pour dessiner l'image centrée dans l'hexagone
                    int imgX = hex.getCenter().x - tileImage.getWidth() / 2;
                    int imgY = hex.getCenter().y - tileImage.getHeight() / 2;
                    // Dessinez l'image de la tuile dans l'hexagone
                    g.drawImage(tileImage, imgX, imgY, null);
                }
            }
        }
    }
}

// Méthode pour obtenir le type de tuile à une position donnée
private int getTypeTuileAt(Point position) {
    // Logique pour obtenir le type de tuile à partir de la position
    // Vous pouvez utiliser les informations de votre modèle pour cela
    return 0; // Remplacez cela par la vraie logique de votre application
}

private synchronized void choix_case(MouseEvent e) {
    Point clickedPoint = e.getPoint();
    Point position;
    for (Hexagon hex : hexagons) {
        if (hex.getHexagon().contains(clickedPoint)) {
            position = hex.getPosition();

            int newX = (int) ((position.getX()) / 25.5) - 14;
            int newY = (int) ((position.getY() - 40) / 45);

            clickedPosition = new Position(newX, newY);
            notify();
            break;
        }
    }
}

public synchronized Position waitForClick() throws InterruptedException {
    clickedPosition = null;
    while (clickedPosition == null) {
        wait();
    }
    return clickedPosition;
}

private void handleMouseMove(MouseEvent e) {
    Point movedPoint = e.getPoint();
    boolean found = false;
    for (Hexagon hex : hexagons) {
        if (hex.getHexagon().contains(movedPoint)) {
            hoveredHexagon = hex;
            found = true;
            break;
        }
    }
    if (!found) {
        hoveredHexagon = null;
    }
    imagePanel.repaint();
}

private class ImagePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
        drawHexagons(g);
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new HexagonalGrid().setVisible(true);
        }
    });
}
}
