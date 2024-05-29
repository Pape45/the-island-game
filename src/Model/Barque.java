package Model;

import Controller.Tour;
import view.CombinedGrid3;

import java.util.List;

public class Barque extends Piece {
    private static final int BARQUE_ROWS = 3;
    //3 places pour explorateur
    private static final int BARQUE_COLS = 2;
    //numéro joueur
    //numéro explorateur
    private static int[][] barque;

    public Barque(Position position) {
        barque = new int[BARQUE_ROWS][BARQUE_COLS];
        this.position = position;
    }

    public void setValue(int row, int col, int value) {
        barque[row][col] = value;
    }
    public int getValue(int row, int col) {return barque[row][col];}

    public static boolean canMoveBarque(PlateauJeu Plateau_de_jeu, int numero_barque) {
        int[] nb_explorateur = new int[4];
        int joueur;
        for(int row=0; row<BARQUE_ROWS; row++) {
            joueur=barque[row][0];
            nb_explorateur[joueur]++;
        }
        int max=0;
        for(int i=0; i<nb_explorateur.length; i++){
            if(nb_explorateur[i]>max){
                max=nb_explorateur[i];
            }
        }
        if(nb_explorateur[Plateau_de_jeu.tour%4]==max){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean adjacentTerre(PlateauJeu Plateau_de_jeu, Position position) {
        List<Position> voisins= Position.getNeighbors(position);
        int tuile_voisines=0;
        for(int i=0; i< voisins.size(); i++){
            for(int j=0; j<Plateau_de_jeu.tuiles.size(); j++){
                if(Position.isPositionContains(voisins, Plateau_de_jeu.tuiles.get(j).getPosition())){
                    tuile_voisines++;
                }
            }
        }
        return tuile_voisines != 0;
    }

    public static void init_barques(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        System.out.println("Posez les barques");
        Position position_barque;
        for(int i=0; i<8; i++){
            do{
                position_barque= Tour.choix_case();
            }while(!Tuile.caseIsEmpty(Plateau_de_jeu,position_barque) || !adjacentTerre(Plateau_de_jeu,position_barque));
            int indice_barque=Plateau_de_jeu.barques.size();
            Plateau_de_jeu.barques.add(new Barque(position_barque));
            Plateau_de_jeu.barques.get(indice_barque).setPosition(position_barque);
            System.out.println(Plateau_de_jeu.barques.size());
            for(int j=0;j<3;j++){
                for(int k=0;k<2;k++){
                    Plateau_de_jeu.barques.get(indice_barque).setValue(j,k,-1);
                }
            }
        }
        System.out.println("Pose des barques terminée");
    }
}
    





