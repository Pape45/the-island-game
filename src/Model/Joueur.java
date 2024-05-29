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
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 1));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 1));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 1));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 2));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 2));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 3));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 3));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 4));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 5));
        explorateurs.add(new Explorateur(new Position(-1,-1),0, 0, 6));
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

    public static boolean AlreadyMyExplorateurOnCase(PlateauJeu Plateau_de_jeu, Position position){
        for(int i=0; i<Plateau_de_jeu.joueurs[Plateau_de_jeu.tour%4].explorateurs.size(); i++){
            if(Position.isPositionsEquals(Plateau_de_jeu.joueurs[Plateau_de_jeu.tour%4].explorateurs.get(i).getPosition(),position)){
                for(int k=0; k<Plateau_de_jeu.barques.size();k++){
                    if(Position.isPositionsEquals(position, Plateau_de_jeu.barques.get(k).getPosition())){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

}
