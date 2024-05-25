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
                for (int i = 0; i < Plateau_de_jeu.tuiles.length; i++) {
                    if (Plateau_de_jeu.tuiles[i].getPosition().equals(position_arrivee) || !voisins.contains(position_arrivee)) {
                        good = -1;
                    }
                }
            } while (good == -1);

            Plateau_de_jeu.serpentDeMer.get(numero_creature).setPosition(position_arrivee);
            SerpentDeMerAction(Plateau_de_jeu, numero_creature);
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
                    for (int i = 0; i < Plateau_de_jeu.tuiles.length; i++) {
                        if (Plateau_de_jeu.tuiles[i].getPosition().equals(position_arrivee) || !voisins.contains(position_arrivee)) {
                            good = -1;
                        }
                    }
                } while (good == -1);

                deplacement++;
                Plateau_de_jeu.requins.get(numero_creature).setPosition(position_arrivee);
                explorateur_mange = RequinAction(Plateau_de_jeu, numero_creature);
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
                    for (int i = 0; i < Plateau_de_jeu.tuiles.length; i++) {
                        if (Plateau_de_jeu.tuiles[i].getPosition().equals(position_arrivee) || !voisins.contains(position_arrivee)) {
                            good = -1;
                        }
                    }
                } while (good == -1);

                deplacement++;
                Plateau_de_jeu.baleines.get(numero_creature).setPosition(position_arrivee);
                barque_retournee = BaleineAction(Plateau_de_jeu, numero_creature);

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
                numero_explorateur = get_explorateur(Plateau_de_jeu.tour,position_depart,Plateau_de_jeu);
            } while (numero_explorateur == -1);

            do {
                position_arrivee = choix_case();
                voisins = Position.getNeighbors(position_depart);
            } while(!voisins.contains(position_arrivee));

            Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).deplacer(position_arrivee);
            Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).setAlreadyMovedThisRound(true);

        }
    }

    public int get_explorateur (int tour, Position position_depart, Init_Jeu Plateau_de_jeu) {
        for (int i = 0; i < Plateau_de_jeu.joueurs[tour % 4].explorateurs.size(); i++) {
            if (Plateau_de_jeu.joueurs[tour % 4].explorateurs.get(i).getPosition().equals(position_depart)) {
                return i;
            }
        }
        return -1;
    }
    
    public int BaleineAction(Init_Jeu Plateau_de_jeu, int indice_baleine) {

        Position pos_baleine = Plateau_de_jeu.baleines.get(indice_baleine).getPosition();

        for (int j = 0; j < Plateau_de_jeu.barques.size(); j++) {
            Position pos_barque = Plateau_de_jeu.barques.get(j).getPosition();
            if (pos_baleine.equals(pos_barque)) {
                Plateau_de_jeu.barques.remove(j);
                return 1;
            }
        }
        return -1;
    }

    public int RequinAction(Init_Jeu Plateau_de_jeu, int indice_requin){
        int explorateur_mange=0;
        Position pos_requin = Plateau_de_jeu.requins.get(indice_requin).getPosition();
        for (int j = 0; j < Plateau_de_jeu.joueurs.length; j++) {
            for (int k = 0; k < Plateau_de_jeu.joueurs[j].explorateurs.size(); j++) {
                Position pos_explorateur = Plateau_de_jeu.joueurs[j].explorateurs.get(k).getPosition();
                if(pos_requin.equals(pos_explorateur)){
                    Joueur.CreatureMangeExplorateur(Plateau_de_jeu, j, k);
                    explorateur_mange++;
                }
            }
        }
        return explorateur_mange;
    }

    public void SerpentDeMerAction(Init_Jeu Plateau_de_jeu, int indice_serpent_de_mer) {
        Position pos_serpent_de_mer = Plateau_de_jeu.serpentDeMer.get(indice_serpent_de_mer).getPosition();

        for (int j = 0; j < Plateau_de_jeu.barques.size(); j++) {
            Position pos_barque = Plateau_de_jeu.barques.get(j).getPosition();
            if (pos_serpent_de_mer.equals(pos_barque)) {
                Plateau_de_jeu.barques.remove(j);
            }
        }

        for (int j = 0; j < Plateau_de_jeu.joueurs.length; j++) {
            for (int k = 0; k < Plateau_de_jeu.joueurs[j].explorateurs.size(); j++) {
               Position pos_explorateur = Plateau_de_jeu.joueurs[j].explorateurs.get(k).getPosition();
               if(pos_serpent_de_mer.equals(pos_explorateur)) {
                   Joueur.CreatureMangeExplorateur(Plateau_de_jeu, j, k);
               }
            }
        }
    }
}



