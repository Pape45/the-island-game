package Model;

public class DeDeCreature {
    private final String[] faces;

    public DeDeCreature() {
        faces = new String[6];

        faces[0] = "Requin";
        faces[1] = "Requin";
        faces[2] = "Baleine";
        faces[3] = "Baleine";
        faces[4] = "SerpentDeMer";
        faces[5] = "SerpentDeMer";
    }

    public String lancerDe() {
        int index = (int) (Math.random() * 6);
        return faces[index];
    }

    public void deplacerUneCreature() {
        String creature = lancerDe();

    }
}
