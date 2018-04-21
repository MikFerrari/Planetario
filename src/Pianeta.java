import java.util.ArrayList;

public class Pianeta extends CorpoCeleste{

	private final static int MAX_LUNE = 5000;
	private int numeroLune = 0;
    ArrayList<Luna> lune = new ArrayList<Luna>();

    public Pianeta(double massa, Punto posizione) {
        super(massa, posizione);
    }

    public Pianeta(String nome, double massa, Punto posizione) {
        super(nome, massa, posizione);
    }
    
    public boolean addLuna(Luna nuova) {
    	if(numeroLune < MAX_LUNE) {
    		lune.add(nuova);
        	numeroLune++;
        	return true;
    	}
    	else {
    		return false;
    	}
    }

    public void removeLuna(Luna daRimuovere) {
    	lune.remove(daRimuovere);
    	numeroLune--;
    }
    
    //Togliere??
    public Stella getPercorso(SistemaStellare sistema) {
    	return sistema.getStella();
    }
    
}
