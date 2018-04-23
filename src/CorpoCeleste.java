/**
 * 
 * @author Just E.A.T.
 * Questa classe è il prototipo di un oggetto corpo celeste
 *
 */

public class CorpoCeleste {

    protected double massa;
    protected String nome;
    protected String hash;
    protected Punto posizione;
    protected static int contatore = 1;

    
    public CorpoCeleste(String nome, double massa, Punto posizione) {
        
    	if(nome.equals(""))
        	this.nome = "" + contatore++;
        else this.nome = nome;
        
        this.massa = massa;
        hash = creaHash();
        this.posizione = posizione;
    }
    
    
//scorre l'arraylist di pianeti e i relativi arraylist di lune alla ricerca del corpo celeste che corrisponde al nome cercato
    

    
//in base al nome del corpo celeste di riferimento richiama il metodo per trovare il corpo con quel nome e una volta trovato ne calcola la posizione relativa
    
    public Punto posizioneRelativa(Pianeta riferimento) {
   
    	Punto posizioneRel = new Punto(0,0,"Posizione del corpo celeste " + this.getPosizione() + " rispetto al corpo celeste " + riferimento.getNome());
    	posizioneRel.setAscissa(this.getPosizione().getAscissa()-riferimento.getPosizione().getAscissa());
    	posizioneRel.setOrdinata(this.getPosizione().getOrdinata()-riferimento.getPosizione().getOrdinata());
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
        return (posizione.getAscissa() == altro.posizione.getAscissa()) && (posizione.getOrdinata() == altro.posizione.getOrdinata());
    }

    public boolean controlloNomi(CorpoCeleste altro) {
        return nome.equals(altro.nome);
    }

    public String toString() {
        return "\n" + nome + "\nMassa: " + massa + "\nPosizione" + posizione.coordinate() + "\n";
    }
}