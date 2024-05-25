package Controller;
import Model.*;
import java.util.List;


public class Partie {
    PlateauJeu Plateau_de_jeu= new PlateauJeu();
    while(Plateau_de_jeu.state_of_game==0)
    {
        Tour.tour(Plateau_de_jeu);
    }
}
