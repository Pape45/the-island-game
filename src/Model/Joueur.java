package Model;


import java.util.ArrayList;
import java.util.List;



public class Joueur {
    public final List<Explorateur> explorateurs = new ArrayList<>();
    public final List<Tuile> tuilesEnMain = new ArrayList<>();

    public int numeroJoueur;

    public void setNumeroJoueur(int numeroJoueur) {
        this.numeroJoueur = numeroJoueur;
    }

    public String toString() {
        return "Joueur:"+ numeroJoueur ;
    }

    public void ajouterTuile(Tuile tuile) {
        tuilesEnMain.add(tuile);
    }

    public List<Tuile> getTuilesEnMain() {
        return tuilesEnMain;
    }

    public static void CreatureMangeExplorateur(Init_Jeu Plateau_de_jeu, int numeroJoueur, int numeroExplorateur){
        Plateau_de_jeu.joueurs[numeroJoueur].explorateurs.remove(numeroExplorateur);
    }

}
