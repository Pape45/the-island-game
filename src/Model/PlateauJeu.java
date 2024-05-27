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


        serpentDeMer.add(new SerpentDeMer(new Position(-9, 1)));
        serpentDeMer.add(new SerpentDeMer(new Position(10, 2)));
        serpentDeMer.add(new SerpentDeMer(new Position(-10,10)));
        serpentDeMer.add(new SerpentDeMer(new Position(9, 11)));
        serpentDeMer.add(new SerpentDeMer(new Position(0, 6)));
        
        requins.add(new Requin(new Position(-7, 1)));
        requins.add(new Requin(new Position(8, 2)));  
        requins.add(new Requin(new Position(-8, 10)));
        requins.add(new Requin(new Position(7, 11))); 
        requins.add(new Requin(new Position(2, 6)));

        baleines.add(new Baleine(new Position(-5, 1)));
        baleines.add(new Baleine(new Position(6, 2)));  
        baleines.add(new Baleine(new Position(-6, 10)));
        baleines.add(new Baleine(new Position(-7, 11))); 
        baleines.add(new Baleine(new Position(0, 0)));

        barques.add(new Barque(new Position(3, 11)));
        barques.add(new Barque(new Position(-3, 11)));
        barques.add(new Barque(new Position(-5, 11)));
        barques.add(new Barque(new Position(5, 11)));



        joueurs[0].explorateurs.get(0).setPosition(new Position(-3, 3));
        joueurs[0].explorateurs.get(1).setPosition(new Position(-1,3));
        joueurs[0].explorateurs.get(2).setPosition(new Position(1,3));
        joueurs[0].explorateurs.get(3).setPosition(new Position(3,3));
        joueurs[0].explorateurs.get(4).setPosition(new Position(-4,4));
        joueurs[0].explorateurs.get(5).setPosition(new Position(0,4));
        joueurs[0].explorateurs.get(6).setPosition(new Position(-2,4));
        joueurs[0].explorateurs.get(7).setPosition(new Position(-4,4));
        joueurs[0].explorateurs.get(8).setPosition(new Position(-7,5));
        joueurs[0].explorateurs.get(9).setPosition(new Position(-5,5));
        
        joueurs[1].explorateurs.get(0).setPosition(new Position(-3,5));
        joueurs[1].explorateurs.get(1).setPosition(new Position(-1,5));
        joueurs[1].explorateurs.get(2).setPosition(new Position(3,5));
        joueurs[1].explorateurs.get(3).setPosition(new Position(1,5));
        joueurs[1].explorateurs.get(4).setPosition(new Position(5,5));
        joueurs[1].explorateurs.get(5).setPosition(new Position(7,5));
        joueurs[1].explorateurs.get(6).setPosition(new Position(-6,6));
        joueurs[1].explorateurs.get(7).setPosition(new Position(-4,6));
        joueurs[1].explorateurs.get(8).setPosition(new Position(-2,6));
        joueurs[1].explorateurs.get(9).setPosition(new Position(2,6));
        
        joueurs[2].explorateurs.get(0).setPosition(new Position(4,6));
        joueurs[2].explorateurs.get(1).setPosition(new Position(6,6));
        joueurs[2].explorateurs.get(2).setPosition(new Position(-7,7));
        joueurs[2].explorateurs.get(3).setPosition(new Position(-5,7));
        joueurs[2].explorateurs.get(4).setPosition(new Position(-3,7));
        joueurs[2].explorateurs.get(5).setPosition(new Position(-1,7));
        joueurs[2].explorateurs.get(6).setPosition(new Position(1,7));
        joueurs[2].explorateurs.get(7).setPosition(new Position(3,7));
        joueurs[2].explorateurs.get(8).setPosition(new Position(5,7));
        joueurs[2].explorateurs.get(9).setPosition(new Position(7,7));
        
        joueurs[3].explorateurs.get(0).setPosition(new Position(-4,8));
        joueurs[3].explorateurs.get(1).setPosition(new Position(-2,8));
        joueurs[3].explorateurs.get(2).setPosition(new Position(0,8));
        joueurs[3].explorateurs.get(3).setPosition(new Position(2,8));
        joueurs[3].explorateurs.get(4).setPosition(new Position(7,8));
        joueurs[3].explorateurs.get(5).setPosition(new Position(-3,9));
        joueurs[3].explorateurs.get(6).setPosition(new Position(-1,9));
        joueurs[3].explorateurs.get(7).setPosition(new Position(1,9));
        joueurs[3].explorateurs.get(8).setPosition(new Position(3,9));
        joueurs[3].explorateurs.get(9).setPosition(new Position(5,9));

    }

    public void setState_of_game(int state_of_game){
        this.state_of_game = state_of_game;
    }

    public int getState_of_game(){
        return state_of_game;
    }
}