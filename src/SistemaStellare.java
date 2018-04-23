public class SistemaStellare {

	private Stella stella;
	private ElencoPianeti pianetiSistema = new ElencoPianeti();
	
	private boolean rimozione;
	
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
	
	public void initMassaTotale() {
		massaTotale = stella.getMassa(); //aggiunge la massa della stella

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
	
	private void aggiornaSommaPesata(CorpoCeleste nuovo) {
		
		double ascissa = 0;
		double ordinata = 0;
		
		ascissa = sommaPesataPosizioni.getAscissa();
		ordinata = sommaPesataPosizioni.getOrdinata();
		
		if(!rimozione) {
			ascissa += (nuovo.getPosizione().getAscissa() * nuovo.getMassa());
			ordinata += (nuovo.getPosizione().getOrdinata() * nuovo.getMassa());
		}
		else {
			ascissa -= (nuovo.getPosizione().getAscissa() * nuovo.getMassa());
			ordinata -= (nuovo.getPosizione().getOrdinata() * nuovo.getMassa());
		}
		
		sommaPesataPosizioni.setAscissa(ascissa);
		sommaPesataPosizioni.setOrdinata(ordinata);
		
	}
	
//il nome centroDiMassa del metodo si confondeva con l'attributo centroDiMassa
	
	public void calcoloCentroDiMassa() {
		
		double ascissa = (sommaPesataPosizioni.getAscissa() / massaTotale);
		double ordinata = (sommaPesataPosizioni.getOrdinata() / massaTotale);
		
		centroDiMassa.setAscissa(ascissa);
		centroDiMassa.setOrdinata(ordinata);
	}
	
	public Stella getStella() {
		return stella;
	}

	public Punto getCentroDiMassa() {
		return centroDiMassa;
	}


	public boolean addPianeta(Pianeta nuovo) {
		if(sovrapposizioni(nuovo) || nomiUguali(nuovo)) {
			return false;
		}
		rimozione = false;
		massaTotale += nuovo.massa;
		aggiornaSommaPesata(nuovo);
		calcoloCentroDiMassa();
		return pianetiSistema.addPianeta(nuovo);
	}

	public boolean removePianeta(String nome) {
		rimozione = true;
		Pianeta rimosso = pianetiSistema.removePianeta(nome);
		if(rimosso != null) {
			massaTotale -= rimosso.massa;
			aggiornaSommaPesata(rimosso);
			calcoloCentroDiMassa();
			return true;
		}
		else return false;
	}
	
	public boolean addLuna(String riferimento, Luna nuovaLuna) {
		if(sovrapposizioni(nuovaLuna) || nomiUguali(nuovaLuna) || pianetiSistema.trovaPianeta(riferimento) == null) {
			return false;
		}
		rimozione = false;
		massaTotale += nuovaLuna.massa;
		aggiornaSommaPesata(nuovaLuna);
		calcoloCentroDiMassa();
		return pianetiSistema.trovaPianeta(riferimento).addLuna(nuovaLuna);
	}
	
	public boolean removeLuna(String riferimento, String nomeLuna) {
		rimozione = true;
		Luna rimossa = pianetiSistema.trovaPianeta(riferimento).removeLuna(nomeLuna);
		if(rimossa != null) {
			massaTotale -= rimossa.massa;
			aggiornaSommaPesata(rimossa);
			calcoloCentroDiMassa();
			return true;
		}
		else return false;
	}
	
	public boolean sovrapposizioni(CorpoCeleste nuovo) {
		if(nuovo.controlloCoincidenza(stella))
			return true;
		for(int i = 0; i < pianetiSistema.pianeti.size(); i++) {
			if(nuovo.controlloCoincidenza(pianetiSistema.pianeti.get(i)))
				return true;
			else {
				for(int j = 0; j < pianetiSistema.pianeti.get(i).lune.size(); j++) {
					if(nuovo.controlloCoincidenza(pianetiSistema.pianeti.get(i).lune.get(j)))
						return true;
				}
			}
		}
		return false;
	}

	public boolean nomiUguali(CorpoCeleste nuovo) {
		for(int i = 0; i < pianetiSistema.pianeti.size(); i++) {
			if(nuovo.controlloNomi(pianetiSistema.pianeti.get(i)))
				return true;
			else {
				for(int j = 0; j < pianetiSistema.pianeti.get(i).lune.size(); j++) {
					if(nuovo.controlloNomi(pianetiSistema.pianeti.get(i).lune.get(j)))
						return true;
				}
			}
		}
		return false;
	}

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

	public Pianeta trovaPianeta(String ricercato) {
		return pianetiSistema.trovaPianeta(ricercato);
	}

	public CorpoCeleste[] getPercorso(String luna) {
		return pianetiSistema.getPercorso(luna);
	}
}