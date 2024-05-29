package Model;

import java.util.Iterator;

public class Requin extends Piece {

    public static int MangerNageur(PlateauJeu Plateau_de_jeu, int indice_requin){
        int explorateur_mange=0;
        Position pos_requin = Plateau_de_jeu.requins.get(indice_requin).getPosition();
        for (int j = 0; j < Plateau_de_jeu.joueurs.length; j++) {
            Iterator<Explorateur> iterator = Plateau_de_jeu.joueurs[j].explorateurs.iterator();
            while (iterator.hasNext()) {
                Explorateur explorateur = iterator.next();
                Position pos_explorateur = explorateur.getPosition();
                if (Position.isPositionsEquals(pos_requin,pos_explorateur) && explorateur.getStatut() == 1) {
                    iterator.remove();
                    explorateur_mange++;
                }
            }
        }
        return  explorateur_mange;
    }

    public Requin(Position position) {
        this.position = position;
    }
}
