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

    public void setPosition(Position newPosition) {
        this.position = newPosition;
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

    public static List<Position> init_position_tuiles(){
        List<Position> position_tuiles = new ArrayList<>();
        //LIGNE 1
        Position position1= new Position(-3,3);
        position_tuiles.add(position1);
        Position position2= new Position(-1,3);
        position_tuiles.add(position2);
        Position position3= new Position(1,3);
        position_tuiles.add(position3);
        Position position4= new Position(3,3);
        position_tuiles.add(position4);

        //LIGNE 2
        Position position5= new Position(-4,4);
        position_tuiles.add(position5);
        Position position6= new Position(-2,4);
        position_tuiles.add(position6);
        Position position7= new Position(0,4);
        position_tuiles.add(position7);
        Position position8= new Position(-2,4);
        position_tuiles.add(position8);
        Position position9= new Position(-4,4);
        position_tuiles.add(position9);

        //LIGNE 3
        Position position10 = new Position(-7,5);
        position_tuiles.add(position10);
        Position position11 = new Position(-5,5);
        position_tuiles.add(position11);
        Position position12 = new Position(-3,5);
        position_tuiles.add(position12);
        Position position13 = new Position(-1,5);
        position_tuiles.add(position13);
        Position position14 = new Position(1,5);
        position_tuiles.add(position14);
        Position position15 = new Position(3,5);
        position_tuiles.add(position15);
        Position position16 = new Position(5,5);
        position_tuiles.add(position16);
        Position position17 = new Position(7,5);
        position_tuiles.add(position17);

        //LIGNE 4
        Position position18 = new Position(-6,6);
        position_tuiles.add(position18);
        Position position19 = new Position(-4,6);
        position_tuiles.add(position19);
        Position position20 = new Position(-2,6);
        position_tuiles.add(position20);
        Position position21 = new Position(2,6);
        position_tuiles.add(position21);
        Position position22 = new Position(4,6);
        position_tuiles.add(position22);
        Position position23 = new Position(6,6);
        position_tuiles.add(position23);

        //LIGNE 5
        Position position24 = new Position(-7,7);
        position_tuiles.add(position24);
        Position position25 = new Position(-5,7);
        position_tuiles.add(position25);
        Position position26 = new Position(-3,7);
        position_tuiles.add(position26);
        Position position27 = new Position(-1,7);
        position_tuiles.add(position27);
        Position position28 = new Position(1,7);
        position_tuiles.add(position28);
        Position position29 = new Position(3,7);
        position_tuiles.add(position29);
        Position position30 = new Position(5,7);
        position_tuiles.add(position30);
        Position position31 = new Position(7,7);
        position_tuiles.add(position31);

        //LIGNE 6
        Position position32 = new Position(-4,8);
        position_tuiles.add(position32);
        Position position33 = new Position(-2,8);
        position_tuiles.add(position33);
        Position position34 = new Position(0,8);
        position_tuiles.add(position34);
        Position position35 = new Position(2,8);
        position_tuiles.add(position35);
        Position position36 = new Position(7,8);
        position_tuiles.add(position36);

        //LIGNE 7
        Position position37 = new Position(-3,9);
        position_tuiles.add(position37);
        Position position38 = new Position(-1,9);
        position_tuiles.add(position38);
        Position position39 = new Position(1,9);
        position_tuiles.add(position39);
        Position position40 = new Position(3,9);
        position_tuiles.add(position40);

        return position_tuiles;
    }
}