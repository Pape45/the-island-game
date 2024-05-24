package Model;

import java.util.List;

public class Piece {
    public Position position;

    public boolean alreadyMovedThisRound;

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

    public static void CreatureMangeExplorateur(Init_Jeu Plateau_de_jeu, int numeroJoueur, int numeroExplorateur){
        Plateau_de_jeu.joueurs[numeroJoueur].explorateur.remove(numeroExplorateur);
    }
}


