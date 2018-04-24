import java.util.ArrayList;

public class SistemaStellare {


	private static final int MAX_PIANETI = 26000;
	private static final int ELEMENTI_PERCORSO = 2;

	private Stella stella;
	private ArrayList<Pianeta> pianetiSistema = new ArrayList<>();
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

		for(int i = 0; i < pianetiSistema.size(); i++) {

			ascissa += pianetiSistema.get(i).getPosizione().getAscissa() * pianetiSistema.get(i).getMassa();
			ordinata += pianetiSistema.get(i).getPosizione().getOrdinata() * pianetiSistema.get(i).getMassa();

			for(int j = 0; j < pianetiSistema.get(i).lune.size(); j++) {
				ascissa += pianetiSistema.get(i).lune.get(j).getPosizione().getAscissa() * pianetiSistema.get(i).lune.get(j).getMassa();
				ordinata += pianetiSistema.get(i).lune.get(j).getPosizione().getOrdinata() * pianetiSistema.get(i).lune.get(j).getMassa();
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
		if(pianetiSistema.size() < MAX_PIANETI) {
			pianetiSistema.add(nuovo);
			return true;
		}

		else return false;
	}

	public boolean removePianeta(String nome) {
		rimozione = true;
		Pianeta rimosso = trovaPianeta(nome);
		if(rimosso != null) {
			massaTotale -= rimosso.massa;
			aggiornaSommaPesata(rimosso);
			calcoloCentroDiMassa();
			pianetiSistema.remove(rimosso);

			return true;
		}
		else return false;
	}

	public boolean addLuna(String riferimento, Luna nuovaLuna) {
		if(sovrapposizioni(nuovaLuna) || nomiUguali(nuovaLuna) || trovaPianeta(riferimento) == null) {
			return false;
		}
		rimozione = false;
		massaTotale += nuovaLuna.massa;
		aggiornaSommaPesata(nuovaLuna);
		calcoloCentroDiMassa();
		return trovaPianeta(riferimento).addLuna(nuovaLuna);
	}

	public boolean removeLuna(String riferimento, String nomeLuna) {
		rimozione = true;
		Luna rimossa = trovaPianeta(riferimento).removeLuna(nomeLuna);
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
		for(int i = 0; i < pianetiSistema.size(); i++) {
			if(nuovo.controlloCoincidenza(pianetiSistema.get(i)))
				return true;
			else {
				for(int j = 0; j < pianetiSistema.get(i).lune.size(); j++) {
					if(nuovo.controlloCoincidenza(pianetiSistema.get(i).lune.get(j)))
						return true;
				}
			}
		}
		return false;
	}

	public boolean nomiUguali(CorpoCeleste nuovo) {
		if(nuovo.getNome().equals(stella.nome))
			return false;
		for(int i = 0; i < pianetiSistema.size(); i++) {
			if(nuovo.controlloNomi(pianetiSistema.get(i)))
				return true;
			else {
				for(int j = 0; j < pianetiSistema.get(i).lune.size(); j++) {
					if(nuovo.controlloNomi(pianetiSistema.get(i).lune.get(j)))
						return true;
				}
			}
		}
		return false;
	}

	public CorpoCeleste trovaCorpoCeleste(String nomeCorpoCeleste) {

		for(int i = 0; i < pianetiSistema.size(); i++) {
			if(pianetiSistema.get(i).getNome().equals(nomeCorpoCeleste))
				return pianetiSistema.get(i);
			else {
				for(int j = 0; j < pianetiSistema.get(i).lune.size(); j++) {
					if(pianetiSistema.get(i).lune.get(j).getNome().equals(nomeCorpoCeleste))
						return pianetiSistema.get(i).lune.get(j);
				}
			}
		}
		return null;
	}

	public Pianeta trovaPianeta(String pianeta) {

		for(int i = 0; i < pianetiSistema.size(); i++) {
			if(pianetiSistema.get(i).getNome().equals(pianeta))
				return pianetiSistema.get(i);
		}
		return null;
	}

	public CorpoCeleste[] getPercorso(String diQualeLuna) {

		CorpoCeleste[] percorso = new CorpoCeleste[ELEMENTI_PERCORSO];

		for(int i = 0; i  < pianetiSistema.size(); i++) {
			if(pianetiSistema.get(i).lune.contains(pianetiSistema.get(i).trovaLuna(diQualeLuna))) {
				percorso[0] = pianetiSistema.get(i);
				percorso[1] = pianetiSistema.get(i).trovaLuna(diQualeLuna);
				return percorso;
			}
		}
		return null;
	}
}