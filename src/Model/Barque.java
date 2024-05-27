package Model;

public class Barque extends Piece {
    private static final int BARQUE_ROWS = 3;
    //3 places pour explorateur
    private static final int BARQUE_COLS = 2;
    //numéro joueur
    //numéro explorateur
    private int[][] barque;

    public Barque(Position position) {
        barque = new int[BARQUE_ROWS][BARQUE_COLS];
        this.position = position;
    }

    public void setValue(int row, int col, int value) {
        barque[row][col] = value;
    }

}
    





