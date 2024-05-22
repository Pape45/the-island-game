package Controller;

import Model.Piece;
import Model.Position;

import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

public class Deplacements {

    public void deplacement(Piece piece, Case case_arrivee) {  //on appelle cette fonction une fois que l'utilisateur à choisit la pièce à déplacer et la case où il veut la déplacer
        if(!deplacement_is_possible(piece,case_arrivee))      //une fonction qui permet de définir si le déplacement d'une certaine pièce à un certain endroit est possible ou non (exemple: si on sélectionne une case d'arrivée trop loin -> false // si on veut déplacer une baleine sur la terre -> false)
            System.out.println("Déplacement impossible");

        else {
            Piece.setPosition(piece,case_arrivee);            //on modifie la position de la pièce qui a été déplacée
            Piece.setAlreadyMoveThisRound(piece,true);        //on met le booléen qui gère si la pièce a déjà été déplacée ce tour à true
        }
    }

    public boolean deplacement_is_possible(Piece piece, Case case_arrivee){
        if(Piece.getAlreadyMovedThisRound(piece)){
            System.out.println("Cette pièce a déjà été déplacée");
            return false;
        }
        else{
            String piece_type= Piece.getType(piece);   //on récupère le type de la pièce (pion ou monstre)
            if(Objects.equals(piece_type, "monstre") && (Case.isTuile(case_arrivee) || Case.isVoid()){   //si la case d'arrivée pour un monstre est une tuile ou une case "vide"
                System.out.println("Cette pièce ne peut pas se déplacer sur une case de ce type");
                return false;
            }
            else{
                List<Position> piece_voisins = Piece.getVoisins(Piece.getPosition(piece));  //on récupère la liste des positions des cases voisines
                if(!piece_voisins.contains(case_arrivee.getPosition()){  //si la case d'arrivée n'est pas une case voisine
                    System.out.println("La pièce est trop éloignée de la case d'arrivée");
                    return false;
                }
            }
        }
        return true;
    }
}
