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
import java.net.URL;
import javax.swing.plaf.basic.BasicButtonUI;
import Controller.Partie;

public class CombinedGrid3 extends JFrame {
    private ImagePanel imagePanel;
    private PlateauJeu plateauDeJeu;
    private BufferedImage hexImage;
    private BufferedImage resizedHexImage;
    private BufferedImage imageForet;
    private BufferedImage resizedImageForet;
    private BufferedImage imageMontagne;
    private BufferedImage resizedImageMontagne;
    private BufferedImage imagePlage;
    private BufferedImage resizedImagePlage;
    private static final int HEX_SIZE = 30;
    private static final int NEW_BACK_WIDTH = 720; // Nouvelle largeur de l'image
    private static final int NEW_BACK_HEIGHT = 620; // Nouvelle hauteur de l'image
    private static final int[] indiceMaxLigne = {6, 9, 10, 9, 10, 11, 10, 11, 10, 9, 10, 9, 6};
    private ArrayList<Hexagon> hexagons;
    private Hexagon hoveredHexagon = null;
    private static Position clickedPosition = null; // Position clicked by the user
    private List<Tuile> tuiles;


    private static BufferedImage imageExplorateurbleu;
    private static BufferedImage resizedImageExplorateurbleu;
    private static BufferedImage imageExplorateurrouge;
    private static BufferedImage resizedImageExplorateurrouge;
    private static BufferedImage imageExplorateurvert;
    private static BufferedImage resizedImageExplorateurvert;
    private static BufferedImage imageExplorateurjaune;
    private static BufferedImage resizedImageExplorateurjaune;
    private ArrayList<ThickBorderInfo> thickBorders = new ArrayList<>();
    private static BufferedImage imageRequin;
    private static BufferedImage resizedImageRequin;
    private static BufferedImage imageSerpentdemer;
    private static BufferedImage resizedImageSerpentdemer;
    private static BufferedImage imageBaleine;
    private static BufferedImage resizedImageBaleine;
    private static BufferedImage imageBarque;
    private static BufferedImage resizedImageBarque;

    private static final int NEW_IMAGE_WIDTH = 35; // New width of the image
    private static final int NEW_IMAGE_HEIGHT = 35; // New height of the image

    public CombinedGrid3(PlateauJeu plateauDeJeu) {
        this.plateauDeJeu=plateauDeJeu;
        setTitle("Combined Grid Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        hexagons = new ArrayList<>();
        tuiles = plateauDeJeu.tuiles;

        JPanel imagePanel = new ImagePanel(plateauDeJeu);
        try {
            String imagePath="theisland.png";
            URL imageUrl = CombinedGrid3.class.getResource(imagePath);
            hexImage = ImageIO.read(imageUrl);
            resizedHexImage = resizeImage(hexImage, NEW_BACK_WIDTH, NEW_BACK_HEIGHT);

            imagePath="image/TuileForet.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageForet = ImageIO.read(imageUrl);
            resizedImageForet = resizeImage(imageForet, 46, 55);

            imagePath="image/Tuile_Montagne.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageMontagne = ImageIO.read(imageUrl);
            resizedImageMontagne = resizeImage(imageMontagne, 46, 55);

            imagePath="image/Tuile_Plage.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imagePlage = ImageIO.read(imageUrl);
            resizedImagePlage = resizeImage(imagePlage, 46, 55);

            imagePath="image/Explorateur_Bleu.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageExplorateurbleu = ImageIO.read(imageUrl);
            resizedImageExplorateurbleu = resizeImage(imageExplorateurbleu, NEW_IMAGE_WIDTH+2, NEW_IMAGE_HEIGHT+4);

            imagePath="image/Explorateur_Rouge.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageExplorateurrouge = ImageIO.read(imageUrl);
            resizedImageExplorateurrouge = resizeImage(imageExplorateurrouge, NEW_IMAGE_WIDTH-6, NEW_IMAGE_HEIGHT-5);

            imagePath="image/Explorateur_Jaune.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageExplorateurjaune = ImageIO.read(imageUrl);
            resizedImageExplorateurjaune = resizeImage(imageExplorateurjaune, NEW_IMAGE_WIDTH-3, NEW_IMAGE_HEIGHT+3);

            imagePath="image/Explorateur_Vert.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageExplorateurvert = ImageIO.read(imageUrl);
            resizedImageExplorateurvert = resizeImage(imageExplorateurvert, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);

            imagePath="image/Requin.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageRequin = ImageIO.read(imageUrl);
            resizedImageRequin = resizeImage(imageRequin, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);

            imagePath="image/Serpent_de_mer.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageSerpentdemer = ImageIO.read(imageUrl);
            resizedImageSerpentdemer = resizeImage(imageSerpentdemer, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);

            imagePath="image/Baleine.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageBaleine = ImageIO.read(imageUrl);
            resizedImageBaleine = resizeImage(imageBaleine, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);

            imagePath="image/Barque.png";
            imageUrl = CombinedGrid3.class.getResource(imagePath);
            imageBarque = ImageIO.read(imageUrl);
            resizedImageBarque = resizeImage(imageBarque, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        imagePanel.setPreferredSize(new Dimension(NEW_BACK_WIDTH, NEW_BACK_HEIGHT));
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



        thickBorders.add(new ThickBorderInfo(283, 175, 1, 5, 6));
        thickBorders.add(new ThickBorderInfo(334, 175, 1, 6));
        thickBorders.add(new ThickBorderInfo(385, 175, 1, 6));
        thickBorders.add(new ThickBorderInfo(436, 175, 1, 2, 6));
        thickBorders.add(new ThickBorderInfo(258, 220, 5, 6));
        thickBorders.add(new ThickBorderInfo(462, 220, 1, 2));
        thickBorders.add(new ThickBorderInfo(181, 265, 1, 4, 5, 6));
        thickBorders.add(new ThickBorderInfo(232, 265, 6));
        thickBorders.add(new ThickBorderInfo(487, 265, 1));
        thickBorders.add(new ThickBorderInfo(538, 265, 1, 2, 3, 6));
        thickBorders.add(new ThickBorderInfo(207, 310, 5));
        thickBorders.add(new ThickBorderInfo(513, 310, 2));
        thickBorders.add(new ThickBorderInfo(181, 355, 3, 4, 5, 6));
        thickBorders.add(new ThickBorderInfo(232, 355, 4));
        thickBorders.add(new ThickBorderInfo(487, 355, 3));
        thickBorders.add(new ThickBorderInfo(538, 355, 1, 2, 3, 4));
        thickBorders.add(new ThickBorderInfo(258, 400, 4, 5));
        thickBorders.add(new ThickBorderInfo(462, 400, 2, 3));
        thickBorders.add(new ThickBorderInfo(283, 445, 3, 4, 5));
        thickBorders.add(new ThickBorderInfo(334, 445, 3, 4));
        thickBorders.add(new ThickBorderInfo(385, 445, 3, 4));
        thickBorders.add(new ThickBorderInfo(436, 445, 2, 3, 4));


        createHexagonalGrid();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
   
    class ThickBorderInfo {
        Point position;
        int[] faces;

        public ThickBorderInfo(int x, int y, int... faces) {
            this.position = new Point(x, y);
            this.faces = faces;
        }
    }

    public void refreshImagePanel() {
        // Remove the existing ImagePanel
        remove(imagePanel);

        // Create a new ImagePanel and add it to the frame
        imagePanel = new ImagePanel(plateauDeJeu);
        imagePanel.setPreferredSize(new Dimension(NEW_BACK_WIDTH, NEW_BACK_HEIGHT));
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

        // Add the new ImagePanel and revalidate the frame
        add(new JScrollPane(imagePanel), BorderLayout.CENTER);
        revalidate();
        repaint();
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
            int xOffset = (NEW_BACK_WIDTH - (numHexagons - 1) * horiz) / 2;
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
            ((Graphics2D) g).setStroke(new BasicStroke(1));
            g.drawPolygon(hex.getHexagon());

            for (ThickBorderInfo info : thickBorders) {
                if (hex.getPosition().equals(info.position)) {
                    drawThickBorder(g, hex, info.faces);
                    break;
                }
            }
        }
    }

    private void drawThickBorder(Graphics g, Hexagon hex, int[] faces) {
        Polygon polygon = hex.getHexagon();
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        for (int face : faces) {
            int i1 = (face + 4) % 6;
            int i2 = (face + 5) % 6;
            g.drawLine(polygon.xpoints[i1], polygon.ypoints[i1], polygon.xpoints[i2], polygon.ypoints[i2]);
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
        private final List<SerpentDeMer> serpentDeMer;
        private final List<Requin> requin;
        private final List<Baleine> baleine;
        private final List<Barque> barque;
        private final Joueur[] joueurs;
        private final int tour;

        public ImagePanel(PlateauJeu plateauDeJeu) {
            this.serpentDeMer = plateauDeJeu.serpentDeMer;
            this.requin = plateauDeJeu.requins;
            this.baleine = plateauDeJeu.baleines;
            this.barque = plateauDeJeu.barques;
            this.joueurs = plateauDeJeu.joueurs;
            this.tour =plateauDeJeu.tour;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(resizedHexImage, 0, 0, null);
            drawHexagons(g);
            drawTiles(g);
            for (SerpentDeMer serpent : serpentDeMer) {
                int x = (int)(serpent.position.getX() * 25.5 + 343);
                int y = serpent.position.getY() *45+23 ;
                g.drawImage(resizedImageSerpentdemer, x, y, null);
            }

            // Draw Requin
            for (Requin requin : requin) {
                int x = (int)(requin.position.getX() * 25.5 + 343);
                int y = requin.position.getY() * 45+23;
                g.drawImage(resizedImageRequin, x, y, null);
            }

            // Draw Baleine
            for (Baleine baleine : baleine) {
                int x = (int)(baleine.position.getX() * 25.5 + 343);
                int y = baleine.position.getY() * 45+23;
                g.drawImage(resizedImageBaleine, x, y, null);
            }

            for (Barque barque : barque) {
                int x = (int) (barque.position.getX() * 25.5 + 343);
                int y = barque.position.getY() * 45 + 23;
                g.drawImage(resizedImageBarque, x, y, null);
            }

            for (int i = 0; i < joueurs.length; i++) {
                Joueur joueur = joueurs[i];
                BufferedImage explorerImage = getExplorerImageByIndex(i);
                for (int k=0; k<joueur.explorateurs.size();k++) {
                    int x = (int) (joueur.explorateurs.get(k).getPosition().getX() * 25.5 + 338 + 4 * i);
                    int y = joueur.explorateurs.get(k).getPosition().getY() * 45 + 23;
                    if(joueur.explorateurs.get(k).getPosition().getY()!=-1)
                        g.drawImage(explorerImage, x, y, null);
                }
            }

            drawTourAndPlayerInfo(g);
        }
        private BufferedImage getExplorerImageByIndex(int index) {
            switch (index) {
                case 0:
                    return resizedImageExplorateurrouge;
                case 1:
                    return resizedImageExplorateurjaune;
                case 2:
                    return resizedImageExplorateurbleu;
                case 3:
                    return resizedImageExplorateurvert;
                default:
                    throw new IllegalArgumentException("Invalid player index");
            }
        }

        private void drawTiles(Graphics g) {
            // Calculate the offsets to center the tiles

            for (Tuile tuile : tuiles) {
                int x = (int)(tuile.position.getX() * 25.5) +337;
                int y = tuile.position.getY() * 45+13 ;
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

        private void drawTourAndPlayerInfo(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));

            // Les informations à afficher
            String tourText = "Numéro du tour : " + tour;
            String joueurText = "Nom joueur : " + tour;

            FontMetrics fm = g.getFontMetrics();
            int tourWidth = fm.stringWidth(tourText);
            int joueurWidth = fm.stringWidth(joueurText);
            int height = fm.getHeight();

            int padding = 5;
            int width = Math.max(tourWidth, joueurWidth) + 2 * padding;
            int totalHeight = 2 * height + 3 * padding;

            int x = 10;
            int y = 10;

            g.setColor(Color.BLACK);
            g.fillRect(x - padding / 2, y - padding / 2, width, totalHeight);
            g.setColor(Color.WHITE);
            g.drawRect(x - padding / 2, y - padding / 2, width, totalHeight);

            g.drawString(tourText, x, y + fm.getAscent());
            g.drawString(joueurText, x, y + height + 2 * padding + fm.getAscent());
        }
    }


    /*public static void main(String[] args) {
        PlateauJeu plateauDeJeu = new PlateauJeu();
        SwingUtilities.invokeLater(() -> {
            CombinedGrid3 frame = new CombinedGrid3(plateauDeJeu);
            frame.setVisible(true);

            plateauDeJeu.serpentDeMer.get(2).position.setX(6);
            plateauDeJeu.serpentDeMer.get(2).position.setY(0);
            frame.refreshImagePanel();
        });
    }*/
}
