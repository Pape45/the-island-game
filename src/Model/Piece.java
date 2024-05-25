package Model;

import java.util.List;

public class Piece {
    public Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public String getStatus() {
        return "statut_de_piece";
    }

    public static List<String> getCaseWherePieceCanGo(String pieceType) {
        return List.of("type_de_case");
    }

}


