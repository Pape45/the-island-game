package Model;

import java.util.ArrayList;
import java.util.List;

public class Tuile {
    public int faceCachee;//0
    public int faceVisible; //1
    public int typeTuile; // 0 pour forêt, 1 pour montagne, 2 pour plage
    public Position position;
    public final List<Tuile> tuiles = new ArrayList<>();

    public Tuile(int faceCachee, int faceVisible, int typeTuile) {
        this.faceCachee = faceCachee;
        this.faceVisible = faceVisible;
        this.typeTuile = typeTuile;
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

    public void setTypeTuile(int typeTuile) {
        this.typeTuile = typeTuile;
    }

    public int getTypeTuile() {
        return typeTuile;
    }

    public void initialiserTuile() {
        for (int i = 0; i < 16; i++) {
            tuiles.add(new TuilePlage(0, 1,2));
        }

        for (int i = 0; i < 16; i++) {
            tuiles.add(new TuileForet(0, 1,0));
        }

        for (int i = 0; i < 8; i++) {
            tuiles.add(new TuileMontagne(0, 1,1));
        }
    }
}

class TuilePlage extends Tuile {
    public TuilePlage(int faceCachee, int faceVisible,int typeTuile) {
        super(faceCachee, faceVisible, 2);
    }
}

class TuileForet extends Tuile {
    public TuileForet(int faceCachee, int faceVisible, int typeTuile) {
        super(faceCachee, faceVisible, 0);

    }
}

class TuileMontagne extends Tuile {

    public TuileMontagne(int faceCachee, int faceVisible,int typeTuile) {
        super(faceCachee, faceVisible, 1);

    }

}
