public class SistemaStellare {

	private Stella stella;
	private ElencoPianeti pianetiSistema = new ElencoPianeti();
	
	private double massaTotale = 0;
	private Punto sommaPesataPosizioni = new Punto(0, 0, "Somma Pesata Delle Posizioni");
	private Punto centroDiMassa = new Punto(0, 0, "Centro Di Massa");

	
	public SistemaStellare(Stella stella) {
		this.stella = stella;
	}
	
	public String nomeSistema() {
		return "Sistema della stella " + stella.getNome();
	}
	
//il nome massaTotale del metodo si confondeva con l'attributo massaTotale
	
	public void calcoloMassaTotale() { 
		double massa = 0;
		for(int i = 0; i < pianetiSistema.pianeti.size(); i++) {
	
			massa += pianetiSistema.pianeti.get(i).getMassa(); //massa le masse di tutti i pianeti
			
			for(int j = 0; j < pianetiSistema.pianeti.get(i).lune.size(); j++) {
				massa += pianetiSistema.pianeti.get(i).lune.get(j).getMassa(); //massa le masse di tutte le lune orbitanti attorno a un pianeta
			}
					
		}
		massa += stella.getMassa(); //aggiunge la massa della stella
		
		massaTotale = massa;
	}
	

//il nome sommaPesataPosizioni del metodo si confondeva con l'attributo sommaPesataPosizioni

	public void calcoloSommaPesataPosizioni() {
		
		double ascissa = 0;
		double ordinata = 0;
		
		for(int i = 0; i < pianetiSistema.pianeti.size(); i++) {
			
			ascissa += pianetiSistema.pianeti.get(i).getPosizione().getAscissa() * pianetiSistema.pianeti.get(i).getMassa();
			ordinata += pianetiSistema.pianeti.get(i).getPosizione().getOrdinata() * pianetiSistema.pianeti.get(i).getMassa();
			
			for(int j = 0; j < pianetiSistema.pianeti.get(i).lune.size(); j++) {
				ascissa += pianetiSistema.pianeti.get(i).lune.get(j).getPosizione().getAscissa() * pianetiSistema.pianeti.get(i).lune.get(j).getMassa();
				ordinata += pianetiSistema.pianeti.get(i).lune.get(j).getPosizione().getOrdinata() * pianetiSistema.pianeti.get(i).lune.get(j).getMassa();
			}
					
		}
		sommaPesataPosizioni.setAscissa(ascissa);
		sommaPesataPosizioni.setOrdinata(ordinata);
	}
	
//il nome centroDiMassa del metodo si confondeva con l'attributo centroDiMassa
	
	public void calcoloCentroDiMassa() {
		
		double ascissa = sommaPesataPosizioni.getAscissa() / massaTotale;
		double ordinata = sommaPesataPosizioni.getOrdinata() / massaTotale;
		
		centroDiMassa.setAscissa(ascissa);
		centroDiMassa.setOrdinata(ordinata);
	}
	
	public Stella getStella() {
		return stella;
	}
/*
	private void setCentroDiMassa(Punto centroDiMassa) {
		this.centroDiMassa = centroDiMassa;
	}

	private void setMassaTotale(double massaTotale) {
		this.massaTotale = massaTotale;
	}

	private void setSommaPesataPosizioni(Punto sommaPesataPosizioni) {
		this.sommaPesataPosizioni = sommaPesataPosizioni;
	}

	private double getMassaTotale() {
		return massaTotale;
	}

	public Punto getCentroDiMassa() {
		return centroDiMassa;
	}

	private Punto getSommaPesataPosizioni() {
		return sommaPesataPosizioni;
	}
	*/

	public boolean addPianeta(Pianeta nuovo) {
		massaTotale += nuovo.massa;
		//aggiorno somma pesata
		calcoloCentroDiMassa();
		return pianetiSistema.addPianeta(nuovo);
	}

	public void removePianeta(String nome) {
		Pianeta rimosso = pianetiSistema.removePianeta(nome);
		massaTotale -= rimosso.massa;
		//aggiorno somma pesata
		calcoloCentroDiMassa();
	}
}