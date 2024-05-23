package Model;

import javax.swing.JFrame;
import javax.swing.JPanel;
import view.*;

public class Main {
    public static void main(String[] args) {
        Init_Jeu jeu = new Init_Jeu();

        System.out.println("Jeu initialisé avec succès !");

        System.out.println("Nombre de joueurs : " + jeu.getJoueurs().length);
        System.out.println("Nombre de barques : " + jeu.getBarques().length);
        System.out.println("Nombre de requins : " + jeu.getRequins().length);
        System.out.println("Nombre de baleines : " + jeu.getBaleines().length);
        System.out.println("Nombre de serpents de mer : " + jeu.getSerpentDeMer().length);

        Explorateur explorateur = new Explorateur();
        jeu.getBarques()[0].addExplorateur(explorateur, 1, 3);

        jeu.getBarques()[0].afficherExplorateurs();


    }








}