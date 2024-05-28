package Model;

import Controller.Tour;

import java.util.ArrayList;
import java.util.List;

public class Explorateur extends Piece {
    private int statut; // 0: sur terre, 1: nageur, 2: sur barque, 3: arrivé, 4: mort
    private int deplacement; // Nombre de déplacements
    private int tresor; // Points pour gagner
    
    public Explorateur(int statut, int deplacement, int tresor) {
        setStatut(statut);
        setdepacement(deplacement);
        setTresor(tresor);
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }


    public void setdepacement(int deplacement) {
        this.deplacement = deplacement;
    }

    public int getDeplacement() {
        return deplacement;
    }
    
    @Override
    public void setPosition(Position position) {
        this.position = position;
        this.deplacement=this.deplacement+1;
    }

    public int getTresor() {
        return tresor;
    }

    public void setTresor(int tresor) {
        this.tresor = tresor;
    }

    public static int get_explorateur(int tour, Position position_depart, PlateauJeu Plateau_de_jeu) {
        for (int i = 0; i < Plateau_de_jeu.joueurs[tour % 4].explorateurs.size(); i++) {
            if (Position.isPositionsEquals(Plateau_de_jeu.joueurs[tour % 4].explorateurs.get(i).getPosition(),position_depart)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isInWater(PlateauJeu Plateau_de_jeu){
        Position position_exploreur= this.getPosition();
        for(int i=0; i<Plateau_de_jeu.barques.size();i++){
            if(Position.isPositionsEquals(position_exploreur,Plateau_de_jeu.barques.get(i).getPosition())){
                return false;
            }
        }
        for(int i=0; i<Plateau_de_jeu.tuiles.size();i++){
            if(Position.isPositionsEquals(position_exploreur,Plateau_de_jeu.tuiles.get(i).getPosition())){
                return false;
            }
        }
        return true;
    }

    public static void init_pos_explorateurs(PlateauJeu Plateau_de_jeu) throws InterruptedException {
        System.out.println("Init explorateurs");
        List<Position> listes_tuiles_libres = Tuile.init_position_tuiles();
        Position position;
        int indice_tuile;
        for(int i=0; i<10; i++){
            for(int k=0; k<4;k++){
                do{
                    indice_tuile=-1;
                    position= Tour.choix_case();
                    for(int p=0; p<listes_tuiles_libres.size(); p++){
                        if(listes_tuiles_libres.get(p).getX() == position.getX() && listes_tuiles_libres.get(p).getY() == position.getY()){
                            indice_tuile=p;
                            System.out.println(k+" "+i);
                        }
                    }
                    System.out.println(indice_tuile);
                }while(indice_tuile==-1);
                listes_tuiles_libres.remove(indice_tuile);
                Plateau_de_jeu.joueurs[k].explorateurs.get(i).setPosition(position);
                System.out.println(listes_tuiles_libres.size());
            }
        }
        System.out.println("Fin Init explorateurs");
    }

    public static int nbExplorateurSurLaCase(PlateauJeu Plateau_de_jeu, Position position){
        int nb_explorateur=0;
        for(int i=0; i<Plateau_de_jeu.joueurs.length;i++){
            for(int k=0; k<Plateau_de_jeu.joueurs[i].explorateurs.size();k++){
                if(Position.isPositionsEquals(position,Plateau_de_jeu.joueurs[i].explorateurs.get(i).getPosition())){
                    nb_explorateur++;
                }
            }
        }
        return nb_explorateur;
    }

}
