package Model;

public class Init_Plateau {

    private final Case[][] grille;
    private final int taille;

    public Init_Plateau(int taille) {
        this.taille = taille;
        this.grille = new Case[taille][taille];

        initialiserGrille();
    }

    private void initialiserGrille() {
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                grille[x][y] = new Case(new Position(x, y), "vide");
            }
        }
    }

    public void setCaseContent(Position position, String content) {
        int x = position.getX();
        int y = position.getY();

        grille[x][y].setContent(content);

    }

    public String getCaseContent(Position position) {
        int x = position.getX();
        int y = position.getY();

        return grille[x][y].getContent();

    }



    private static class Case {
        private Position position;
        private String content;

        public Case(Position position, String content) {
            this.position = position;
            this.content = content;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }

}
