package Controller;
import Model.*;

import java.util.List;

public class Tour {


    public void tour(Init_Jeu Plateau_de_jeu, int tour){

    }

    public void deplacerCreature(Init_Jeu Plateau_de_jeu) {
        int resultat_de = Plateau_de_jeu.de.lancer();//de.lancer doit retourner un chiffre 0=serpent de mer   1=requin   2=baleine
        int numero_creature=-1;
        int good;

        //SERPENT DE MER//
        if (resultat_de==0){

            Position position_depart;
            Position position_arrivee;
            do {
                position_depart = choix_case();
                for (int i = 0; i < Plateau_de_jeu.serpentDeMer.size(); i++) {
                    if (Plateau_de_jeu.serpentDeMer.get(i).getPosition().equals(position_depart)) {
                        numero_creature = i;
                    }
                }
            } while (numero_creature == -1);

            do {
                position_arrivee = choix_case();
                good = 1;
                List<Position> voisins = Position.getNeighbors(position_depart);
                for (int i = 0; i < Plateau_de_jeu.tuiles.size(); i++) {
                    if (Plateau_de_jeu.tuiles.get(i).getPosition().equals(position_arrivee) || !voisins.contains(position_arrivee)) {
                        good = -1;
                    }
                }
            } while (good == -1);

            Plateau_de_jeu.serpentDeMer.get(numero_creature).setPosition(position_arrivee);
            SerpentDeMer.RetournerBarqueMangerNageur(Plateau_de_jeu, numero_creature);
        }

        //REQUIN//
        else if (resultat_de==1) {
            int deplacement=0;
            int explorateur_mange;
            do {
                Position position_depart;
                Position position_arrivee;
                do {
                    position_depart = choix_case();
                    for (int i = 0; i < Plateau_de_jeu.requins.size(); i++) {
                        if (Plateau_de_jeu.requins.get(i).getPosition().equals(position_depart)) {
                            numero_creature = i;
                        }
                    }
                } while (numero_creature == -1);

                do {
                    position_arrivee = choix_case();
                    good = 1;
                    List<Position> voisins = Position.getNeighbors(position_depart);
                    for (int i = 0; i < Plateau_de_jeu.tuiles.size(); i++) {
                        if (Plateau_de_jeu.tuiles.get(i).getPosition().equals(position_arrivee) || !voisins.contains(position_arrivee)) {
                            good = -1;
                        }
                    }
                } while (good == -1);

                deplacement++;
                Plateau_de_jeu.requins.get(numero_creature).setPosition(position_arrivee);
                explorateur_mange = Requin.MangerNageur(Plateau_de_jeu, numero_creature);
            }while(deplacement<2 && explorateur_mange==0);
        }

        //BALEINE//
        else {
            int deplacement = 0;
            int barque_retournee;
            do {
                Position position_depart;
                Position position_arrivee;
                do {
                    position_depart = choix_case();
                    for (int i = 0; i < Plateau_de_jeu.baleines.size(); i++) {
                        if (Plateau_de_jeu.baleines.get(i).getPosition().equals(position_depart)) {
                            numero_creature = i;
                        }
                    }
                } while (numero_creature == -1);

                do {
                    position_arrivee = choix_case();
                    good = 1;
                    List<Position> voisins = Position.getNeighbors(position_depart);
                    for (int i = 0; i < Plateau_de_jeu.tuiles.size(); i++) {
                        if (Plateau_de_jeu.tuiles.get(i).getPosition().equals(position_arrivee) || !voisins.contains(position_arrivee)) {
                            good = -1;
                        }
                    }
                } while (good == -1);

                deplacement++;
                Plateau_de_jeu.baleines.get(numero_creature).setPosition(position_arrivee);
                barque_retournee = Baleine.RetournerBarque(Plateau_de_jeu, numero_creature);

            }while(deplacement<3 && barque_retournee==-1);
        }
    }

    public void deplacer_explorateur(Init_Jeu Plateau_de_jeu) {
        for (int i = 0; i < 3; i++) {

            int numero_explorateur;
            Position position_depart;
            Position position_arrivee;
            List<Position> voisins;

            do {
                position_depart = choix_case();
                numero_explorateur = Explorateur.get_explorateur(Plateau_de_jeu.tour,position_depart,Plateau_de_jeu);
            } while (numero_explorateur == -1);

            do {
                position_arrivee = choix_case();
                voisins = Position.getNeighbors(position_depart);
            } while(!voisins.contains(position_arrivee));

            int type_explorateur = Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getStatut();
            Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).setPosition(position_arrivee);
            if( type_explorateur==1){
                break;
            }
        }
    }

}
