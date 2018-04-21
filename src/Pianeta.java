import java.util.ArrayList;

public class Pianeta extends CorpoCeleste{

	private final static int MAX_LUNE = 5000;
	
    ArrayList<Luna> lune = new ArrayList<Luna>();

    public Pianeta(double massa, Punto posizione) {
        super(massa, posizione);
    }

    public Pianeta(String nome, double massa, Punto posizione) {
        super(nome, massa, posizione);
    }
    
    public boolean addLuna(Luna nuova) {
    	if(lune.size() < MAX_LUNE) {
    		lune.add(nuova);
        	return true;
    	}
    	else return false;
    }
    
    public Luna trovaLuna(String luna) {
	   	
    	for(int i = 0; i < lune.size(); i++) {
    		if(lune.get(i).nome.equals(luna))
    			return lune.get(i);
    	}
    	return null;
    }

    public void removeLuna(String daRimuovere) {
    	lune.remove(trovaLuna(daRimuovere));
    }
    
    public Stella getPercorso(SistemaStellare sistema) {
    	return sistema.getStella();
    }
    
}