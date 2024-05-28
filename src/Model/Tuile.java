package Model;

import java.util.ArrayList;
import java.util.List;
import Controller.*;

public class Tuile {
    public int faceCachee;// 1=ajout requin  2=ajout baleine  3=ajout barque  4=tourbillon  5=volcan  6=dauphin  7=déplacer barque  8=déplacer Serpent de mer  9=déplacer requin  10=déplacer baleine  11=tuer requin  12=tuer baleine
    public int typeTuile; // 1 pour forêt, 2 pour montagne, 0 pour plage
    public Position position;

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

    public int getFaceCachee() {
        return faceCachee;
    }

    public static int[] nombreTuile(PlateauJeu Plateau_de_jeu){
        int[] nombreTuiles = new int[3];
        for(int i=0; i<Plateau_de_jeu.tuiles.size(); i++) {
            nombreTuiles[Plateau_de_jeu.tuiles.get(i).typeTuile]++;
        }
        //System.out.println(Plateau_de_jeu.tuiles.get(0).typeTuile);
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

    public static void retirerTuile(PlateauJeu Plateau_de_jeu) throws InterruptedException {
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

    public static void newRequinAction(PlateauJeu Plateau_de_jeu, Position position_tuile){
        int indice_requin=Plateau_de_jeu.requins.size();
        Plateau_de_jeu.requins.add(new Requin(position_tuile));
        Requin.MangerNageur(Plateau_de_jeu, indice_requin);
    }

    public static void newBaleineAction(PlateauJeu Plateau_de_jeu, Position position_tuile){
        Plateau_de_jeu.baleines.add(new Baleine(position_tuile));
    }

    public static void newBarqueAction(PlateauJeu Plateau_de_jeu, Position position_tuile){
        int indice_barque=Plateau_de_jeu.barques.size();
        int row=0,col=0;
        Plateau_de_jeu.barques.add(new Barque(position_tuile));
        for(int i=0;i<3;i++){
            for(int k=0;k<2;k++){
                Plateau_de_jeu.barques.get(indice_barque).setValue(i,k,-1);
            }
        }

        for (int i= 0; i < Plateau_de_jeu.joueurs.length; i++) {
            for (int k = 0; k < Plateau_de_jeu.joueurs[i].explorateurs.size(); k++) {
                col=0;
                if(Position.isPositionsEquals(position_tuile,Plateau_de_jeu.joueurs[i].explorateurs.get(k).getPosition())){
                    Plateau_de_jeu.joueurs[i].explorateurs.get(k).setStatut(2);
                    Plateau_de_jeu.barques.get(indice_barque).setValue(row,col,i);
                    col++;
                    Plateau_de_jeu.barques.get(indice_barque).setValue(row,col,k);
                    row++;
                }
            }
        }
    }

    public static void volcanAction(PlateauJeu Plateau_de_jeu){
        Plateau_de_jeu.setState_of_game(1);
    }

    public static void tourbillonAction(PlateauJeu Plateau_de_jeu, Position position_tuile){
        List<Position> voisins_tourbillon= Position.getNeighbors(position_tuile);
        for(int i=0; i<voisins_tourbillon.size(); i++){
            for(int j=0; j<Plateau_de_jeu.tuiles.size(); j++){
                if(!Position.isPositionsEquals(voisins_tourbillon.get(i),Plateau_de_jeu.tuiles.get(j).getPosition())){
                    for (int k = 0; k < Plateau_de_jeu.requins.size(); k++) {
                        if (Position.isPositionsEquals(voisins_tourbillon.get(i),Plateau_de_jeu.requins.get(k).getPosition())) {
                            Plateau_de_jeu.requins.remove(k);
                        }
                    }
                    for (int k = 0; k < Plateau_de_jeu.baleines.size(); k++) {
                        if (Position.isPositionsEquals(voisins_tourbillon.get(i),Plateau_de_jeu.baleines.get(k).getPosition())) {
                            Plateau_de_jeu.baleines.remove(k);
                        }
                    }
                    for (int k = 0; k < Plateau_de_jeu.serpentDeMer.size(); k++) {
                        if (Position.isPositionsEquals(voisins_tourbillon.get(i),Plateau_de_jeu.serpentDeMer.get(k).getPosition())) {
                            Plateau_de_jeu.serpentDeMer.remove(k);
                        }
                    }
                    for (int k = 0; k < Plateau_de_jeu.joueurs.length; k++) {
                        for (int l = 0; l < Plateau_de_jeu.joueurs[k].explorateurs.size(); l++) {
                            if(Position.isPositionsEquals(voisins_tourbillon.get(i),Plateau_de_jeu.joueurs[k].explorateurs.get(l).getPosition())) {
                                Plateau_de_jeu.joueurs[k].explorateurs.remove(l);
                            }
                        }
                    }
                    for (int k = 0; k < Plateau_de_jeu.barques.size(); k++) {
                        if (Position.isPositionsEquals(voisins_tourbillon.get(i),Plateau_de_jeu.barques.get(k).getPosition())) {
                            Plateau_de_jeu.barques.remove(k);
                        }
                    }
                }
            }
        }
    }

    public static void deplacerRequinAction(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        int numero_requin = -1;

        Position position_depart;
        Position position_arrivee;
        do {

            position_depart = Tour.choix_case();
            for (int i = 0; i < Plateau_de_jeu.requins.size(); i++) {
                if (Position.isPositionsEquals(Plateau_de_jeu.requins.get(i).getPosition(), position_depart)) {
                    numero_requin = i;
                }
            }
        } while (numero_requin == -1);


        do {
            position_arrivee = Tour.choix_case();
        } while (!caseIsEmpty(Plateau_de_jeu,position_arrivee));

        Plateau_de_jeu.requins.get(numero_requin).setPosition(position_arrivee);
        Requin.MangerNageur(Plateau_de_jeu, numero_requin);
    }

    public static void deplacerBaleineAction(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        int numero_baleine = -1;

        Position position_depart;
        Position position_arrivee;
        do {

            position_depart = Tour.choix_case();
            for (int i = 0; i < Plateau_de_jeu.baleines.size(); i++) {
                if (Position.isPositionsEquals(Plateau_de_jeu.baleines.get(i).getPosition(), position_depart)) {
                    numero_baleine = i;
                }
            }
        } while (numero_baleine == -1);

        do {
            position_arrivee = Tour.choix_case();
        } while (!caseIsEmpty(Plateau_de_jeu,position_arrivee));

        Plateau_de_jeu.baleines.get(numero_baleine).setPosition(position_arrivee);
        Baleine.RetournerBarque(Plateau_de_jeu, numero_baleine);
    }

    public static void deplacerSerpentDeMerAction(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        int numero_SerpentDeMer = -1;

        Position position_depart;
        Position position_arrivee;
        do {

            position_depart = Tour.choix_case();
            for (int i = 0; i < Plateau_de_jeu.serpentDeMer.size(); i++) {
                if (Position.isPositionsEquals(Plateau_de_jeu.serpentDeMer.get(i).getPosition(), position_depart)) {
                    numero_SerpentDeMer = i;
                }
            }
        } while (numero_SerpentDeMer == -1);

        do {
            position_arrivee = Tour.choix_case();
        } while (!caseIsEmpty(Plateau_de_jeu,position_arrivee));

        Plateau_de_jeu.serpentDeMer.get(numero_SerpentDeMer).setPosition(position_arrivee);
        SerpentDeMer.RetournerBarqueMangerNageur(Plateau_de_jeu, numero_SerpentDeMer);
    }

    public static boolean caseIsEmpty(PlateauJeu Plateau_de_jeu, Position position){
        for (int i = 0; i < Plateau_de_jeu.tuiles.size(); i++) {
            if (Position.isPositionsEquals(Plateau_de_jeu.tuiles.get(i).getPosition(), position)) {
                return false;
            }
        }
        for (int k = 0; k < Plateau_de_jeu.requins.size(); k++) {
            if (Position.isPositionsEquals(position, Plateau_de_jeu.requins.get(k).getPosition())) {
                return false;
            }
        }
        for (int k = 0; k < Plateau_de_jeu.baleines.size(); k++) {
            if (Position.isPositionsEquals(position, Plateau_de_jeu.baleines.get(k).getPosition())) {
                return false;
            }
        }
        for (int k = 0; k < Plateau_de_jeu.serpentDeMer.size(); k++) {
            if (Position.isPositionsEquals(position, Plateau_de_jeu.serpentDeMer.get(k).getPosition())) {
                return false;
            }
        }
        for (int k = 0; k < Plateau_de_jeu.joueurs.length; k++) {
            for (int l = 0; l < Plateau_de_jeu.joueurs[k].explorateurs.size(); l++) {
                if (Position.isPositionsEquals(position, Plateau_de_jeu.joueurs[k].explorateurs.get(l).getPosition())){
                    return false;
                }
            }
        }
        for (int k = 0; k < Plateau_de_jeu.barques.size(); k++) {
            if (Position.isPositionsEquals(position, Plateau_de_jeu.barques.get(k).getPosition())) {
                return false;
            }
        }
        return true;
    }

    public static void dauphinAction(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        int numero_explorateur;
        Position position_depart;
        Position position_arrivee;
        List<Position> voisins;

        int type_explorateur;
        do {
            position_depart = Tour.choix_case();
            numero_explorateur = Explorateur.get_explorateur(Plateau_de_jeu.tour, position_depart, Plateau_de_jeu);
            type_explorateur = Plateau_de_jeu.joueurs[Plateau_de_jeu.tour%4].explorateurs.get(numero_explorateur).getStatut();
        } while (type_explorateur != 1);

        for(int i=0;i<3;i++) {
            do {
                position_arrivee = Tour.choix_case();
                voisins = Position.getNeighbors(Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getPosition());
            } while (!voisins.contains(position_arrivee));

            Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).setPosition(position_arrivee);
        }
    }

    public static void deplacementBarqueAction(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        int numero_barque=-1;
        Position position_depart;
        Position position_arrivee;
        List<Position> voisins;

        do {
            position_depart = Tour.choix_case();
            for(int i=0; i<Plateau_de_jeu.barques.size(); i++){
                if(Position.isPositionsEquals(position_depart,Plateau_de_jeu.barques.get(i).getPosition())){
                    numero_barque=i;
                }
            }
        } while (!Barque.canMoveBarque(Plateau_de_jeu,numero_barque));

        int good;
        for(int k=0;k<3;k++) {
            do {
                position_arrivee = Tour.choix_case();
                good = 1;
                voisins = Position.getNeighbors(Plateau_de_jeu.barques.get(numero_barque).getPosition());
                for (int i = 0; i < Plateau_de_jeu.tuiles.size(); i++) {
                    if (Position.isPositionsEquals(Plateau_de_jeu.tuiles.get(i).getPosition(), position_arrivee)) {
                        good = -1;
                    }
                }
                if (!Position.isPositionContains(voisins, position_arrivee)) {
                    good = -1;
                }
            } while (good == -1);

            Plateau_de_jeu.barques.get(numero_barque).setPosition(position_arrivee);
            int joueur;
            int explorateur;
            for(int r=0; r<3; r++){
                if(Plateau_de_jeu.barques.get(numero_barque).getValue(r,0)!=-1){
                    joueur=Plateau_de_jeu.barques.get(numero_barque).getValue(r,0);
                    explorateur=Plateau_de_jeu.barques.get(numero_barque).getValue(r,1);
                    Plateau_de_jeu.joueurs[joueur].explorateurs.get(explorateur).setPosition(position_arrivee);
                }
            }
        }

    }

}