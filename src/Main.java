import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

import java.util.Scanner;


public class Main {
    private static final String MESSAGGIO_BENVENUTO = "Buongiorno, benvenuto nel programma per la gestione dei sistemi stellari!";
    private static final String INSERIMENTO_NOME_STELLA = "Inserisca il nome della stella ";
    private static final String INSERIMENTO_MASSA = "Inserisca la massa di %s ";
    private static final String NOME_CORPO_CELESTE = "Inserisca il nome ";
    private static final String MESSAGGIO_COORDINATE = "Inserisca le coordinate ";
    private static final String MESSAGGIO_RIMOZIONE= "Inserisca il nome del pianeta da rimuovere";
    private static final String MESSAGGIO_RIMOZIONE_COMPLETATA = "Il corpo celeste è stato rimosso con successo";
    private static final String AGGIUNTA_RIMOZIONE = "Aggiungere/Rimuovere corpo celeste";

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
    private static final String MESSAGGIO_RIMOZIONE_FALLITO ="Operazione fallita: corpo celeste non trovato";

    private static final String[] OPZIONI_MENU = {
            RICERCA_CORPO_CELESTE, LUNE_ORBITANTI,
            CALCOLO_CENTRO, CALCOLO_PERCORSO
            };
    private static final String[] OPZIONI_MENU_ADD_REMOVE = {
            AGGIUNTA_RIMOZIONE,
            AGGIUNTA_PIANETA,AGGIUNTA_LUNA,
            RIMOZIONE_PIANETA,RIMOZIONE_LUNA
            };

    public static void main(String[] args) {
        int scelta;
        String nomeSistema;
        MyMenu menu = new MyMenu(TITOLO_MENU, OPZIONI_MENU);
        MyMenu menuAddRemove = new MyMenu(TITOLO_MENU, OPZIONI_MENU_ADD_REMOVE);
        SistemaStellare sistema = init();
        nomeSistema = sistema.nomeSistema();

        do {
            scelta = menu.scegli();
            switch (scelta){
                case 1:
                    switch (menuAddRemove.scegli()){
                        case 1:
                            sistema.addPianeta(initPianeta());
                            break;
                        case 2:
                            break;
                        case 3:
                            if(sistema.removePianeta(InputDati.leggiStringa(MESSAGGIO_RIMOZIONE)))
                                System.out.println(MESSAGGIO_RIMOZIONE_COMPLETATA);
                            else
                                System.out.println(MESSAGGIO_RIMOZIONE_FALLITO);
                            break;
                        case 4:
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:

                    break;
                case 3:

                case 4:
                    break;
                case 5:
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
    private static Pianeta initPianeta(){
        String nome;
        double massa, x, y;
        Punto posizone;

        Scanner tastiera = new Scanner(System.in);
        nome = InputDati.leggiStringa(NOME_CORPO_CELESTE);
        massa = InputDati.leggiDoubleConMinimo(String.format(INSERIMENTO_MASSA, nome), MIN_MASSA);
        System.out.print(MESSAGGIO_COORDINATE);
        x = tastiera.nextDouble();
        y = tastiera.nextDouble();
        posizone = new Punto(x, y, nome);
        return new Pianeta(nome,massa,posizone);
    }

    private static Luna initLuna(){
        String nome;
        double massa, x, y;
        Punto posizone;

        Scanner tastiera = new Scanner(System.in);
        nome = InputDati.leggiStringa(NOME_CORPO_CELESTE);
        massa = InputDati.leggiDoubleConMinimo(String.format(INSERIMENTO_MASSA, nome), MIN_MASSA);
        System.out.print(MESSAGGIO_COORDINATE);
        x = tastiera.nextDouble();
        y = tastiera.nextDouble();
        posizone = new Punto(x, y, nome);
        return new Luna(nome,massa,posizone);
    }
}
