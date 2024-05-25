package Model;

import java.util.ArrayList;
import java.util.List;

public class PlateauJeu {

    public final int state_of_game=0;//0 partie en cours ,1 partie finie
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
    }
}