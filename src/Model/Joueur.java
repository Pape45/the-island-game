package Model;


import java.util.ArrayList;
import java.util.List;



public class Joueur {
    private  Explorateur[] explorateur = new Explorateur[10];


    private final List<Tuile> tuilesEnMain = new ArrayList<>();



    public void ajouterTuile(Tuile tuile) {
        tuilesEnMain.add(tuile);
    }

    public List<Tuile> getTuilesEnMain() {
        return tuilesEnMain;
    }


}
