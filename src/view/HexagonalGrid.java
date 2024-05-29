package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HexagonalGrid extends JFrame {
    private BufferedImage image;
    private BufferedImage resizedImage;
    private BufferedImage settingsIcon;
    private BufferedImage splashImage;
    private JPanel imagePanel;
    private JPanel splashPanel;
    private JPanel mainPanel;
    private ArrayList<Hexagon> hexagons;
    private static final int HEX_SIZE = 30;
    private Hexagon hoveredHexagon = null;
    private static final int NEW_IMAGE_WIDTH = 720;
    private static final int NEW_IMAGE_HEIGHT = 620;
    private static final int[] indiceMaxLigne = {6, 9, 10, 9, 10, 11, 10, 11, 10, 9, 10, 9, 6};
    private ArrayList<ThickBorderInfo> thickBorders = new ArrayList<>();
    private int numeroTour;
    private String nomJoueur;
    private String temporaryMessage;
    private Timer messageTimer;
    private JButton showBoardButton;
    private JPanel gamePanel;

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
        setSize(960, 750);

        hexagons = new ArrayList<>();
        imagePanel = new ImagePanel();
        splashPanel = new SplashPanel();
        mainPanel = new JPanel(new BorderLayout());

        try {
            String imagePath = "theisland.png";
            URL imageUrl = HexagonalGrid.class.getResource(imagePath);

            if (imageUrl != null) {
                image = ImageIO.read(imageUrl);
                resizedImage = resizeImage(image, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
            } else {
                System.err.println("Image not found at: " + imagePath);
            }

            String iconPath = "settings.png";
            URL iconUrl = HexagonalGrid.class.getResource(iconPath);

            if (iconUrl != null) {
                settingsIcon = ImageIO.read(iconUrl);
            } else {
                System.err.println("Settings icon not found at: " + iconPath);
            }

            String splashPath = "FondIsland.png";
            URL splashUrl = HexagonalGrid.class.getResource(splashPath);

            if (splashUrl != null) {
                splashImage = ImageIO.read(splashUrl);
            } else {
                System.err.println("Splash image not found at: " + splashPath);
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

        showBoardButton = new JButton("Commencer le jeu");
        showBoardButton.setPreferredSize(new Dimension(200, 50));
        showBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGamePanel();
                mainPanel.removeAll();
                mainPanel.add(gamePanel, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        splashPanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(showBoardButton);
        splashPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(splashPanel, BorderLayout.CENTER);

        add(mainPanel);

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

    private void createGamePanel() {
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(new JScrollPane(imagePanel), BorderLayout.CENTER);

        JPanel topRightPanel = new JPanel();
        topRightPanel.setPreferredSize(new Dimension(200, 200));
        topRightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setPreferredSize(new Dimension(200, 100));
        bottomRightPanel.setBackground(Color.GRAY);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(NEW_IMAGE_WIDTH, 100));
        bottomPanel.setBackground(Color.DARK_GRAY);

        gamePanel.add(topRightPanel, BorderLayout.EAST);
        gamePanel.add(bottomRightPanel, BorderLayout.SOUTH);
        gamePanel.add(bottomPanel, BorderLayout.SOUTH);

        createHexagonalGrid(); // Création de la grille hexagonale
        imagePanel.repaint(); // Redessiner le jeu
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

    private class SplashPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (splashImage != null) {
                g.drawImage(splashImage, 0, 0, getWidth(), getHeight(), this);
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

// Classe Hexagon pour représenter les hexagones
class Hexagon {
    private Point position;
    private int radius;
    private Polygon hexagon;

    public Hexagon(Point position, int radius) {
        this.position = position;
        this.radius = radius;
        this.hexagon = createHexagon(position, radius);
    }

    public Point getPosition() {
        return position;
    }

    public Polygon getHexagon() {
        return hexagon;
    }

    private Polygon createHexagon(Point center, int radius) {
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (center.x + radius * Math.cos(i * 2 * Math.PI / 6));
            yPoints[i] = (int) (center.y + radius * Math.sin(i * 2 * Math.PI / 6));
        }
        return new Polygon(xPoints, yPoints, 6);
    }
}
