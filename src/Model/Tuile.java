package Model;

public class Tuile {

    public int faceCachee;
    public int faceVisible;
    public int numeroTuile;
    public Position position;
    public String nom;

    public Tuile(int faceCachee, int faceVisible) {
        this.faceCachee=faceCachee;
        this.faceVisible=faceVisible;

    }

    public Position getPosition() {

        return position;
    }

    public int getFaceCachee() {
        return faceCachee;
    }

    public void setFaceCachee(int faceCachee) {
        this.faceCachee = faceCachee;
    }

    public int getFaceVisible() {

        return faceVisible;
    }

    public void setFaceVisible(int faceVisible) {
        this.faceVisible = faceVisible;
    }

    public void setNumeroTuile(int numeroTuile){
        this.numeroTuile=numeroTuile;
    }

    public String toString() {
        return "Tuile= "+ numeroTuile;
    }
}


class TuilePlage extends Tuile {

    public TuilePlage(int faceCachee, int faceVisible) {
        super(faceCachee, faceVisible);

    }

}

class TuileForet extends Tuile {

    public TuileForet(int faceCachee, int faceVisible) {
        super(faceCachee, faceVisible);

    }

}

class TuileMontagne extends Tuile {
    private String nom;
    public TuileMontagne(int faceCachee, int faceVisible) {
        super(faceCachee, faceVisible);

    }

    public void setTypeTuile(){
        this.nom="Montagne";
    }

    public String getNom(){
        return nom;
    }

}
