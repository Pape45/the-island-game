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

        List<Position> positions_tuiles = Tuile.init_position_tuiles();

        for (int i = 0; i < 16; i++) {
            int randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(0, 0));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            positions_tuiles.remove(randomInt);
        }

        for (int i = 0; i < 16; i++) {
            int randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(0, 1));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            positions_tuiles.remove(randomInt);
        }

        for (int i = 0; i < 8; i++) {
            int randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(0, 2));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            positions_tuiles.remove(randomInt);
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