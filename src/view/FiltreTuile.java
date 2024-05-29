/*package view;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class FiltreTuile {
    private BufferedImage image;
    private BufferedImage resizedImage;
    private JPanel imagePanel;
    private static final int NEW_IMAGE_WIDTH = 30; // Nouvelle largeur de l'image
    private static final int NEW_IMAGE_HEIGHT = 30; // Nouvelle hauteur de l'image
    public  FiltreTuile(PlateauJeu PlateauDeJeu) throws IOException
    {
        image = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\theisland.png"));
        resizedImage = resizeImage(image, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        for (int i=0;i<40;i++)
        {

        }
    }


    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
}*/

package view;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

/*public class FiltreTuile {
    private static BufferedImage imageforet;
    private static BufferedImage resizedImageforet;
    private static BufferedImage imagemontagne;
    private static BufferedImage resizedImagemontagne;
    private static BufferedImage imageplage;
    private static BufferedImage resizedImageplage;
    private static final int NEW_IMAGE_WIDTH = 35; // New width of the image
    private static final int NEW_IMAGE_HEIGHT = 35; // New height of the image

    public FiltreTuile(PlateauJeu plateauDeJeu) throws IOException {
        imageforet = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\tileForet.png"));
        resizedImageforet = resizeImage(imageforet, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imagemontagne = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\tileMontagne.png"));
        resizedImagemontagne = resizeImage(imagemontagne, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imageplage = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\tilePlage.png"));
        resizedImageplage = resizeImage(imageplage, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);

        // Create and set up the window
        JFrame frame = new JFrame("Plateau de Jeu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        ImagePanel imagePanel = new ImagePanel(plateauDeJeu.tuiles);
        frame.add(imagePanel);
        frame.setSize(800, 600); // Set the desired size of the frame
        frame.setVisible(true);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    private static class ImagePanel extends JPanel {
        private final List<Tuile> tuiles;

        public ImagePanel(List<Tuile> tuiles) {
            this.tuiles = tuiles;
        }

        @Override
        protected void paintComponent(Graphics g,PlateauJeu Plateau_de_jeu) {
            super.paintComponent(g,Plateau_de_jeu);

            // Draw each image at the position specified by each Tuile
            for (Tuile tuile : tuiles) {
                int x = tuile.position.getX() * 20 + 300;
                int y = tuile.position.getY() * 30;
                switch (tuile.typeTuile) {
                    case 0:
                        g.drawImage(resizedImageplage, x, y, null);
                        break;
                    case 1:
                        g.drawImage(resizedImageforet, x, y, null);
                        break;
                    default:
                        g.drawImage(resizedImagemontagne, x, y, null);
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create and set up a PlateauJeu with sample data for demonstration
        PlateauJeu plateauDeJeu = new PlateauJeu();
        // Launch the application
        
        try {
            new FiltreTuile(plateauDeJeu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
