import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
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

    public HexagonalGrid() {
        setTitle("Hexagonal Grid on Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 650);

        hexagons = new ArrayList<>();
        imagePanel = new ImagePanel();

        try {
            image = ImageIO.read(new File("theisland.png"));
            resizedImage = resizeImage(image, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
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
        hexagons.clear(); // Clear any existing hexagons
        int radius = HEX_SIZE;
        int horiz = (int) (Math.sqrt(3) * radius);
        int vert = 2 * radius;
        int yOffset = 40;

        for (int row = 0; row < indiceMaxLigne.length; row++) {
            int numHexagons = indiceMaxLigne[row]+1;
            int xOffset = (NEW_IMAGE_WIDTH - (numHexagons-1) * horiz) / 2;
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
                g.setColor(Color.RED);  // Couleur de survol
            } else {
                g.setColor(Color.BLACK);  // Couleur par défaut
            }
            g.drawPolygon(hex.getHexagon());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HexagonalGrid().setVisible(true);
            }
        });
    }
}
