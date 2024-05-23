package Model;

public class Tuile {

    private int faceCachee;
    private int faceVisible;

    public Tuile(int faceCachee, int faceVisible) {
        this.faceCachee=faceCachee;
        this.faceVisible=faceVisible;

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

    public TuileMontagne(int faceCachee, int faceVisible) {
        super(faceCachee, faceVisible);

    }

}
