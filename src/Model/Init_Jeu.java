package Model;

import java.util.ArrayList;
import java.util.List;

public class Init_Jeu {

    private static final int TAILLE = 13;

    public final int tour=0;
    public final Joueur[] joueurs = new Joueur[4];
    public final List<Barque> barques = new ArrayList<>();
    public final List<Requin> requins = new ArrayList<>();
    public final List<Baleine> baleines = new ArrayList<>();
    public final List<SerpentDeMer> serpentDeMer = new ArrayList<>();
    public final List<Tuile> tuiles=  new ArrayList<>();
    public final DeDeCreature de= new DeDeCreature();

    public Init_Jeu() {
        for (int i = 0; i < joueurs.length; i++) {
            joueurs[i] = new Joueur();
            joueurs[i].setNumeroJoueur(i);
            System.out.println(joueurs[i]);

            for(int k=0; k< 10; k++){
                joueurs[i].explorateurs.add(new Explorateur());
            }
        }

        int nombreBarque = 12;
        for (int i = 0; i < nombreBarque; i++) {
            barques.add(new Barque());
            barques.get(i).setNumeroBarque(i);
            System.out.println(barques.get(i));
        }

        int nombreRequin = 6;
        for (int i = 0; i < nombreRequin; i++) {
            requins.add( new Requin());
            requins.get(i).setNumeroRequin(i);
            System.out.println(requins.get(i));
        }

        int nombreBaleine = 6;
        for (int i = 0; i < nombreBaleine; i++) {
            baleines.set(i, new Baleine());
            baleines.get(i).setNumeroBaleine(i);
            System.out.println(baleines.get(i));
        }

        int nombreSerpentDeMer = 5;
        for (int i = 0; i < nombreSerpentDeMer; i++) {
            serpentDeMer.set(i, new SerpentDeMer());
            serpentDeMer.get(i).setNumeroSerpentDeMer(i);
            System.out.println(serpentDeMer.get(i));
        }

        for (int i = 0; i < tuiles.length; i++) {
            tuiles[i] = new Tuile(1, 0);
            tuiles[i].setNumeroTuile(i);
            System.out.println(tuiles[i]);
        }

    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /*public Barque[] getBarques() {
        return barques;
    }

    public Requin[] getRequins() {
        return requins;
    }

    public Baleine[] getBaleines() {
        return baleines;
    }

    public SerpentDeMer[] getSerpentDeMer() {
        return serpentDeMer;
    }*/

    public Tuile[] getTuiles() {
        return tuiles;
    }


}