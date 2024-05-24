package Model;

public class DeDeCreature {
    private final int[] faces;

    public DeDeCreature() {
        faces = new int[6];
        faces[0] = 1;
        faces[1] = 2;
        faces[2] = 3;

    }

    public int lancer() {
        int valeur = (int) (Math.random() * 3);
        return faces[valeur];
    }


}
