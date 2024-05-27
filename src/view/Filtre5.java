package view;

import Model.Joueur;

public class Filtre5 {

    public void filtre_5(HexagonalGrid hexagonalGrid, int numeroTour, Joueur joueur) {
        hexagonalGrid.updateTourAndPlayer(numeroTour, joueur.getNomJoueur());
    }
}
