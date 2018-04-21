import java.util.ArrayList;

public class ElencoPianeti {
	
    private static final int MAX_PIANETI = 26000;
    private int numeroPianeti = 0;
    ArrayList<Pianeta> pianeti = new ArrayList<Pianeta>();
    
    //default Constructor

    public boolean addPianeta(Pianeta nuovo) {
      
    	if(numeroPianeti < MAX_PIANETI) {
    		pianeti.add(nuovo);
        	numeroPianeti++;
        	return true;
    	}
    	else 
    		return false;
    }
   
    private Pianeta trovaPianeta(String pianeta) {
    	Pianeta trovato;
    	return trovato;
    }

    public void removePianeta(String daRimuovere) {
    	pianeti.remove(trovaPianeta(daRimuovere));
    	numeroPianeti--;
    }
}
