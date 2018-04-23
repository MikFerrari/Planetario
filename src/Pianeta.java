import java.util.ArrayList;

public class Pianeta extends CorpoCeleste{

	private final static int MAX_LUNE = 5000;
	
    ArrayList<Luna> lune = new ArrayList<Luna>();

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
	   	
    	for (int i = 0; i < lune.size(); i++) {
    		if(lune.get(i).getNome().equals(luna))
    			return lune.get(i);
    	}
    	return null;
    }

    public Luna removeLuna(String daRimuovere) {
    	Luna rimossa = trovaLuna(daRimuovere);
    	lune.remove(rimossa);
    	return rimossa;
    }

	public ArrayList<Luna> getLune() {
		return lune;
	}

	public Stella getPercorso(SistemaStellare sistema) {
    	return sistema.getStella();
    }
    
}