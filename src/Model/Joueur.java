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

    public static int[] getJoueurTresors(PlateauJeu Plateau_de_jeu){
        int[] tresors= new int[Plateau_de_jeu.joueurs.length];
        for(int i=0; i<Plateau_de_jeu.joueurs.length;i++){
            for(int k=0; k<Plateau_de_jeu.joueurs[i].explorateurs.size(); k++){
                if(Plateau_de_jeu.joueurs[i].explorateurs.get(i).getStatut()==3){
                    tresors[i]+=Plateau_de_jeu.joueurs[i].explorateurs.get(i).getTresor();
                }
            }
        }
        return tresors;
    }

}
