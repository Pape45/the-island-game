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

    private final Explorateur[][] explorateurs;

    public Barque() {
        explorateurs = new Explorateur[4][10];
    }

    public Explorateur[][] getExplorateurs() {
        return explorateurs;
    }

    public void addExplorateur(Explorateur explorateur, int joueur, int numero) {
        if (joueur >= 0 && joueur < 4 && numero >= 0 && numero < 10) {
            explorateurs[joueur][numero] = explorateur;
            explorateur.setStatut(2);
        }
    }

    public void afficherExplorateurs() {
        for (int joueur = 0; joueur < explorateurs.length; joueur++) {
            for (int numero = 0; numero < explorateurs[joueur].length; numero++) {
                if (explorateurs[joueur][numero] != null) {
                    System.out.println("Joueur " + joueur + ", Explorateur " + numero + ": statut = " + explorateurs[joueur][numero].getStatut());
                }
            }
        }
    }

    private int numeroBarque;

    public int getNumeroBarque() {
        return numeroBarque;
    }

    public void setNumeroBarque(int numeroBarque) {
        this.numeroBarque = numeroBarque;
    }

    public String toString() {
        return "Barque= "+ numeroBarque;
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
