package Model;

public class Tuile {

    private String faceCachee;
    private String faceVisible;

    public Tuile(String faceCachee, String faceVisible) {
        this.faceCachee = faceCachee;
        this.faceVisible = faceVisible;
    }

    public String getFaceCachee() {
        return faceCachee;
    }

    public void setFaceCachee(String faceCachee) {
        this.faceCachee = faceCachee;
    }

    public String getFaceVisible() {
        return faceVisible;
    }

    public void setFaceVisible(String faceVisible) {
        this.faceVisible = faceVisible;
    }

}


class TuilePlage extends Tuile {

    public TuilePlage(String faceCachee, String faceVisible) {
        super(faceCachee, faceVisible);

    }

}

class TuileForet extends Tuile {

    public TuileForet(String faceCachee, String faceVisible) {
        super(faceCachee, faceVisible);

    }

}

class TuileMontagne extends Tuile {

    public TuileMontagne(String faceCachee, String faceVisible) {
        super(faceCachee, faceVisible);

    }

}
