package Model;


import java.util.ArrayList;
import java.util.List;



public class Joueur {
    public final List<Explorateur> explorateurs = new ArrayList<>();
    public final List<Tuile> tuilesEnMain = new ArrayList<>();
  

    public Joueur() {
        initialiserExplorateurs();
    }

    public void initialiserExplorateurs() {
        explorateurs.add(new Explorateur(0, 0, 1));
        explorateurs.add(new Explorateur(0, 0, 1));
        explorateurs.add(new Explorateur(0, 0, 1));
        explorateurs.add(new Explorateur(0, 0, 2));
        explorateurs.add(new Explorateur(0, 0, 2));
        explorateurs.add(new Explorateur(0, 0, 3));
        explorateurs.add(new Explorateur(0, 0, 3));
        explorateurs.add(new Explorateur(0, 0, 4));
        explorateurs.add(new Explorateur(0, 0, 5));
        explorateurs.add(new Explorateur(0, 0, 6));
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
