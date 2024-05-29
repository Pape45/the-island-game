package Model;

import java.util.Iterator;

public class SerpentDeMer extends Piece {

    public static void RetournerBarqueMangerNageur(PlateauJeu Plateau_de_jeu, int indice_serpent_de_mer) {
        Position pos_serpent_de_mer = Plateau_de_jeu.serpentDeMer.get(indice_serpent_de_mer).getPosition();

        for (Iterator<Barque> it = Plateau_de_jeu.barques.iterator(); it.hasNext(); ) {
            Barque barque = it.next();
            Position pos_barque = barque.getPosition();
            if (Position.isPositionsEquals(pos_serpent_de_mer,pos_barque)) {
                it.remove();
                for (int k = 0; k < Plateau_de_jeu.joueurs.length; k++) {
                    for (Explorateur explorateur : Plateau_de_jeu.joueurs[k].explorateurs) {
                        if (Position.isPositionsEquals(explorateur.getPosition(), pos_barque)) {
                            explorateur.setStatut(1);
                        }
                    }
                }
            }
        }

        for (int j = 0; j < Plateau_de_jeu.joueurs.length; j++) {
            Iterator<Explorateur> iterator = Plateau_de_jeu.joueurs[j].explorateurs.iterator();
            while (iterator.hasNext()) {
                Explorateur explorateur = iterator.next();
                Position pos_explorateur = explorateur.getPosition();
                if (Position.isPositionsEquals(pos_serpent_de_mer, pos_explorateur) && explorateur.getStatut() == 1) {
                    iterator.remove();
                }
            }
        }
    }

    public SerpentDeMer(Position position) {
        this.position = position;
    }
}
