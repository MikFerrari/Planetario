import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Main {
    private static final String MESSAGGIO_BENVENUTO = "Buongiorno, benvenuto nel programma per la gestione dei sistemi stellari!";
    private static final String INSERIMENTO_NOME_STELLA = "Inserisca il nome della stella";
    private static final String INSERIMENTO_MASSA = "Inserisca la massa di %s";
    private static final int MIN_MASSA = 0;

    private static final String TITOLO_MENU = "Selezioni un'opzione";
    private static final String AGGIUNTA_PIANETA= "Aggiungere nuovo pianeta";
    private static final String AGGIUNTA_LUNA= "Aggiungere nuova luna";
    private static final String RIMOZIONE_PIANETA = "Rimuovere pianeta ";
    private static final String RIMOZIONE_LUNA = "Rimuovere luna";
    private static final String RICERCA_CORPO_CELESTE = "Ricerca corpo celeste";
    private static final String LUNE_ORBITANTI = "Visualizzazione lune orbitanti intorno da un pianeta";
    private static final String CALCOLO_CENTRO= "Calcolo del centro di massa di %s";
    private static final String CALCOLO_PERCORSO = "Calcolo percorso verso una luna";

    private static final String[] OPZIONI_MENU = {
            AGGIUNTA_PIANETA, AGGIUNTA_LUNA,
            RIMOZIONE_PIANETA, RIMOZIONE_LUNA,
            RICERCA_CORPO_CELESTE, LUNE_ORBITANTI,
            CALCOLO_CENTRO, CALCOLO_PERCORSO
            };

    public static void main(String[] args) {
        int scelta;
        String nomeSistema;
        MyMenu menu = new MyMenu(TITOLO_MENU,OPZIONI_MENU);
        SistemaStellare sistema = init();
        nomeSistema = sistema.nomeSistema();
        do {
            scelta = menu.scegli();
            switch (scelta){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    break;
            }
        } while (scelta!=0);

    }
    private static SistemaStellare init() {
        double massa;
        String nomeStella;
        System.out.println(MESSAGGIO_BENVENUTO);
        nomeStella = InputDati.leggiStringa(INSERIMENTO_NOME_STELLA);
        massa = InputDati.leggiDoubleConMinimo(String.format(INSERIMENTO_MASSA, nomeStella), MIN_MASSA);
        return new SistemaStellare(new Stella(nomeStella, massa));
    }
}
