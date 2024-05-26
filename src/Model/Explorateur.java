package Model;

public class Explorateur extends Piece {
    private int statut; // 0: sur terre, 1: nageur, 2: sur barque, 3: arrivé, 4: mort
    private int deplacement; // Nombre de déplacements
    private int tresor; // Points pour gagner
    
    public Explorateur(int statut, int deplacement, int tresor) {
        setStatut(statut);
        setdepacement(deplacement);
        setTresor(tresor);
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }


    public void setdepacement(int deplacement) {
        this.deplacement = deplacement;
    }

    public int getDeplacement() {
        return deplacement;
    }
    
    @Override
    public void setPosition(Position position) {
        this.position = position;
        this.deplacement=this.deplacement+1;
    }

    public int getTresor() {
        return tresor;
    }

    public void setTresor(int tresor) {
        this.tresor = tresor;
    }

    public static int get_explorateur(int tour, Position position_depart, PlateauJeu Plateau_de_jeu) {
        for (int i = 0; i < Plateau_de_jeu.joueurs[tour % 4].explorateurs.size(); i++) {
            if (Position.isPositionsEquals(Plateau_de_jeu.joueurs[tour % 4].explorateurs.get(i).getPosition(),position_depart)) {
                return i;
            }
        }
        return -1;
    }

}
