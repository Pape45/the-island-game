package Model;

import javax.swing.JFrame;
import javax.swing.JPanel;
import view.*;

public class Main {
    public static void main(String[] args) {
        Init_Jeu jeu = new Init_Jeu();

        System.out.println("Jeu initialisé avec succès !");



        Explorateur explorateur = new Explorateur();
        jeu.getBarques()[0].addExplorateur(explorateur, 1, 3);
        jeu.getBarques()[0].afficherExplorateurs();


    }








}