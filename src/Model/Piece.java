package Model;

public class Piece {

    public void deplacer() {
    }
}


class Barque extends Piece {
    private Explorateur[] explorateurs = new Explorateur[3]; // null pas d'explorateur


    public Explorateur[] getExplorateurs() {
        return explorateurs;
    }

    public void setExplorateurs(Explorateur[] explorateurs) {
        this.explorateurs = explorateurs;
    }
}



class Pions extends Piece {
    private int statut; // 0 explorateur sur terre, 1 nageur, 2 sur barque, 3 arrivé, 4 mort
    private int deplacement; // Nombre de déplacement
    private int tresor; // Point pour gagner
    private Position positions;


    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    public int getTresor() {
        return tresor;
    }

    public void setTresor(int tresor) {
        this.tresor = tresor;
    }

    public Position getPositions() {
        return positions;
    }

    public void setPositions(Position positions) {
        this.positions = positions;
    }
}


