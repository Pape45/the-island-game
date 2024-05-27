package Model;

public class Baleine extends Piece {

    public static int RetournerBarque(PlateauJeu Plateau_de_jeu, int indice_baleine) {

        Position pos_baleine = Plateau_de_jeu.baleines.get(indice_baleine).getPosition();

        for (int j = 0; j < Plateau_de_jeu.barques.size(); j++) {
            Position pos_barque = Plateau_de_jeu.barques.get(j).getPosition();
            if (pos_baleine.equals(pos_barque)) {
                Plateau_de_jeu.barques.remove(j);
                return 1;
            }
        }
        return -1;
    }
    public Baleine(Position position) {
        this.position = position;
    }
}
