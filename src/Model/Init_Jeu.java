package Model;

public class Init_Jeu {

    private static final int TAILLE = 13;

    public final Joueur[] joueurs = new Joueur[4];
    public final Barque[] barques = new Barque[12];
    public final Requin[] requins = new Requin[6];
    public final Baleine[] baleines = new Baleine[5];
    public final SerpentDeMer[] serpentDeMer = new SerpentDeMer[5];
    public final Tuile[] tuiles = new Tuile[40];
    public final DeDeCreature de= new DeDeCreature();

    public Init_Jeu() {


        for (int i = 0; i < joueurs.length; i++) {
            joueurs[i] = new Joueur();
            joueurs[i].setNumeroJoueur(i);
            System.out.println(joueurs[i]);
        }

        for (int i = 0; i < barques.length; i++) {
            barques[i] = new Barque();
            barques[i].setNumeroBarque(i);
            System.out.println(barques[i]);
        }

        for (int i = 0; i < requins.length; i++) {
            requins[i] = new Requin();
            requins[i].setNumeroRequin(i);
            System.out.println(requins[i]);
        }

        for (int i = 0; i < baleines.length; i++) {
            baleines[i] = new Baleine();
            baleines[i].setNumeroBaleine(i);
            System.out.println(baleines[i]);
        }

        for (int i = 0; i < serpentDeMer.length; i++) {
            serpentDeMer[i] = new SerpentDeMer();
            serpentDeMer[i].setNumeroSerpentDeMer(i);
            System.out.println(serpentDeMer[i]);
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

    public Barque[] getBarques() {
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
    }

    public Tuile[] getTuiles() {
        return tuiles;
    }


}