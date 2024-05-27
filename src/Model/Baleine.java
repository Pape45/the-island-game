package Model;

public class Baleine extends Piece {

    public static int RetournerBarque(PlateauJeu Plateau_de_jeu, int indice_baleine) {

        Position pos_baleine = Plateau_de_jeu.baleines.get(indice_baleine).getPosition();

        for (int j = 0; j < Plateau_de_jeu.barques.size(); j++) {
            Position pos_barque = Plateau_de_jeu.barques.get(j).getPosition();
            if (pos_baleine.equals(pos_barque)) {
                Plateau_de_jeu.barques.remove(j);
                for (int k = 0; k < Plateau_de_jeu.joueurs.length; k++) {
                    for (int l = 0; l < Plateau_de_jeu.joueurs[k].explorateurs.size(); l++) {
                        if(Position.isPositionsEquals(Plateau_de_jeu.joueurs[k].explorateurs.get(l).getPosition(),pos_barque)){
                            Plateau_de_jeu.joueurs[k].explorateurs.get(l).setStatut(1);
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
