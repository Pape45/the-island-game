package view;

import Model.Joueur;

public class Filtre5 {

    private HexagonalGrid hexagonalGrid; // Stockage de la référence

    public Filtre5(HexagonalGrid hexagonalGrid) {
        this.hexagonalGrid = hexagonalGrid;
    }

    public void updateTourAndPlayer(int numeroTour, Joueur joueur) {
        hexagonalGrid.updateTourAndPlayer(numeroTour, joueur.getNomJoueur());
    }
}
