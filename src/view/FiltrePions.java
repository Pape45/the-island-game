package view;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

/*public class FiltrePions {
    private static BufferedImage imageExplorateurbleu;
    private static BufferedImage resizedImageExplorateurbleu;
    private static BufferedImage imageExplorateurrouge;
    private static BufferedImage resizedImageExplorateurrouge;
    private static BufferedImage imageExplorateurvert;
    private static BufferedImage resizedImageExplorateurvert;
    private static BufferedImage imageExplorateurjaune;
    private static BufferedImage resizedImageExplorateurjaune;
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

    public FiltrePions(PlateauJeu plateauDeJeu) throws IOException {
        imageExplorateurbleu = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\Explorateur_Bleu.png"));
        resizedImageExplorateurbleu = resizeImage(imageExplorateurbleu, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imageExplorateurrouge = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\Explorateur_Rouge.png"));
        resizedImageExplorateurrouge = resizeImage(imageExplorateurrouge, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imageExplorateurjaune = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\Explorateur_Jaune.png"));
        resizedImageExplorateurjaune = resizeImage(imageExplorateurjaune, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imageExplorateurvert = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\Explorateur_Vert.png"));
        resizedImageExplorateurvert = resizeImage(imageExplorateurvert, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imageRequin = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\Requin.png"));
        resizedImageRequin = resizeImage(imageRequin, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imageSerpentdemer = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\Serpent_de_mer.png"));
        resizedImageSerpentdemer = resizeImage(imageSerpentdemer, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imageBaleine = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\Baleine.png"));
        resizedImageBaleine = resizeImage(imageBaleine, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);
        imageBarque = ImageIO.read(new File("C:\\Users\\ymell\\Documents\\GitHub\\the-island-game\\src\\view\\image\\Barque.png"));
        resizedImageBarque = resizeImage(imageBarque, NEW_IMAGE_WIDTH, NEW_IMAGE_HEIGHT);

        // Create and set up the window
        JFrame frame = new JFrame("Plateau de Jeu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        ImagePanel imagePanel = new ImagePanel(plateauDeJeu);
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
        private final List<SerpentDeMer> serpentDeMer;
        private final List<Requin> requin;
        private final List<Baleine> baleine;
        private final List<Barque> barque;
        private final Joueur[] joueurs;

        public ImagePanel(PlateauJeu plateauDeJeu) {
            this.serpentDeMer = plateauDeJeu.serpentDeMer;
            this.requin = plateauDeJeu.requins;
            this.baleine = plateauDeJeu.baleines;
            this.barque = plateauDeJeu.barques;
            this.joueurs = plateauDeJeu.joueurs;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g,Plateau_de_jeu);

            // Draw SerpentDeMer
            for (SerpentDeMer serpent : serpentDeMer) {
                int x = serpent.position.getX() * 20 + 300;
                int y = serpent.position.getY() * 30;
                g.drawImage(resizedImageSerpentdemer, x, y, null);
            }

            // Draw Requin
            for (Requin requin : requin) {
                int x = requin.position.getX() * 20 + 300;
                int y = requin.position.getY() * 30;
                g.drawImage(resizedImageRequin, x, y, null);
            }

            // Draw Baleine
            for (Baleine baleine : baleine) {
                int x = baleine.position.getX() * 20 + 300;
                int y = baleine.position.getY() * 30;
                g.drawImage(resizedImageBaleine, x, y, null);
            }

            // Draw Barque
            for (Barque barque : barque) {
                int x = barque.position.getX() * 20 + 300;
                int y = barque.position.getY() * 30;
                g.drawImage(resizedImageBarque, x, y, null);
            }

            // Draw Explorateurs
            for (int i = 0; i < joueurs.length; i++) {
                Joueur joueur = joueurs[i];
                BufferedImage explorerImage = getExplorerImageByIndex(i);
                for (Explorateur explorateur : joueur.explorateurs) {
                    int x = explorateur.position.getX() * 20 + 300;
                    int y = explorateur.position.getY() * 30;
                    g.drawImage(explorerImage, x, y, null);
                }
            }
        }

        private BufferedImage getExplorerImageByIndex(int index) {
            switch (index) {
                case 0:
                    return resizedImageExplorateurrouge;
                case 1:
                    return resizedImageExplorateurbleu;
                case 2:
                    return resizedImageExplorateurjaune;
                case 3:
                    return resizedImageExplorateurvert;
                default:
                    throw new IllegalArgumentException("Invalid player index");
            }
        }
    }

    public static void main(String[] args) {
        // Create and set up a PlateauJeu with sample data for demonstration
        PlateauJeu plateauDeJeu = new PlateauJeu();
        // Launch the application
        try {
            new FiltrePions(plateauDeJeu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
