package Model;

public class Explorateur extends Piece {
    private int statut; // 0: sur terre, 1: nageur, 2: sur barque, 3: arrivé, 4: mort
    private int deplacement; // Nombre de déplacements
    private int tresor; // Points pour gagner

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

}
