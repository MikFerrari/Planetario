public class SistemaStellare {

	private Stella stella;
	private ElencoPianeti pianetiSistema;
	
	private Punto centroDiMassa;
	
	public SistemaStellare(Stella stella, ElencoPianeti pianetiSistema) {
		this.stella = stella;
		this.pianetiSistema = pianetiSistema;
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
	
	public Stella getStella() {
		return stella;
	}
	
	
	
}
