
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

    public CorpoCeleste(double massa, Punto posizione) {
        nome = "" + contatore++;
        this.massa = massa;
        hash = creaHash();
        this.posizione = posizione;
    }
    
    public CorpoCeleste(String nome, double massa, Punto posizione) {
        this.nome = nome;
        this.massa = massa;
        hash = creaHash();
        this.posizione = posizione;
    }
    
    public Punto posizioneRelativa(CorpoCeleste riferimento) {
   
    	Punto posizioneRel = new Punto(0,0,"");
    	posizioneRel.setAscissa(this.getPosizione().getAscissa()-riferimento.getPosizione().getAscissa());
    	posizioneRel.setOrdinata(this.getPosizione().getOrdinata()-riferimento.getPosizione().getOrdinata());
    	return posizioneRel;
    	
    }

    private String creaHash(){
        String hash = "";

        return hash;
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
}
