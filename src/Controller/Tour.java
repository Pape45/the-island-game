package Controller;
import Model.*;

import java.util.List;

public class Tour {

}

public class DeplacerPiece{

    public void deplacerCreature() {
        int de = de.lancer();//de.lancer doit retourner un chiffre 0=serpent de mer   1=requin   2=baleine
        int numero_creature=-1;
        int tuile;
        if (de==0){
            Position position_depart;
            Position position_arrivee;
            do{
                position_depart = choix_case();
                for(int i=0;i<plateau_de_jeu.serpent_de_mer.length ;i++) {
                    if (plateau_de_jeu.serpent_de_mer[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while(numero_creature==-1);

            do{
                position_arrivee=choix_case();
                tuile=1;
                for (int i = 0; i < plateau_de_jeu.tuile.length; i++) {
                    if (plateau_de_jeu.tuile[i].getPosition().equals(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            plateau_de_jeu.serpent_de_mer[numero_creature].setPosition(position_arrivee);
        }

        else if (de==1){
            Position position_depart;
            Position position_arrivee;
            do {
                position_depart=choix_case();
                for(int i=0;i<plateau_de_jeu.requin.length ;i++) {
                    if (plateau_de_jeu.requin[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while (numero_creature==-1);

            do{
                position_arrivee=choix_case();
                tuile=1;
                for (int i = 0; i < plateau_de_jeu.tuile.length; i++) {
                    if (plateau_de_jeu.tuile[i].getPosition().equals(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            plateau_de_jeu.requin[numero_creature].setPosition(position_arrivee);

        }else {
            Position position_depart;
            Position position_arrivee;
            do{
                position_depart=choix_case();
                for(int i=0;i<plateau_de_jeu.baleine.length ;i++) {
                    if (plateau_de_jeu.baleine[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while (numero_creature==-1);

            do{
                position_arrivee=choix_case();
                tuile=1;
                List<Position> voisins=Position.getNeighbors(position_depart);
                for (int i = 0; i < plateau_de_jeu.tuile.length; i++) {
                    if (plateau_de_jeu.tuile[i].getPosition().equals(position_arrivee) || !voisins.contains(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            plateau_de_jeu.baleine[numero_creature].setPosition(position_arrivee);
        }
    }

    public void deplacer_explorateur(int tour) {
        for (int i = 0; i < 3; i++) {

            int numero_explorateur;
            Position position_depart;
            Position position_arrivee;
            List<Position> voisins;

            do {
                position_depart = choix_case();
                numero_explorateur = get_explorateur(tour,position_depart);
            } while (numero_explorateur == -1);

            do {
                position_arrivee = choix_case();
                voisins = Position.getNeighbors(position_depart);
            } while(!voisins.contains(position_arrivee));

            plateau_de_jeu.joueur[tour % 4].explorateur[numero_explorateur].deplacer(position_arrivee);
            plateau_de_jeu.joueur[tour % 4].explorateur[numero_explorateur].setAlreadyMovedThisRound(true);
        }
    }

    public int get_explorateur (int tour, Position position_depart) {
        for (int i = 0; i < plateau_de_jeu.joueur[tour % 4].explorateur.length; i++) {
            if (plateau_de_jeu.joueur[tour % 4].explorateur[i].getPosition().equals(position_depart)) {
                return i;
            }
        }
        return -1;
    }
}