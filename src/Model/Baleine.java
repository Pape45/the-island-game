package Model;

import java.util.Iterator;

public class Baleine extends Piece {

    public static int RetournerBarque(PlateauJeu Plateau_de_jeu, int indice_baleine) {

        Position pos_baleine = Plateau_de_jeu.baleines.get(indice_baleine).getPosition();

        for (Iterator<Barque> it = Plateau_de_jeu.barques.iterator(); it.hasNext(); ) {
            Barque barque = it.next();
            Position pos_barque = barque.getPosition();
            if (Position.isPositionsEquals(pos_baleine,pos_barque)) {
                it.remove();
                System.out.println(Plateau_de_jeu.barques.size());
                for (int k = 0; k < Plateau_de_jeu.joueurs.length; k++) {
                    for (Explorateur explorateur : Plateau_de_jeu.joueurs[k].explorateurs) {
                        if (Position.isPositionsEquals(explorateur.getPosition(), pos_barque)) {
                            explorateur.setStatut(1);
                        }
                    }
                }
                return 1;
            }
        }
        return -1;
    }
    public Baleine(Position position) {
        this.position = position;
    }
}
