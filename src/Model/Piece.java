package Model;

import java.util.List;

public class Piece {
    private Position position;

    private boolean alreadyMovedThisRound;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean getAlreadyMovedThisRound() {
        return alreadyMovedThisRound;
    }

    public void setAlreadyMovedThisRound(boolean alreadyMovedThisRound) {
        this.alreadyMovedThisRound = alreadyMovedThisRound;
    }

    public String getStatus() {
        return "statut_de_piece";
    }

    public static List<String> getCaseWherePieceCanGo(String pieceType) {
        return List.of("type_de_case");
    }
}

class Barque extends Piece {
    private Explorateur[] explorateurs = new Explorateur[3]; // null si pas d'explorateur

    public Explorateur[] getExplorateurs() {
        return explorateurs;
    }

    public void setExplorateurs(Explorateur[] explorateurs) {
        this.explorateurs = explorateurs;
    }
}

class Explorateur extends Piece {
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
