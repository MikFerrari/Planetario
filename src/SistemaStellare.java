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
	
	public double massaTotale() { 
		double massa = 0;
		for(int i = 0; i < pianetiSistema.pianeti.size(); i++) {
	
			massa += pianetiSistema.pianeti.get(i).getMassa(); //massa le masse di tutti i pianeti
			
			for(int j = 0; j < pianetiSistema.pianeti.get(i).lune.size(); j++) {
				massa += pianetiSistema.pianeti.get(i).lune.get(j).getMassa(); //massa le masse di tutte le lune orbitanti attorno a un pianeta
			}
					
		}
		massa += stella.getMassa(); //aggiunge la massa della stella
		
		return massa;
	}
	
	public void sommaPesataPosizioni() {
		
		double ascissa = 0;
		double ordinata = 0;
		
		for(int i = 0; i < pianetiSistema.pianeti.size(); i++) {
			
			ascissa = pianetiSistema.pianeti.get(i).getPosizione().getAscissa() * pianetiSistema.pianeti.get(i).getMassa();
			ordinata = pianetiSistema.pianeti.get(i).getPosizione().getOrdinata() * pianetiSistema.pianeti.get(i).getMassa();
			
			for(int j = 0; j < pianetiSistema.pianeti.get(i).lune.size(); j++) {
				ascissa += pianetiSistema.pianeti.get(i).lune.get(j).getPosizione().getOrdinata() *
							   pianetiSistema.pianeti.get(i).lune.get(j).getMassa();
			}
					
		}
		sommaPesataPosizioni.setAscissa(ascissa);
		sommaPesataPosizioni.setOrdinata(ordinata);
	}
	
	public void centroDiMassa() {
		
		double ascissa = sommaPesataPosizioni.getAscissa() / massaTotale;
		double ordinata = sommaPesataPosizioni.getOrdinata() / massaTotale;
		
		centroDiMassa.setAscissa(ascissa);
		centroDiMassa.setOrdinata(ordinata);
	}
	
	public Stella getStella() {
		return stella;
	}
	
	/*public ElencoPianeti getPianetiSistema() {
		return pianetiSistema;
	}*/
	public void addPianeta(Pianeta nuovo){
		pianetiSistema.addPianeta(nuovo);
	}
}