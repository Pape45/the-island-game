package Controller;
import Model.*;
import java.util.List;


public class Partie {

    public static void partie() {

        PlateauJeu Plateau_de_jeu = new PlateauJeu();
        Plateau_de_jeu.setState_of_game(0);
        while(Plateau_de_jeu.getState_of_game()==0)

        {
            Tour.tour(Plateau_de_jeu);
        }
    }
}
