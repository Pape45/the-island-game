package Model;

import java.util.ArrayList;
import java.util.List;

public class PlateauJeu {

    public int state_of_game;    //0 partie en cours ,1 partie finie
    public int tour=0;
    public Joueur[] joueurs = new Joueur[4];
    public List<Barque> barques = new ArrayList<>();
    public List<Requin> requins = new ArrayList<>();
    public List<Baleine> baleines = new ArrayList<>();
    public List<SerpentDeMer> serpentDeMer = new ArrayList<>();
    public List<Tuile> tuiles=  new ArrayList<>();
    public DeDeCreature de= new DeDeCreature();

    public PlateauJeu() {
        for (int i = 0; i < joueurs.length; i++) 
        {
            joueurs[i] = new Joueur();
        }

        for (int i = 0; i < 16; i++) {
            tuiles.add(new Tuile.TuilePlage(0, 0));
        }

        for (int i = 0; i < 16; i++) {
            tuiles.add(new Tuile.TuileForet(0, 1));
        }

        for (int i = 0; i < 8; i++) {
            tuiles.add(new Tuile.TuileMontagne(0, 2));
        }

        serpentDeMer.add(new SerpentDeMer(new Position(-9, 1)));
        serpentDeMer.add(new SerpentDeMer(new Position(10, 2)));
        serpentDeMer.add(new SerpentDeMer(new Position(-10,10)));
        serpentDeMer.add(new SerpentDeMer(new Position(9, 11)));
        serpentDeMer.add(new SerpentDeMer(new Position(0, 6)));
    }

    public void setState_of_game(int state_of_game){
        this.state_of_game = state_of_game;
    }

    public int getState_of_game(){
        return state_of_game;
    }
}