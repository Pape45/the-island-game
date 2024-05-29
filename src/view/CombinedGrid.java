package view;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombinedGrid extends JFrame {
    private BufferedImage hexImage;
    private BufferedImage resizedHexImage;
    private BufferedImage imageForet;
    private BufferedImage resizedImageForet;
    private BufferedImage imageMontagne;
    private BufferedImage resizedImageMontagne;
    private BufferedImage imagePlage;
    private BufferedImage resizedImagePlage;
    private static final int HEX_SIZE = 30;
    private static final int NEW_IMAGE_WIDTH = 720; // Nouvelle largeur de l'image
    private static final int NEW_IMAGE_HEIGHT = 620; // Nouvelle hauteur de l'image
    private static final int[] indiceMaxLigne = {6, 9, 10, 9, 10, 11, 10, 11, 10, 9, 10, 9, 6};
    private ArrayList<Hexagon> hexagons;
    private Hexagon hoveredHexagon = null;
    private Position clickedPosition = null; // Position clicked by the user
    private List<Tuile> tuiles;

    public CombinedGrid(PlateauJeu plateauDeJeu) {
        setTitle("Combined Grid Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        hexagons = new ArrayList<>();
        tuiles = plateauDeJeu.tuiles;

        JPanel imagePanel = new ImagePanel();
        try {
            hexImage = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\theisland.png"));
            resizedHexImage = resizeImage(hexImage, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
            imageForet = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\tileForet.png"));
            resizedImageForet = resizeImage(imageForet, 35, 35);
            imageMontagne = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\tileMontagne.png"));
            resizedImageMontagne = resizeImage(imageMontagne, 35, 35);
            imagePlage = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\tilePlage.png"));
            resizedImagePlage = resizeImage(imagePlage, 35, 35);
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
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
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
        hexagons.clear(); // Clear any existing hexagons
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
                g.setColor(Color.RED); // Couleur de survol
            } else {
                g.setColor(Color.BLACK); // Couleur par défaut
            }
            g.drawPolygon(hex.getHexagon());
        }
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
                notify(); // Notify waiting thread
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
        repaint();
    }

    private class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(resizedHexImage, 0, 0, null);
            drawHexagons(g);
            drawTiles(g);
        }

        private void drawTiles(Graphics g) {
            // Calculate the offsets to center the tiles

            for (Tuile tuile : tuiles) {
                int x = (int)(tuile.position.getX() * 25.5) +343;
                int y = tuile.position.getY() * 45+23 ;
                switch (tuile.typeTuile) {
                    case 0:
                        g.drawImage(resizedImagePlage, x, y, null);
                        break;
                    case 1:
                        g.drawImage(resizedImageForet, x, y, null);
                        break;
                    default:
                        g.drawImage(resizedImageMontagne, x, y, null);
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        PlateauJeu plateauDeJeu = new PlateauJeu();
        SwingUtilities.invokeLater(() -> new CombinedGrid(plateauDeJeu).setVisible(true));
    }
}
