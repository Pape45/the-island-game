package Model;


import java.util.ArrayList;
import java.util.List;



public class Joueur {
    public final Explorateur[] explorateur;
    public final List<Tuile> tuilesEnMain = new ArrayList<>();

    public int numeroJoueur;

    public Joueur() {
        explorateur= new Explorateur[10];
    }

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


}
