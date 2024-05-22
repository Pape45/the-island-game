package Model;


public class Monstres extends Piece {
    private Position positions;

    public Position getPositions() {
        return positions;
    }

    public void setPositions(Position positions) {
        this.positions = positions;
    }
}



class Baleine extends Monstres {
    public void renverserBarque() {

    }
}

class Requin extends Monstres {
    public void mangerNageur() {

    }
}


class SerpentDeMer extends Monstres {
    public void renverserBarque() {

    }

    public void mangerNageur() {

    }
}
