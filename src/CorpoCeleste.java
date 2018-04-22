/**
 * 
 * @author Just E.A.T.
 * Questa classe � il prototipo di un oggetto corpo celeste
 *
 */

public class CorpoCeleste {

    protected double massa;
    protected String nome;
    protected String hash;
    protected Punto posizione;
    protected static int contatore = 1;
    
    private ElencoPianeti pianetiSistema = new ElencoPianeti();

    
    public CorpoCeleste(String nome, double massa, Punto posizione) {
        
    	if(nome.equals(""))
        	this.nome = "" + contatore++;
        else this.nome = nome;
        
        this.massa = massa;
        hash = creaHash();
        this.posizione = posizione;
    }
    
    
//scorre l'arraylist di pianeti e i relativi arraylist di lune alla ricerca del corpo celeste che corrisponde al nome cercato
    
    public CorpoCeleste trovaCorpoCeleste(String nomeCorpoCeleste) {
    	
    	for(int i = 0; i < pianetiSistema.pianeti.size(); i++) {
    		if(pianetiSistema.pianeti.get(i).getNome().equals(nomeCorpoCeleste))
    			return pianetiSistema.pianeti.get(i);
    		else {
    			for(int j = 0; j < pianetiSistema.pianeti.get(i).lune.size(); j++) {
    				if(pianetiSistema.pianeti.get(i).lune.get(j).getNome().equals(nomeCorpoCeleste))
    					return pianetiSistema.pianeti.get(i).lune.get(j);
    			}  
    		}
    	}
    	return null;  	
    }
    
//in base al nome del corpo celeste di riferimento richiama il metodo per trovare il corpo con quel nome e una volta trovato ne calcola la posizione relativa
    
    public Punto posizioneRelativa(String riferimento) {
   
    	Punto posizioneRel = new Punto(0,0,"Posizione del corpo celeste " + this.getPosizione() + " rispetto al corpo celeste " + trovaCorpoCeleste(riferimento).getNome());
    	posizioneRel.setAscissa(this.getPosizione().getAscissa()-trovaCorpoCeleste(riferimento).getPosizione().getAscissa());
    	posizioneRel.setOrdinata(this.getPosizione().getOrdinata()-trovaCorpoCeleste(riferimento).getPosizione().getOrdinata());
    	return posizioneRel;
    	
    }

    private String creaHash(){
        String hash = "" + nome + hashCode();
        return hash;
    }

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		return result;
	}

	public double getMassa() {
        return massa;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public String getHash() {
        return hash;
    }

    //metodo posizione assoluta 
    public Punto getPosizione() {
        return posizione;
    }

    public void setPosizione(Punto posizione) {
        this.posizione = posizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean controlloCoincidenza(CorpoCeleste altro) {
    	if(posizione.getAscissa() == altro.posizione.getAscissa() && posizione.getOrdinata() == altro.posizione.getOrdinata())
    		return true;
    	else return false;
    }
}