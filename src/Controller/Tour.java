package Controller;
import Model.*;
public class Tour {

}

public class DeplacerCreature{

    public void deplacerCreature() {
        int de = de.lancer();//de.lancer doit retourner un chiffre 0=serpent de mer   1=requin   2=baleine
        int numero_creature=-1;
        int tuile;
        if (de==0){
            do{
                Position position_depart = choix_case();
                for(int i=0;i<6 ;i++) {
                    if (plateau_de_jeu.serpent_de_mer[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while(numero_creature==-1);

            do{
                Position position_arrivee=choix_case();
                tuile=1;
                for (int i = 0; i < 40; i++) {
                    if (plateau_de_jeu.tuile[i].getPosition().equals(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            plateau_de_jeu.serpent_de_mer[numero_creature].setPosition(position_arriver);
        }

        else if (de==1){
            do {
                Position position_depart=choix_case();
                for(int i=0;i<7 ;i++) {
                    if (plateau_de_jeu.requin[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while (numero_creature==-1);

            do{
                Position position_arrivee=choix_case();
                tuile=1;
                for (int i = 0; i < 40; i++) {
                    if (plateau_de_jeu.tuile[i].getPosition().equals(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            plateau_de_jeu.requin[numero_creature].setPosition(position_arriver);

        }else {
            do{
                Position position_depart=choix_case();
                for(int i=0;i<7 ;i++) {
                    if (plateau_de_jeu.baleine[i].getPosition().equals(position_depart)) {
                        numero_creature=i;
                    }
                }
            }while (numero_creature==-1);

            do{
                Position position_arrivee=choix_case();
                tuile=1;
                for (int i = 0; i < 40; i++) {
                    if (plateau_de_jeu.tuile[i].getPosition().equals(position_arrivee)){
                        tuile=-1;
                    }
                }
            }while(tuile==-1);

            plateau_de_jeu.baleine[numero_creature].setPosition(position_arriver);
        }
    }

    public void deplacer_piece() {
        for (int i = 0; i < 3; i++) {
            position_depart = choix_case_depart();
            position_arriver = choix_case_arriver();
            numero_explorateur = get_explorateur(position_depart);
            plateau_de_jeu.joueur[tour % 4].explorateur[numero_explorateur].deplacer(position_arriver);
        }
    }

    public int get_explorateur (Position position_depart) {
        for (int i = 0; i < 3; i++) {
            if (plateau_de_jeu.joueur[tour % 4].explorateur[i].Positions == position_depart) {
                return i;
            }
        }
        return -1;
    }
}