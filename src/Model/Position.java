package Model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Position> getNeighbors(Position position) {
        List<Position> neighbors = new ArrayList<Position>();
        int pos_x = position.getX();
        int pos_y = position.getY();

        int nbLigne=13;
        int[] indiceMaxLigne= {6,9,10,9,10,11,10,11,10,9,10,9,6};

        if(abs(pos_x-2) < indiceMaxLigne[pos_y])
            neighbors.add(new Position(pos_x-2, pos_y));  //voisin de gauche

        if(abs(pos_x+2) < indiceMaxLigne[pos_y])
            neighbors.add(new Position(pos_x+2, pos_y));   //voisin de droite

        if(abs(pos_x-1) < indiceMaxLigne[pos_y-1] && pos_y-1>=0)
            neighbors.add(new Position(pos_x-1, pos_y-1));  //voisin en haut à gauche

        if(abs(pos_x+1) < indiceMaxLigne[pos_y-1] && pos_y-1>=0)
            neighbors.add(new Position(pos_x+1, pos_y-1));   //voisin en haut à droite

        if(abs(pos_x-1) < indiceMaxLigne[pos_y+1] && pos_y+1>= nbLigne)   //voisin en bas à gauche
            neighbors.add(new Position(pos_x-1, pos_y+1));

        if(abs(pos_x+1) < indiceMaxLigne[pos_y+1] && pos_y+1>= nbLigne)   //voisin en bas à droite
            neighbors.add(new Position(pos_x+1, pos_y+1));

        return neighbors;
    }
}
