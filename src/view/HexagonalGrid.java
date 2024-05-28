package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HexagonalGrid extends JFrame {
    private BufferedImage image;
    private BufferedImage resizedImage;
    private BufferedImage settingsIcon;
    private JPanel imagePanel;
    private ArrayList<Hexagon> hexagons;
    private static final int HEX_SIZE = 30;
    private Hexagon hoveredHexagon = null;
    private static final int NEW_IMAGE_WIDTH = 720;
    private static final int NEW_IMAGE_HEIGHT = 620;
    private static final int EXTRA_WIDTH = 200;
    private static final int[] indiceMaxLigne = {6, 9, 10, 9, 10, 11, 10, 11, 10, 9, 10, 9, 6};
    private ArrayList<ThickBorderInfo> thickBorders = new ArrayList<>();
    private int numeroTour;
    private String nomJoueur;
    private String temporaryMessage;
    private Timer messageTimer;
    private BufferedImage[] explorerImages;
    private JPanel explorerPanel;
    private static final int EXPLORER_IMAGE_SIZE = 50;
    private static final int EXTRA_PANEL_WIDTH = 200;


    private static class ExplorerImageInfo {
        int number;
        int posX;
        int posY;
        int treasureNumber; // New field for treasure number

        ExplorerImageInfo(int number, int posX, int posY, int treasureNumber) {
            this.number = number;
            this.posX = posX;
            this.posY = posY;
            this.treasureNumber = treasureNumber;
        }
    }


    private ArrayList<ExplorerImageInfo> explorerImageInfos = new ArrayList<>();

    public void addExplorerImage(int number, int posX, int posY, int treasureNumber) {
        if (number < 0 || number >= explorerImages.length) {
            throw new IllegalArgumentException("Invalid explorer number: " + number);
        }
        explorerImageInfos.add(new ExplorerImageInfo(number, posX, posY, treasureNumber));
        explorerPanel.repaint();
    }



    class ThickBorderInfo {
        Point position;
        int[] faces;

        public ThickBorderInfo(int x, int y, int... faces) {
            this.position = new Point(x, y);
            this.faces = faces;
        }
    }


    public HexagonalGrid() {

        setTitle("Hexagonal Grid on Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 650);

        getContentPane().setBackground(new Color(245, 245, 220));

        hexagons = new ArrayList<>();
        imagePanel = new ImagePanel();
        imagePanel.setBackground(new Color(245, 245, 220)); // Beige clair


        explorerImages = new BufferedImage[4];
        try {
            explorerImages[0] = ImageIO.read(HexagonalGrid.class.getResource("Explorateur_Bleu.png"));
            explorerImages[1] = ImageIO.read(HexagonalGrid.class.getResource("Explorateur_Jaune.png"));
            explorerImages[2] = ImageIO.read(HexagonalGrid.class.getResource("Explorateur_Rouge.png"));
            explorerImages[3] = ImageIO.read(HexagonalGrid.class.getResource("Explorateur_Vert.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adjust frame size to include extra panel
        setSize(750 + EXTRA_PANEL_WIDTH, 650);

        explorerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (ExplorerImageInfo info : explorerImageInfos) {
                    if (info != null && explorerImages[info.number] != null) {
                        g.drawImage(explorerImages[info.number], info.posX, info.posY, EXPLORER_IMAGE_SIZE, EXPLORER_IMAGE_SIZE, null);
                        // Draw the treasure number
                        g.setColor(Color.WHITE); // Set the color to white
                        g.setFont(new Font("Arial", Font.BOLD, 14));
                        g.drawString(String.valueOf(info.treasureNumber), info.posX + EXPLORER_IMAGE_SIZE / 2 - 5, info.posY + EXPLORER_IMAGE_SIZE / 2 + 5);
                    }
                }
            }
        };


        explorerPanel.setPreferredSize(new Dimension(EXTRA_PANEL_WIDTH, NEW_IMAGE_HEIGHT));
        explorerPanel.setBackground(new Color(245, 245, 220)); // Set background color to beige
        add(explorerPanel, BorderLayout.EAST);


        try {
            String imagePath = "theisland.jpg";
            URL imageUrl = HexagonalGrid.class.getResource(imagePath);

            if (imageUrl != null) {
                image = ImageIO.read(imageUrl);
                resizedImage = resizeImage(image, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
            } else {
                System.err.println("Image not found at: " + imagePath);
            }

            // Load settings icon
            String iconPath = "settings.png";
            URL iconUrl = HexagonalGrid.class.getResource(iconPath);

            if (iconUrl != null) {
                settingsIcon = ImageIO.read(iconUrl);
            } else {
                System.err.println("Settings icon not found at: " + iconPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagePanel.setPreferredSize(new Dimension(NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT));
        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });

        imagePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMove(e);
            }
        });

        add(new JScrollPane(imagePanel));

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
    }


    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
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
            for (int col = 0; col < numHexagons; col++) {
                int x = xOffset + col * horiz;
                int y = yOffset + row * vert * 3 / 4;
                hexagons.add(new Hexagon(new Point(x, y), radius));
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

    private void handleMouseClick(MouseEvent e) {
        Point clickedPoint = e.getPoint();
        for (Hexagon hex : hexagons) {
            if (hex.getHexagon().contains(clickedPoint)) {
                System.out.println("Clicked on hexagon at: " + hex.getPosition());
                break;
            }
        }
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

    public void updateTourAndPlayer(int numeroTour, String nomJoueur) {
        this.numeroTour = numeroTour;
        this.nomJoueur = nomJoueur;
        imagePanel.repaint();
    }

    public void showTemporaryMessage(String message, int durationMs) {
        temporaryMessage = message;
        imagePanel.repaint();

        if (messageTimer != null) {
            messageTimer.stop();
        }

        messageTimer = new Timer(durationMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temporaryMessage = null;
                imagePanel.repaint();
            }
        });

        messageTimer.setRepeats(false);
        messageTimer.start();
    }

    private class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(resizedImage, 0, 0, null);
            drawHexagons(g);
            drawTourAndPlayerInfo(g);
            drawSettingsIcon(g);
            drawTemporaryMessage(g);
        }


        private void drawTourAndPlayerInfo(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));

            // Les informations à afficher
            String tourText = "Numéro du tour : " + numeroTour;
            String joueurText = "Nom joueur : " + nomJoueur;

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


        private void drawSettingsIcon(Graphics g) {
            if (settingsIcon != null) {
                int iconWidth = 30;
                int iconHeight = 30;
                int x = 680;
                int y = 15;
                g.drawImage(settingsIcon, x, y, iconWidth, iconHeight, this);
            }
        }

        private void drawTemporaryMessage(Graphics g) {
            if (temporaryMessage != null) {
                g.setFont(new Font("Serif", Font.BOLD, 50));
                g.setColor(Color.RED);

                FontMetrics fm = g.getFontMetrics();
                int messageWidth = fm.stringWidth(temporaryMessage);
                int messageHeight = fm.getHeight();

                int x = (NEW_IMAGE_WIDTH - messageWidth) / 2;
                int y = (NEW_IMAGE_HEIGHT - messageHeight) / 2 + fm.getAscent();

                g.drawString(temporaryMessage, x, y);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HexagonalGrid frame = new HexagonalGrid();
            frame.setVisible(true);
        });
    }
}
