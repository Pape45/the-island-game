package Model;

public class Requin extends Piece {

    public static int MangerNageur(PlateauJeu Plateau_de_jeu, int indice_requin){
        int explorateur_mange=0;
        Position pos_requin = Plateau_de_jeu.requins.get(indice_requin).getPosition();
        for (int j = 0; j < Plateau_de_jeu.joueurs.length; j++) {
            for (int k = 0; k < Plateau_de_jeu.joueurs[j].explorateurs.size(); j++) {
                Position pos_explorateur = Plateau_de_jeu.joueurs[j].explorateurs.get(k).getPosition();
                if(pos_requin.equals(pos_explorateur)){
                    Plateau_de_jeu.joueurs[j].explorateurs.remove(k);
                    explorateur_mange++;
                }
            }
        }
        return explorateur_mange;
    }

    public Requin(Position position) {
        this.position = position;
    }
}
