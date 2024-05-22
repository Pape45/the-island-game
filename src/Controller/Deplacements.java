package Controller;

import Model.Piece;
import Model.Position;

import java.util.List;

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

    public boolean deplacement_is_possible(Piece piece, Position case_arrivee){
        if(Piece.getAlreadyMovedThisRound(piece)){
            System.out.println("Cette pièce a déjà été déplacée");
            return false;
        }
        else{
            String piece_type= Piece.getType(piece);   //on récupère le type de la pièce (nageur, explorateur, requin, baleine,...)
            String case_type= Case.getType(case_arrivee); //on récupère le type de la case (mer, plage, forêt, montagne)
            List<String> case_where_piece_can_move= Piece.getCaseWherePieceCanGo(piece_type);
            if(!case_where_piece_can_move.contains(case_type)){   //si la case d'arrivée n'est pas dans la liste des cases où peut se déplacer la pièce
                System.out.println("Cette pièce ne peut pas se déplacer sur une case de ce type");
                return false;
            }
            else{
                Position position_piece= piece.getPosition();
                Position position_case_arrivee= case_arrivee.getPosition();
                if(abs(position_piece.getX()-position_case_arrivee.getX())>1 || abs(position_piece.getY()-position_case_arrivee.getY())>1){    //on test si la pièce est au maximum à une case de la case d'arrivée -> à revoir parce que chaque case a 6 cases voisines et non 8 comme dans un tableau à case carrée, sinon on peut faire une méthode qui renvoie la liste des cases voisines à la case qu'on lui envoie en entrée
                    System.out.println("La pièce est trop éloignée de la case d'arrivée");
                    return false;
                }
            }
        }
        return true;
    }
}
