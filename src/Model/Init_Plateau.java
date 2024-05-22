package Model;

public class Init_Plateau {

    private final String[][] plateau_initial = {
            {"vide", "vide", "vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide", "vide", "vide"},
            {"vide", "vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer"},
            {"vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer"},
            {"vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide"},
            {"vide", "vide", "vide", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "vide", "vide", "vide"}
    };

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

                grille[x][y] = new Case(new Position(x, y), plateau_initial[x][y], null, null);

            }
        }
    }

    public Case getCase(Position position) {
        return grille[position.getX()][position.getY()];
    }

    public static class Case {
        private Position position;
        private String content;
        private Tuile tuile;
        private Piece piece;

        public Case(Position position, String content, Tuile tuile, Piece piece) {
            this.position = position;
            this.content = content;
            this.tuile = tuile;
            this.piece = piece;
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

        public Tuile getTuile() {
            return tuile;
        }

        public void setTuile(Tuile tuile) {
            this.tuile = tuile;
        }

        public Piece getPiece() {
            return piece;
        }

        public void setPiece(Piece piece) {
            this.piece = piece;
        }

        public boolean existTuile() {

            if (tuile == null) {
                return false;
            }

            return true;
        }

        public boolean existPiece() {

            if (piece == null) {
                return false;
            }
            return true;
        }
    }
}
