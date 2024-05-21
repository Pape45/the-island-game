package view;
import javax.swing.*;
import java.awt.*;

public class filtre1 {
    public static JPanel create(JFrame frame) {
        JPanel panel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("view/photo/plateau.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(672, 596, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        JLabel photoLabel = new JLabel(imageIcon);
        panel.add(photoLabel);

      
        return panel;
    }
}

