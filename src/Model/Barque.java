package Model;

public class Barque extends Piece {

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
