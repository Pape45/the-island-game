package Model;


public class Piece {
    public Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {

        this.position.setX(position.getX()) ;
        this.position.setY(position.getY()) ;
    }


}


