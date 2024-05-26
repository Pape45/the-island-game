package Model;

import java.util.ArrayList;
import java.util.List;
import Controller.*;

public class Tuile {
    public int faceCachee;//
    public int typeTuile; // 1 pour forêt, 2 pour montagne, 0 pour plage
    public Position position;
    public final List<Tuile> tuiles = new ArrayList<>();

    public Tuile(int faceCachee, int typeTuile) {
        this.faceCachee = faceCachee;
        this.typeTuile = typeTuile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    static class TuilePlage extends Tuile {
        public TuilePlage(int faceCachee, int typeTuile) {
            super(faceCachee, 2);
        }
    }

    static class TuileForet extends Tuile {
        public TuileForet(int faceCachee, int typeTuile) {
            super(faceCachee, 0);
        }
    }

    static class TuileMontagne extends Tuile {
        public TuileMontagne(int faceCachee, int typeTuile) {
            super(faceCachee, 1);
        }
    }

    public static int[] nombreTuile(PlateauJeu Plateau_de_jeu){
        int[] nombreTuiles = new int[3];
        for(int i=0; i<Plateau_de_jeu.tuiles.size(); i++) {
            nombreTuiles[Plateau_de_jeu.tuiles.get(i).typeTuile]++;
        }
        return nombreTuiles;
    }

    public static boolean adjacentMer(PlateauJeu Plateau_de_jeu, Position position){
        List<Position> voisins= Position.getNeighbors(position);
        int tuile_voisines=0;
        for(int i=0; i< voisins.size(); i++){
            for(int j=0; j<Plateau_de_jeu.tuiles.size(); j++){
                if(Position.isPositionContains(voisins, position)){
                    tuile_voisines++;
                }
            }
        }
        return tuile_voisines != 6;
    }

    public static void retirerTuile(PlateauJeu Plateau_de_jeu){
        int tuile=-1;
        do {
            Position position_tuile = Tour.choix_case();
            int[] nombreTuiles = nombreTuile(Plateau_de_jeu);
            for(int i=0; i<Plateau_de_jeu.tuiles.size(); i++) {
                if (Position.isPositionsEquals(Plateau_de_jeu.tuiles.get(i).getPosition(), position_tuile)){
                    if(nombreTuiles[0]!=0 && Plateau_de_jeu.tuiles.get(i).typeTuile==0 && adjacentMer(Plateau_de_jeu,position_tuile)) {
                        tuile = i;
                    }
                    else if(nombreTuiles[0]==0 && Plateau_de_jeu.tuiles.get(i).typeTuile==1 && adjacentMer(Plateau_de_jeu,position_tuile)){
                        tuile = i;
                    }
                    else if(nombreTuiles[1]==0 && Plateau_de_jeu.tuiles.get(i).typeTuile==2 && adjacentMer(Plateau_de_jeu,position_tuile)){
                        tuile = i;
                    }
                }
            }
        }while (tuile==-1);

        Plateau_de_jeu.joueurs[Plateau_de_jeu.tour%4].tuilesEnMain.add(Plateau_de_jeu.tuiles.get(tuile));
        Plateau_de_jeu.tuiles.remove(tuile);
    }
}