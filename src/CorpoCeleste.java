public class CorpoCeleste {

    protected double massa;
    protected String nome;
    protected String hash;
    protected Punto posizione;
    protected static int contatore = 1;

    CorpoCeleste(double massa, Punto posizione) {
        nome = "" + contatore++;
        this.massa = massa;
        hash = creaHash();
        this.posizione = posizione;
    }

    CorpoCeleste(String nome, double massa, Punto posizione) {
        this.nome = nome;
        this.massa = massa;
        hash = creaHash();
        this.posizione = posizione;
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
