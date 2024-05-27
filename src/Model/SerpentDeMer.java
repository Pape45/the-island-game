package Model;

public class SerpentDeMer extends Piece {

    public static void RetournerBarqueMangerNageur(PlateauJeu Plateau_de_jeu, int indice_serpent_de_mer) {
        Position pos_serpent_de_mer = Plateau_de_jeu.serpentDeMer.get(indice_serpent_de_mer).getPosition();

        for (int j = 0; j < Plateau_de_jeu.barques.size(); j++) {
            Position pos_barque = Plateau_de_jeu.barques.get(j).getPosition();
            if (pos_serpent_de_mer.equals(pos_barque)) {
                Plateau_de_jeu.barques.remove(j);
            }
        }

        for (int j = 0; j < Plateau_de_jeu.joueurs.length-1; j++) {
            for (int k = 0; k < Plateau_de_jeu.joueurs[j].explorateurs.size(); k++) {
                Position pos_explorateur = Plateau_de_jeu.joueurs[j].explorateurs.get(k).getPosition();
                if(Position.isPositionsEquals(pos_serpent_de_mer,pos_explorateur)) {
                    Plateau_de_jeu.joueurs[j].explorateurs.remove(k);
                }
            }
        }
    }

    public SerpentDeMer(Position position) {
        this.position = position;
    }
}
