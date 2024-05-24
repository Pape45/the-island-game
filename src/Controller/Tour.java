package Controller;
import Model.*;

import java.util.List;

public class Tour {


    public void tour(Init_Jeu Plateau_de_jeu, int tour){

    }

    public void deplacerCreature(Init_Jeu Plateau_de_jeu) {
        int resultat_de = Plateau_de_jeu.de.lancer();//de.lancer doit retourner un chiffre 0=serpent de mer   1=requin   2=baleine
        int numero_creature=-1;
        int tuile;
        if (resultat_de==0){
            Position position_depart;
            Position position_arrivee;
            do{
                position_depart = choix_case();
                for(int i=0;i<Plateau_de_jeu.serpentDeMer.length ;i++) {
                    if (Plateau_de_jeu.serpentDeMer[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while(numero_creature==-1);

            do{
                position_arrivee=choix_case();
                tuile=1;
                for (int i = 0; i < Plateau_de_jeu.tuiles.length; i++) {
                    if (Plateau_de_jeu.tuiles[i].getPosition().equals(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            Plateau_de_jeu.serpentDeMer[numero_creature].setPosition(position_arrivee);
        }

        else if (resultat_de==1){
            Position position_depart;
            Position position_arrivee;
            do {
                position_depart=choix_case();
                for(int i=0;i<Plateau_de_jeu.requins.length ;i++) {
                    if (Plateau_de_jeu.requins[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while (numero_creature==-1);

            do{
                position_arrivee=choix_case();
                tuile=1;
                for (int i = 0; i < Plateau_de_jeu.tuiles.length; i++) {
                    if (Plateau_de_jeu.tuiles[i].getPosition().equals(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            Plateau_de_jeu.requins[numero_creature].setPosition(position_arrivee);

        }else {
            Position position_depart;
            Position position_arrivee;
            do{
                position_depart=choix_case();
                for(int i=0;i<Plateau_de_jeu.baleines.length ;i++) {
                    if (Plateau_de_jeu.baleines[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while (numero_creature==-1);

            do{
                position_arrivee=choix_case();
                tuile=1;
                List<Position> voisins=Position.getNeighbors(position_depart);
                for (int i = 0; i < Plateau_de_jeu.tuiles.length; i++) {
                    if (Plateau_de_jeu.tuiles[i].getPosition().equals(position_arrivee) || !voisins.contains(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            Plateau_de_jeu.baleines[numero_creature].setPosition(position_arrivee);
        }
    }

    public void deplacer_explorateur(int tour, Init_Jeu Plateau_de_jeu) {
        for (int i = 0; i < 3; i++) {

            int numero_explorateur;
            Position position_depart;
            Position position_arrivee;
            List<Position> voisins;

            do {
                position_depart = choix_case();
                numero_explorateur = get_explorateur(tour,position_depart,Plateau_de_jeu);
            } while (numero_explorateur == -1);

            do {
                position_arrivee = choix_case();
                voisins = Position.getNeighbors(position_depart);
            } while(!voisins.contains(position_arrivee));

            Plateau_de_jeu.joueurs[tour % 4].explorateur[numero_explorateur].deplacer(position_arrivee);
            Plateau_de_jeu.joueurs[tour % 4].explorateur[numero_explorateur].setAlreadyMovedThisRound(true);
        }
    }

    public int get_explorateur (int tour, Position position_depart, Init_Jeu Plateau_de_jeu) {
        for (int i = 0; i < Plateau_de_jeu.joueurs[tour % 4].explorateur.length; i++) {
            if (Plateau_de_jeu.joueurs[tour % 4].explorateur[i].getPosition().equals(position_depart)) {
                return i;
            }
        }
        return -1;
    }




    public void CreatureAction(Init_Jeu Plateau_de_jeu) {

        for (int i = 0; i < Plateau_de_jeu.baleines.length; i++) {  //pour chaque baleine sur le terrain

            Position pos_baleine = Plateau_de_jeu.baleines[i].getPosition();   //position du baleine
            List<Position> voisins = Position.getNeighbors(pos_baleine);     //liste de position des cases voisines

            for (int j = 0; j < Plateau_de_jeu.barques.length; j++) {         // pour chaque barque

                if (voisins.contains(Plateau_de_jeu.barques[j].getPosition())) {    //si la liste de position des cases voisines contient la position d'une barque
                    //Plateau_de_jeu.barques.remove(j);                                     // la barque est supprimée (retournée)
                }
            }
        }

        for (int i = 0; i < Plateau_de_jeu.requins.length; i++) {  //pour chaque requin sur le terrain

            Position pos_requin = Plateau_de_jeu.requins[i].getPosition();   //position du requin
            List<Position> voisins = Position.getNeighbors(pos_requin);     //liste de position des cases voisines

            for (int j = 0; j < Plateau_de_jeu.joueurs.length; j++) {         // pour chaque joueur

                for (int k = 0; k < Plateau_de_jeu.joueurs[j].explorateur.length; j++) {      //pour chaque explorateur

                    if (voisins.contains(Plateau_de_jeu.joueurs[j].explorateur[k].getPosition())) {    //si la liste de position des cases voisines contient la position d'un explorateur

                        //Plateau_de_jeu.joueurs[j].explorateur.remove(k);   // le plateau_de_jeu.joueur[j].explorateur[k] est supprimé (mort)
                    }
                }
            }
        }

        for (int i = 0; i < Plateau_de_jeu.serpentDeMer.length; i++) {  //pour chaque serpent de mer sur le terrain

            Position pos_serpent_de_mer = Plateau_de_jeu.serpentDeMer[i].getPosition();   //position du requin
            List<Position> voisins = Position.getNeighbors(pos_serpent_de_mer);             //liste de position des cases voisines

            for (int j = 0; j < Plateau_de_jeu.barques.length; j++) {         // pour chaque barque

                if (voisins.contains(Plateau_de_jeu.barques[j].getPosition())) {    //si la liste de position des cases voisines contient la position d'une barque
                    //Plateau_de_jeu.barques.remove(j);                                     // la barque est supprimée (retournée)
                }
            }

            for (int j = 0; j < Plateau_de_jeu.joueurs.length; j++) {         // pour chaque joueur

                for (int k = 0; k < Plateau_de_jeu.joueurs[j].explorateur.length; j++) {      //pour chaque explorateur

                    if (voisins.contains(Plateau_de_jeu.joueurs[j].explorateur[k].getPosition())) {    //si la liste de position des cases voisines contient la position d'un explorateur
                        //Plateau_de_jeu.joueurs[j].explorateur.remove(k);   // le plateau_de_jeu.joueur[j].explorateur[k] est supprimé (mort)
                    }
                }
            }
        }
    }

}



