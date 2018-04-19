import java.util.ArrayList;

public class Pianeta extends CorpoCeleste{

    ArrayList<Luna> lune = new ArrayList<Luna>();

    Pianeta(double massa, Punto posizione) {
        super(massa, posizione);
    }

    Pianeta(String nome, double massa, Punto posizione) {
        super(nome, massa, posizione);
    }
}
