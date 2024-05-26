package Controller;
import Model.*;



public class Partie {

    public static void partie() {

        PlateauJeu Plateau_de_jeu = new PlateauJeu();
        Plateau_de_jeu.setState_of_game(0);
        Position buffer=new Position(0, 0);
        Plateau_de_jeu.joueurs[0].explorateurs.get(0).setPosition(buffer);
        buffer.setX(2);
        buffer.setY(0);
        Plateau_de_jeu.joueurs[0].explorateurs.get(1).setPosition(buffer);
        buffer.setX(1);
        buffer.setY(1);
        Plateau_de_jeu.joueurs[1].explorateurs.get(0).setPosition(buffer);
        buffer.setX(3);
        buffer.setY(1);
        Plateau_de_jeu.joueurs[1].explorateurs.get(1).setPosition(buffer);
        buffer.setX(2);
        buffer.setY(2);
        Plateau_de_jeu.joueurs[2].explorateurs.get(0).setPosition(buffer);
        buffer.setX(0);
        buffer.setY(2);
        Plateau_de_jeu.joueurs[2].explorateurs.get(1).setPosition(buffer);
        buffer.setX(1);
        buffer.setY(1);
        Plateau_de_jeu.joueurs[3].explorateurs.get(0).setPosition(buffer);
        buffer.setX(3);
        buffer.setY(1);
        Plateau_de_jeu.joueurs[3].explorateurs.get(1).setPosition(buffer);


        while(Plateau_de_jeu.getState_of_game()==0)

        {
            Tour.tour(Plateau_de_jeu);
        }
    }
}
