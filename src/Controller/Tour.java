package Controller;
import Model.*;
import view.HexagonalGrid;

import java.util.List;

public class Tour {
    private static HexagonalGrid hexagonalGrid;

    public Tour(HexagonalGrid hexagonalGrid) {
        this.hexagonalGrid = hexagonalGrid;
    }

    public void tour(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        System.out.println("Tour commence");
        //Position clickedPosition = hexagonalGrid.waitForClick();
        //System.out.println("Position clicked: " + clickedPosition.getX());

        deplacerCreature(Plateau_de_jeu);
        deplacer_explorateur(Plateau_de_jeu);
        Plateau_de_jeu.tour = Plateau_de_jeu.tour + 1;
    }

    public void deplacerCreature(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        int resultat_de = 0; // de.lancer doit retourner un chiffre 0=serpent de mer 1=requin 2=baleine
        int numero_creature = -1;
        int good;
        //SERPENT DE MER//
        if (resultat_de==0) {
            Position position_depart;
            Position position_arrivee;
            do {
                position_depart = hexagonalGrid.waitForClick();
                //System.out.println("Position clicked: " + position_depart.getX());
                for (int i = 0; i < Plateau_de_jeu.serpentDeMer.size(); i++) {
                    if (Position.isPositionsEquals(Plateau_de_jeu.serpentDeMer.get(i).getPosition(), position_depart)) {
                        numero_creature = i;
                        break;
                    }
                }
            } while (numero_creature == -1);

            System.out.println("Monstre sélectionné");

            do {
                position_arrivee = choix_case();
                good = 1;
                List<Position> voisins = Position.getNeighbors(position_depart);
                for (int i = 0; i < Plateau_de_jeu.tuiles.size(); i++) {
                    if (Position.isPositionsEquals(Plateau_de_jeu.tuiles.get(i).getPosition(), position_arrivee)) {
                        good = -1;
                    }
                }
                if (!Position.isPositionContains(voisins, position_arrivee)) {
                    good = -1;
                }
            } while (good == -1);
            System.out.println("Position arrivée sélectionné");
            Plateau_de_jeu.serpentDeMer.get(numero_creature).setPosition(position_arrivee);

            SerpentDeMer.RetournerBarqueMangerNageur(Plateau_de_jeu, numero_creature);
        }

        // REQUIN //
        else if (resultat_de == 1) {
            int deplacement = 0;
            int explorateur_mange;
            Position position_depart = null;
            do {

                Position position_arrivee;
                if (deplacement==0) {
                    do {
                        position_depart = choix_case();
                        for (int i = 0; i < Plateau_de_jeu.requins.size(); i++) {
                            if (Position.isPositionsEquals(Plateau_de_jeu.requins.get(i).getPosition(), position_depart)) {
                                numero_creature = i;
                            }
                        }
                    } while (numero_creature == -1);
                }
                do {
                    position_arrivee = choix_case();
                    good = 1;
                    List<Position> voisins = Position.getNeighbors(Plateau_de_jeu.requins.get(numero_creature).getPosition());
                    for (int i = 0; i < Plateau_de_jeu.tuiles.size(); i++) {
                        if (Position.isPositionsEquals(Plateau_de_jeu.tuiles.get(i).getPosition(), position_arrivee)) {
                            good = -1;
                        }
                    }
                    if (!Position.isPositionContains(voisins, position_arrivee)) {
                        good = -1;
                    }
                } while (good == -1);

                deplacement++;
                Plateau_de_jeu.requins.get(numero_creature).setPosition(position_arrivee);
                explorateur_mange = Requin.MangerNageur(Plateau_de_jeu, numero_creature);
            } while (deplacement < 2 && explorateur_mange == 0);
        }

        // BALEINE //
        else {
            int deplacement = 0;
            int barque_retournee;
            Position position_depart=null;
            do {
                Position position_arrivee;
                if(deplacement==0) {
                    do {
                        position_depart = choix_case();
                        for (int i = 0; i < Plateau_de_jeu.baleines.size(); i++) {
                            if (Position.isPositionsEquals(Plateau_de_jeu.baleines.get(i).getPosition(), position_depart)) {
                                numero_creature = i;
                            }
                        }
                    } while (numero_creature == -1);
                }
                do {

                    position_arrivee = choix_case();
                    good = 1;
                    List<Position> voisins = Position.getNeighbors(Plateau_de_jeu.baleines.get(numero_creature).getPosition());
                    for (int i = 0; i < Plateau_de_jeu.tuiles.size(); i++) {
                        if (Position.isPositionsEquals(Plateau_de_jeu.tuiles.get(i).getPosition(), position_arrivee)) {
                            good = -1;
                        }
                    }
                    if (!Position.isPositionContains(voisins, position_arrivee)) {
                        good = -1;
                    }
                } while (good == -1);

                deplacement++;
                Plateau_de_jeu.baleines.get(numero_creature).setPosition(position_arrivee);
                barque_retournee = Baleine.RetournerBarque(Plateau_de_jeu, numero_creature);
            } while (deplacement < 3 && barque_retournee == -1);
        }
    }

    public void deplacer_explorateur(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        for (int k = 0; k < 3; k++) {

            int numero_explorateur;
            Position position_depart;
            Position position_arrivee;
            List<Position> voisins;

            do {
                position_depart = choix_case();
                numero_explorateur = Explorateur.get_explorateur(Plateau_de_jeu.tour, position_depart, Plateau_de_jeu);
            } while (numero_explorateur == -1);

            do {
                position_arrivee = choix_case();
                voisins = Position.getNeighbors(Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getPosition());
            } while (!voisins.contains(position_arrivee));

            int type_explorateur = Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getStatut();
            Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).setPosition(position_arrivee);

            if (type_explorateur == 1) {
                break;
            }

            for(int i=0; i<Plateau_de_jeu.tuiles.size();i++){
                if(Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getStatut()!=0 && Position.isPositionsEquals( Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getPosition(), Plateau_de_jeu.tuiles.get(i).getPosition())){
                    Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).setStatut(0);
                }
            }

            if(Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).isInWater(Plateau_de_jeu) && Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getStatut()!=1){
                Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).setStatut(1);
            }

            for(int i=0; i<Plateau_de_jeu.barques.size();i++){
                if(Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getStatut()!=2 &&Position.isPositionsEquals( Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).getPosition(), Plateau_de_jeu.barques.get(i).getPosition())){
                    Plateau_de_jeu.joueurs[Plateau_de_jeu.tour % 4].explorateurs.get(numero_explorateur).setStatut(2);
                    for(int r=0; r<3; r++){
                        if(Plateau_de_jeu.barques.get(i).getValue(r,0)==-1){
                            Plateau_de_jeu.barques.get(i).setValue(r,0,Plateau_de_jeu.tour % 4);
                            Plateau_de_jeu.barques.get(i).setValue(r,1,numero_explorateur);
                        }
                    }
                }
            }
        }

    }

    public static Position choix_case() throws InterruptedException {
        return hexagonalGrid.waitForClick();
    }

}
