package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HexagonalGrid extends JFrame {
    private BufferedImage image;
    private BufferedImage resizedImage;
    private JPanel imagePanel;
    private ArrayList<Hexagon> hexagons;
    private static final int HEX_SIZE = 30;
    private Hexagon hoveredHexagon = null;
    private static final int NEW_IMAGE_WIDTH = 720; // Nouvelle largeur de l'image
    private static final int NEW_IMAGE_HEIGHT = 620; // Nouvelle hauteur de l'image
    private static final int[] indiceMaxLigne = {6, 9, 10, 9, 10, 11, 10, 11, 10, 9, 10, 9, 6};
    private ArrayList<ThickBorderInfo> thickBorders = new ArrayList<>();

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

        hexagons = new ArrayList<>();
        imagePanel = new ImagePanel();

        try {
            // Key change: Construct the path relative to the class location
            String imagePath = "theisland.jpg"; 
            URL imageUrl = HexagonalGrid.class.getResource(imagePath);

            if (imageUrl != null) {
                image = ImageIO.read(imageUrl);
                resizedImage = resizeImage(image, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
            } else {
                System.err.println("Image not found at: " + imagePath);
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

    private class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(resizedImage, 0, 0, null);
            drawHexagons(g);
        }
    }
}
