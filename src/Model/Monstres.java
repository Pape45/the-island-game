package Model;


public class Monstres extends Piece {

}



class Baleine extends Monstres {
    public int numeroBaleine;
    public void renverserBarque() {

    }

    public void setNumeroBaleine(int numeroBaleine){
        this.numeroBaleine=numeroBaleine;
    }

    public String toString() {

        return "Baleine: "+ numeroBaleine;
    }


}

class Requin extends Monstres {
    public void mangerNageur() {

    }
    public int numeroRequin;


   public void setNumeroRequin(int numeroRequin){
       this.numeroRequin=numeroRequin;
   }

    public String toString() {

       return "Requin: "+ numeroRequin;
    }

}


class SerpentDeMer extends Monstres {
    private int numeroSerpentDeMer;
    public void renverserBarque() {

    }

    public void mangerNageur() {

    }

    public void setNumeroSerpentDeMer(int numeroSerpentDeMer){
        this.numeroSerpentDeMer=numeroSerpentDeMer;
    }

    public String toString() {

        return "Serpent de mer: "+ numeroSerpentDeMer;
    }

}
