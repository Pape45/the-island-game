
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Account Creation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(672, 620);
        frame.setLocationRelativeTo(null);
        JPanel panel =filtre1.create(frame);
        frame.add(panel);
        frame.setVisible(true);
    }
}