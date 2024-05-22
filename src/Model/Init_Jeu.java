package Model;

public  class Init_Jeu {

    int taille=13;

    private final Joueur[] joueurs = new Joueur[4];
    private final Barque[] barques = new Barque[12];
    private final Requin[] requins = new Requin[6];
    private final Baleine[] baleines = new Baleine[5];
    private final SerpentDeMer[] serpentDeMer = new SerpentDeMer[5];
    private final Tuile[] tuiles = new Tuile[40];



    public Init_Jeu() {

        for (int i = 0; i < joueurs.length; i++) {
            joueurs[i] = new Joueur();
        }

        for (int i = 0; i < barques.length; i++) {
            barques[i] = new Barque();
        }

        for (int i = 0; i < requins.length; i++) {
            requins[i] = new Requin();
        }

        for (int i = 0; i < baleines.length; i++) {
            baleines[i] = new Baleine();
        }

        for (int i = 0; i < serpentDeMer.length; i++) {
            serpentDeMer[i] = new SerpentDeMer();
        }

        new Init_Plateau(taille);


    }



}
