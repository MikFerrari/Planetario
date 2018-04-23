import java.util.ArrayList;

public class ElencoPianeti {
	
    private static final int MAX_PIANETI = 26000;
    private static final int ELEMENTI_PERCORSO = 3;
    
    private Stella stella;
    ArrayList<Pianeta> pianeti = new ArrayList<Pianeta>();
    
    //default Constructor

    public boolean addPianeta(Pianeta nuovo) {
      
    	if(pianeti.size() < MAX_PIANETI) {
    		pianeti.add(nuovo);
        	return true;
    	}
    	
    	else return false;
    }
   
    public Pianeta trovaPianeta(String pianeta) {
    	   	
    	for(int i = 0; i < pianeti.size(); i++) {
    		if(pianeti.get(i).getNome().equals(pianeta))
    			return pianeti.get(i);
    	}
    	return null;
    }

    public Pianeta removePianeta(String daRimuovere) {
    	Pianeta rimosso = trovaPianeta(daRimuovere);
    	pianeti.remove(rimosso);
    	return rimosso;
    }
    
//Ho pensato di mettere un array di stringhe e non di corpi celesti, perché alla fine il percorso è solo da stampare    
//e di includere la stella anche se è ovvio, perché sul pdf lo specifica.
    
    public String[] getPercorso(String diQualeLuna) {
    	
    	String[] percorso = new String[ELEMENTI_PERCORSO];
    	
    	for(int i = 0; i  < pianeti.size(); i++) {
    		if(pianeti.get(i).lune.contains(pianeti.get(i).trovaLuna(diQualeLuna))) {
    			percorso[0] = stella.getNome();
    			percorso[1] = pianeti.get(i).getNome();
    			percorso[2] = pianeti.get(i).trovaLuna(diQualeLuna).getNome();
    			return percorso;
    		}    			
    	}
    	return null;  		
    }
}