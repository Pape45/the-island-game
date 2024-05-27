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

        int randomInt;

        //TUILE PLAGE
        for (int i = 0; i < 3; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(1, 0));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        for (int i = 3; i < 6; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(2, 0));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(3, 0));
        tuiles.get(6).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        for (int i = 7; i < 10; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(6, 0));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        for (int i = 10; i < 12; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(7, 0));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(8, 0));
        tuiles.get(12).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(9, 0));
        tuiles.get(13).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(10, 0));
        tuiles.get(14).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(11, 0));
        tuiles.get(15).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        //TUILE FORET
        for (int i = 16; i < 18; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(2, 1));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        for (int i = 18; i < 20; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(1, 1));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        for (int i = 20; i < 23; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(3, 1));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        for (int i = 23; i < 25; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(4, 1));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(6, 1));
        tuiles.get(25).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(8, 1));
        tuiles.get(26).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(9, 1));
        tuiles.get(27).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(10, 1));
        tuiles.get(28).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(11, 1));
        tuiles.get(29).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        for (int i = 30; i < 32; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(12, 1));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        //TUILE MONTAGNE
        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(1, 2));
        tuiles.get(32).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        for (int i = 33; i < 37; i++) {
            randomInt = (int) (Math.random() * positions_tuiles.size());
            tuiles.add(new Tuile(4, 2));
            tuiles.get(i).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
            //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
            positions_tuiles.remove(randomInt);
        }

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(11, 2));
        tuiles.get(37).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(12, 2));
        tuiles.get(38).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        randomInt= (int) (Math.random() * positions_tuiles.size());
        tuiles.add(new Tuile(5, 2));
        tuiles.get(39).setPosition(new Position(positions_tuiles.get(randomInt).getX(),positions_tuiles.get(randomInt).getY()));
        //System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY());
        positions_tuiles.remove(randomInt);

        for(int i = 0; i < 40; i++) {
            System.out.println(tuiles.get(i).getPosition().getX() +" "+  tuiles.get(i).getPosition().getY() +" "+ tuiles.get(i).getFaceCachee());
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