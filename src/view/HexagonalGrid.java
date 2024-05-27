package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

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
    private static final int[] INDICE_MAX_LIGNE = {6, 9, 10, 9, 10, 11, 10, 11, 10, 9, 10, 9, 6};
    private ArrayList<ThickBorderInfo> thickBorders = new ArrayList<>();
    private int numeroTour;
    private String nomJoueur;
    private String temporaryMessage;
    private Timer messageTimer;
    private List<BufferedImage> tileImagesPlage;
    private List<BufferedImage> tileImagesForet;
    private List<BufferedImage> tileImagesMontagne;
    private List<Hexagon> centerHexagons;

    class ThickBorderInfo {
        Point position;
        int[] faces;

        public ThickBorderInfo(int x, int y, int... faces) {
            this.position = new Point(x, y);
            this.faces = faces;
        }
    }

    class Hexagon {
        private Point position;
        private Polygon hexagon;
        private BufferedImage tileImage;

        public Hexagon(Point position, int radius) {
            this.position = position;
            this.hexagon = createHexagon(position, radius);
        }

        private Polygon createHexagon(Point center, int size) {
            int[] xPoints = new int[6];
            int[] yPoints = new int[6];
            for (int i = 0; i < 6; i++) {
                xPoints[i] = (int) (center.x + size * Math.cos(Math.PI / 3 * i));
                yPoints[i] = (int) (center.y + size * Math.sin(Math.PI / 3 * i));
            }
            return new Polygon(xPoints, yPoints, 6);
        }

        public Point getPosition() {
            return position;
        }

        public Polygon getHexagon() {
            return hexagon;
        }

        public void setTileImage(BufferedImage tileImage) {
            this.tileImage = tileImage;
        }

        public BufferedImage getTileImage() {
            return tileImage;
        }
    }

    public HexagonalGrid() {
        setTitle("Hexagonal Grid on Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 650);

        hexagons = new ArrayList<>();
        imagePanel = new ImagePanel();

        loadImages();
        loadTileImages();

        imagePanel.setPreferredSize(new Dimension(NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT));
        addMouseListeners(imagePanel);

        add(new JScrollPane(imagePanel));

        initializeThickBorders();
        createHexagonalGrid();
        placeTilesOnHexagons();
    }

    private void loadImages() {
        try {
            String imagePath = "C:\\Users\\Todoroki\\Documents\\GitHub\\the-island-game\\src\\view\\theisland.png";
            URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                image = ImageIO.read(imageUrl);
                resizedImage = resizeImage(image, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
            } else {
                System.err.println("Image not found at: " + imagePath);
            }

            String iconPath = "settings.png";
            URL iconUrl = getClass().getResource(iconPath);

            if (iconUrl != null) {
                settingsIcon = ImageIO.read(iconUrl);
            } else {
                System.err.println("Settings icon not found at: " + iconPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTileImages() {
        tileImagesPlage = new ArrayList<>();
        tileImagesForet = new ArrayList<>();
        tileImagesMontagne = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            try {
                String tilePath = String.format("C:\\Users\\Todoroki\\Documents\\GitHub\\the-island-game\\src\\view\\tilePlage.png", i);
                URL tileUrl = getClass().getResource(tilePath);
                if (tileUrl != null) {
                    tileImagesPlage.add(ImageIO.read(tileUrl));
                } else {
                    System.err.println("Tile image not found at: " + tilePath);
                }

                tilePath = String.format("C:\\Users\\Todoroki\\Documents\\GitHub\\the-island-game\\src\\view\\tileForet.png", i);
                tileUrl = getClass().getResource(tilePath);
                if (tileUrl != null) {
                    tileImagesForet.add(ImageIO.read(tileUrl));
                } else {
                    System.err.println("Tile image not found at: " + tilePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= 8; i++) {
            try {
                String tilePath = String.format("C:\\Users\\Todoroki\\Documents\\GitHub\\the-island-game\\src\\view\\tileMontagne.png", i);
                URL tileUrl = getClass().getResource(tilePath);
                if (tileUrl != null) {
                    tileImagesMontagne.add(ImageIO.read(tileUrl));
                } else {
                    System.err.println("Tile image not found at: " + tilePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addMouseListeners(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMove(e);
            }
        });
    }

    private void initializeThickBorders() {
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
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    private void createHexagonalGrid() {
        int radius = HEX_SIZE;
        int vert = (int) (Math.sqrt(3) * radius);
        int horiz = 2 * radius;
        int numRows = INDICE_MAX_LIGNE.length;

        for (int row = 0; row < numRows; row++) {
            int numCols = INDICE_MAX_LIGNE[row];
            int xOffset = (row % 2) * horiz / 2;
            for (int col = 0; col < numCols; col++) {
                int x = col * horiz + xOffset;
                int y = row * (vert / 2);
                Hexagon hex = new Hexagon(new Point(x, y), radius);
                hexagons.add(hex);

                if (isCenterHexagon(row, col)) {
                    centerHexagons.add(hex);
                }
            }
        }
    }

    private boolean isCenterHexagon(int row, int col) {
        return row >= 3 && row <= 9 && col >= 2 && col <= 7;
    }

    private void placeTilesOnHexagons() {
        List<BufferedImage> allTiles = new ArrayList<>();
        allTiles.addAll(tileImagesPlage);
        allTiles.addAll(tileImagesForet);
        allTiles.addAll(tileImagesMontagne);
        Collections.shuffle(allTiles);

        int numCenterHexagons = centerHexagons.size();
        if (allTiles.size() > numCenterHexagons) {
            System.err.println("More tile images than hexagons available.");
            return;
        }

        for (int i = 0; i < allTiles.size(); i++) {
            centerHexagons.get(i).setTileImage(allTiles.get(i));
        }
    }

    private void handleMouseClick(MouseEvent e) {
        Point clickPoint = e.getPoint();
        for (Hexagon hex : hexagons) {
            if (hex.getHexagon().contains(clickPoint)) {
                System.out.println("Clicked on hexagon at: " + hex.getPosition());
                break;
            }
        }
    }

    private void handleMouseMove(MouseEvent e) {
        Point movePoint = e.getPoint();
        hoveredHexagon = null;
        for (Hexagon hex : hexagons) {
            if (hex.getHexagon().contains(movePoint)) {
                hoveredHexagon = hex;
                break;
            }
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

        private void drawHexagons(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            for (Hexagon hex : hexagons) {
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1));
                g2d.draw(hex.getHexagon());

                if (hex.getTileImage() != null) {
                    Point pos = hex.getPosition();
                    BufferedImage tileImage = hex.getTileImage();
                    int imgWidth = HEX_SIZE * 2;
                    int imgHeight = HEX_SIZE * 2;
                    g2d.drawImage(tileImage, pos.x - imgWidth / 2, pos.y - imgHeight / 2, imgWidth, imgHeight, null);
                }
            }
        }

        private void drawTourAndPlayerInfo(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));

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
                Image scaledIcon = settingsIcon.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                int x = getWidth() - iconWidth - 20;
                int y = 10;
                g.drawImage(scaledIcon, x, y, this);
            }
        }

        private void drawTemporaryMessage(Graphics g) {
            if (temporaryMessage != null) {
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.BOLD, 20));

                FontMetrics fm = g.getFontMetrics();
                int messageWidth = fm.stringWidth(temporaryMessage);
                int messageHeight = fm.getHeight();

                int x = (getWidth() - messageWidth) / 2;
                int y = (getHeight() - messageHeight) / 2;

                g.setColor(Color.BLACK);
                g.fillRect(x - 10, y - fm.getAscent() - 10, messageWidth + 20, messageHeight + 20);
                g.setColor(Color.YELLOW);
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
