package edu.ub.prog2.MarinVegaJuan.vista;

import edu.ub.prog2.MarinVegaJuan.controlador.CtrlReproductor;
import edu.ub.prog2.MarinVegaJuan.model.FitxerAudio;
import edu.ub.prog2.MarinVegaJuan.model.LlistaReproduccio;
import edu.ub.prog2.utils.FitxerAudioErrorException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clase principal del reproductor de musica, implementa tambe el menu per navegar
 * i utilitzar les diferents opcions que implementa el reproductor2
 * @author jmarinve7.alumnes
 */
public class Reproductor2 {

    private CtrlReproductor _ctrl;

    // Declarem les opcions per a referir-se a les opcions del menú.
    static private enum OpcionsMenuReproductor2 {

        MENU_BIBLIOTECA, MENU_LLISTES_REPRODUCCIO, MENU_CONTROL_REPRODUCCIO, MENU_PRINCIPAL_DESAR_DADES, MENU_PRINCIPAL_CARREGAR_DADES, MENU_PRINCIPAL_SORTIR
    }

    
    

       

        ;
        
        
        
    

    
    

    static private  enum opcionsMenuBiblioteca {

        AFEGIR_FITXER_AUDIO, ELIMINAR_FITXER_AUDIO, MOSTRAR_BIBLIOTECA, SORTIR_MENU_BIBLIOTECA
        
        
    }

    
    

       

        ;
        
        
        
        
        
        
    

    
    

    static private  enum OpcionsMenuLlistesReprocuccio {

        CREAR_LLISTA, ELIMINAR_LLISTA, MOSTAR_LLISTA, AFEGIR_FITXER_AUDIO_LLISTA, ELIMINAR_FITXER_AUDIO_LLISTA, 

    
    
     SORTIR_MENU_LLISTES
       };

    static private enum OpcionsMenuControlReproduccio {

        REPRODUIR_CANCO, REPRODUIR_BIBLIOTECA, REPRODUIR_LLISTA, I_O_REPRODUCCIO_ALEATORIA, I_O_REPRODUCCIO_CICLICA,MENU_REPRODUCCIO, SORTIR_MENU_CONTROL_REPRODUCCIO
    };
        static private enum OpcionsMenuReproduccio {

        ANTERIOR,  PLAY_PAUSE, SEGUENT, STOP, SORTIR_MENU_REPRODUCCIO
    };
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuPrincipal = {"Gestió biblioteca",
        "Gestió llistes de reproducció", "Control de reproducció", "Guardar biblioteca", "Carregar biblioteca",
        "Sortir"
    };
    // Declarem descripcions personalitzades per a les opcions del menú secundari
    static private String[] descMenuBiblioteca = {"Afegir fitxer d'audio", "Eliminar fitxer d'audio",
        "Mostrar la biblioteca",
        "Tornar al menú anterior"
    };
    static private String[] descMenuLlistesReproduccio = {"Crear llista", "Eliminar llista", "Mostrar llista ",
        "Afegir fitxer d'audio a llista", "Eliminar fitxer de llista",
        "Tornar al menú anterior"
    };
    static private String[] descMenuControlReproduccio = {"Reproduir una cançó", "Reproduir tota la biblioteca", "Reproduir una llista de reproducció",
        "Activar / Desactivar reproducció aleatoria", "Activar / Desactivar reproducció cíclica", "Menu reproduccio", "Tornar al menú anterior"
    };
    static private String[] descMenuReproduccio = {"Anterior", "Play / Pause", "Seguent",
        "Stop", "Tornar al menu anterior"
    };

    /**
     * Fitxer principal. Crea el menú principal i un menú secundari
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // Creem un objecte principal
        Reproductor2 repro = new Reproductor2();
        // Iniciem la gestió del menú principal de l'aplicació
        repro.gestioMenuReproductor2(sc);
    }

    public Reproductor2() {
        _ctrl = new CtrlReproductor();

    }

    /**
     * Menú principal del reproductor d'audio
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */
    private void gestioMenuReproductor2(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuReproductor2> menu = new Menu<OpcionsMenuReproductor2>("Menu Principal ", OpcionsMenuReproductor2.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsMenuReproductor2 opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                case MENU_BIBLIOTECA:
                    // Cridem el mètode de gestió del menú secundari
                    gestioMenuBilbioteca(sc);
                    break;
                case MENU_LLISTES_REPRODUCCIO:
                    // Cridem el mètode de gestió del menú secundari
                    gestioMenuLlistesReproduccio(sc);
                    break;
                case MENU_CONTROL_REPRODUCCIO:
                    // Cridem el mètode de gestio del control de reproduccio
                    gestioControlReproduccio(sc);
                    break;
                case MENU_PRINCIPAL_DESAR_DADES:
                    // Cridem el mètode per desar les dades
                    desarDadesReproductor();
                    break;
                case MENU_PRINCIPAL_CARREGAR_DADES:
                    // Cridem el mètode per recuperar les dades
                    carregarDadesReproductor();
                    break;
                case MENU_PRINCIPAL_SORTIR:
                    System.out.println("Tancant l'alicació...");
                    break;
            }

        } while (opcio != OpcionsMenuReproductor2.MENU_PRINCIPAL_SORTIR);
    }

    /**
     * Menú secundari
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */
    private void gestioMenuBilbioteca(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<opcionsMenuBiblioteca> menu = new Menu<opcionsMenuBiblioteca>("Gestió biblioteca", opcionsMenuBiblioteca.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuBiblioteca);

        // Obtenim una opció des del menú i fem les accions pertinents
        opcionsMenuBiblioteca opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                case AFEGIR_FITXER_AUDIO:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("1 - Afegir fitxer d'audio\n");
                    afegirNouFitxer(sc);
                    break;
                case ELIMINAR_FITXER_AUDIO:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("2 - Eliminar fitxer d'audio\n");
                    eliminarFitxer(sc);
                    break;
                case MOSTRAR_BIBLIOTECA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("3 - Mostrar biblioteca\n");
                    mostrarBiblioteca();
                    break;
                case SORTIR_MENU_BIBLIOTECA:
                    System.out.println("4 - Enrere");
                    break;
            }

        } while (opcio != opcionsMenuBiblioteca.SORTIR_MENU_BIBLIOTECA);
    }

    private void gestioMenuLlistesReproduccio(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuLlistesReprocuccio> menu = new Menu<OpcionsMenuLlistesReprocuccio>("Menu llistes de reproducció", OpcionsMenuLlistesReprocuccio.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuLlistesReproduccio);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsMenuLlistesReprocuccio opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                case CREAR_LLISTA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("1 - Crear llista\n");
                    crearLlista(sc);
                    break;
                case ELIMINAR_LLISTA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("2 - Eliminar llista\n");
                    eliminarLlista(sc);
                    break;
                case MOSTAR_LLISTA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("3 - Mostrar llista\n");
                    mostrarLlista(sc);
                    break;
                case AFEGIR_FITXER_AUDIO_LLISTA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("4 - Afegir fitxer d'audio\n");
                    afegirFitxerAudioALlista(sc);
                    break;
                case ELIMINAR_FITXER_AUDIO_LLISTA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("5 - Eliminar fitxer d'audio\n");
                    eliminarFitxerAudioALlista(sc);
                    break;
                case SORTIR_MENU_LLISTES:
                    System.out.println("6 - Enrere");
                    break;
            }

        } while (opcio != OpcionsMenuLlistesReprocuccio.SORTIR_MENU_LLISTES);
    }

    private void gestioControlReproduccio(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuControlReproduccio> menu = new Menu<OpcionsMenuControlReproduccio>("Menu control de reproducció", OpcionsMenuControlReproduccio.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuControlReproduccio);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsMenuControlReproduccio opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                // Cridem al metode per reproduir una canço
                case REPRODUIR_CANCO:
                    reproduirFitxerAudio(sc);
                    break;
                case REPRODUIR_BIBLIOTECA:
                    reproduirBiblioteca();
                    break;
                case REPRODUIR_LLISTA:
                    reproduirLlistaReproduccio(sc);
                    break;
                case I_O_REPRODUCCIO_ALEATORIA:
                    pushReproduccioAleatoria();
                    break;
                case I_O_REPRODUCCIO_CICLICA:
                    pushReproduccioCiclica();
                    break;
                case MENU_REPRODUCCIO:
                    gestioMenuReproduccio(sc);
                    break;
                case SORTIR_MENU_CONTROL_REPRODUCCIO:
                    System.out.println("6 - Enrere");
                    break;

            }

        } while (opcio != OpcionsMenuControlReproduccio.SORTIR_MENU_CONTROL_REPRODUCCIO);


    }

    private void gestioMenuReproduccio(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuReproduccio> menu = new Menu<OpcionsMenuReproduccio>("Menu  de reproducció", OpcionsMenuReproduccio.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuReproduccio);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsMenuReproduccio opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                // Cridem al metode per reproduir una canço
                case ANTERIOR:
                    reproduirFitxerAnterior();
                    break;
                case PLAY_PAUSE:
                    premerPlayPause();
                    break;
                case SEGUENT:
                    reproduirFitxerSeguent();
                    break;
                case STOP:
                    premerStop();
                    break;
                case SORTIR_MENU_REPRODUCCIO:
                    System.out.println("6 - Enrere");
                    break;
            }

        } while (opcio != OpcionsMenuReproduccio.SORTIR_MENU_REPRODUCCIO);


    }

    /**
     * Funcio que rep una frase que mostrara per pantalla i recollira
     * la dada que introdueixi l'usuari
     * @param frase
     * @param sc
     * @return
     */
    public String promptString(String frase, Scanner sc) {
        System.out.println(frase);
        return sc.nextLine();

    }

    /**
     * Funcio que rep una frase, la mostra per pantalla i recull la dada
     * que introdueixi l'usuari, com a enter!!
     * @param frase
     * @param sc
     * @return
     */
    public int promptInt(String frase, Scanner sc) {
        System.out.print(frase);
        while (!sc.hasNextInt()) {

            sc.next();
            System.out.println(frase);
        }
        return sc.nextInt();

    }

    /**
     * Funcio que rep una frase, la mostra per pantalla i recull la dada
     * que introdueixi l'usuari, com a bolea!!
     * @param frase
     * @param sc
     * @return
     */
    public boolean promptBoolean(String frase, Scanner sc) {
        boolean resultat, tenimResposta;
        String resposta;
        tenimResposta = false;
        resultat = false;
        String opcions = "(y)si\t (n)no\n";
        System.out.println(frase);

        while (!tenimResposta) {
            System.out.println(opcions);
            resposta = sc.next();
            if (resposta.equals("y")) {
                tenimResposta = true;
                resultat = true;
            } else if (resposta.equals("n")) {
                tenimResposta = true;
                resultat = false;
            }
        }
        return resultat;
    }

    /**
     * Funcio que comproba si hi han llistes, sino, mostra l'error per pantalla
     * @return
     */
    public boolean comprobarExistenciaLlistes() {
        if (_ctrl.hasLists()) {
            return true;
        } else {
            System.out.println("Actualment no tens cap llista de reproducció");
            return false;
        }
    }

    /**
     * Funcio que crida al metode demanarDadesNouFitxer i s'encarrega
     * d'afetgirlo a la biblioteca
     * @param sc
     */
    public void afegirNouFitxer(Scanner sc) {
        //Afegim un fitxer d'audio retornat per la funcio demanar Dades
        _ctrl.afegirFitxerBiblioteca(demanarDadesNouFitxer(sc));

    }

    /**
     * Funcio que demana les dades necesaries per crear un nou fitxer, retornant aquest mateix
     * fitxer
     * @param sc
     * @return FitxerAudio
     */
    public FitxerAudio demanarDadesNouFitxer(Scanner sc) {
        String nomFitxer, extensio, localitzacio, nom, duracio;
        String autor, disc, discografica, genre;
        int anyEdicio, numSong;

        //Demanem les dades per a crear un nou fitxer
        nomFitxer = promptString("Inserti el nom del fitxer: ", sc);

        extensio = promptString("Inserti l'extensio: ", sc);

        localitzacio = promptString("Inserti la localitzacio: ", sc);

        nom = promptString("Nom de la canço: ", sc);

        autor = promptString("Autor: ", sc);

        disc = promptString("Disc: ", sc);

        anyEdicio = promptInt("Any edició: ", sc);

        numSong = promptInt("Posicio al disc: ", sc);

        discografica = promptString("Discografica: ", sc);

        genre = promptString("Genere musical: ", sc);

        duracio = promptString("Duració(ex: h:m:s): ", sc);

        FitxerAudio f = new FitxerAudio(nom, autor, disc, anyEdicio, numSong, discografica, genre, duracio, localitzacio, nomFitxer, extensio);
        return f;
    }

    /**
     * Funcio que mostra les dades de la biblioteca actual
     */
    public void mostrarBiblioteca() {
        if (_ctrl.bibliotecaHasFiles()) {
            System.out.println(_ctrl.getStringBiblioteca());
        } else {
            System.out.println("Actualment no tens dades a la biblioteca");
        }

    }

    /**
     * Funcio que demana les dades per eliminar el fitxer desitjat
     * @param sc
     */
    public void eliminarFitxer(Scanner sc) {
        // Comprobem que existeixen fitxers a la biblioteca
        if (_ctrl.bibliotecaHasFiles()) {
            //Mostrem la biblioteca i demanem les dades
            int n;
            System.out.println("Indica el num del fitxer a eliminar: ");
            System.out.println(_ctrl.getStringBiblioteca());
            System.out.print("Eliminar fitxer num: ");
            n = Integer.valueOf(sc.next());
            //Eliminem el fitxer, que retornara true si no ha succeit cap error
            if (_ctrl.eliminarFitxerBiblioteca(n)) {
                System.out.println("Fitxer eliminat correctament");
            } else {
                System.out.println("Ha succeit un error a l'hora d'eliminar el fitxer");
            }
        } else {
            System.out.println("Actualment no tens dades a la biblioteca");
        }
    }

    /**
     * Funcio que demana les dades per crear una nova llista de reproduccio
     * @param sc
     */
    public void crearLlista(Scanner sc) {
        LlistaReproduccio llista;
        System.out.println("Indica el nom per a la nova llista de reproducció: ");
        llista = new LlistaReproduccio(sc.nextLine());
        _ctrl.afegirLlistaReproduccio(llista);
    }

    /**
     * Funcio que s'encarrega de demanar la llista que es vol eliminar
     * @param sc
     */
    public void eliminarLlista(Scanner sc) {
        if (_ctrl.hasLists()) {
            System.out.println("Indica el num llista de reproduccio a eliminar: ");
            System.out.println(_ctrl.getStringLlistesActuals());
            System.out.print("Eliminar llista num: ");
            _ctrl.eliminarLlistaReproduccio(Integer.valueOf(sc.next()));
        } else {
            System.out.println("Actualment no tens cap llista de reproducció");
        }

    }

    /**
     * funcio que retorna per pantalla l'informacio rellevant de la llista de reproduccio
     * indicada
     * @param sc
     */
    public void mostrarLlista(Scanner sc) {
        if (_ctrl.hasLists()) {
            int lR;
            System.out.println("Indica el num llista de reproduccio a mostrar: ");
            System.out.println(_ctrl.getStringLlistesActuals());
            System.out.print("Mostrar llista num: ");
            lR = Integer.valueOf(sc.next());
            if (_ctrl.llistaHasFiles(lR)) {
                System.out.println(_ctrl.getStringLlistaReproduccio(lR));
            } else {
                System.out.println("Actualment no tens dades en aquesta llista");
            }
        } else {
            System.out.println("Actualment no tens cap llista de reproducció");
        }
    }

    /**
     * Funcio que afegeix el fitxder d'audio especificat a la llista espeficada
     * @param sc
     */
    public void afegirFitxerAudioALlista(Scanner sc) {
        if (_ctrl.hasLists()) {
            if (_ctrl.bibliotecaHasFiles()) {
                int f, lR;
                System.out.println("Indica el num del fitxer a afegir: ");
                System.out.println(_ctrl.getStringBiblioteca());
                System.out.print("Seleccionar fitxer num: ");
                f = Integer.valueOf(sc.next());
                System.out.println("Indica el num llista de reproduccio a la cual vols afegir el fitxer: ");
                System.out.println(_ctrl.getStringLlistesActuals());
                System.out.print("Afegir a llista num: ");
                lR = Integer.valueOf(sc.next());
                _ctrl.afegirFitxerLlista(f, lR);
            } else {
                System.out.println("Actualment no tens dades a la biblioteca");
            }
        } else {
            System.out.println("Actualment no tens cap llista de reproducció");
        }
    }

    /**
     * Funcio que elimina el fitxer d'audio desitjat de la llista especifiacada
     * @param sc
     */
    public void eliminarFitxerAudioALlista(Scanner sc) {
        if (_ctrl.hasLists()) {
            int f, lR;
            System.out.println("Indica el num llista de reproduccio de la cual vols eliminar un fitxer: ");
            System.out.println(_ctrl.getStringLlistesActuals());
            System.out.print("Seleccionar llista num: ");
            lR = Integer.valueOf(sc.next());

            if (_ctrl.llistaHasFiles(lR)) {
                System.out.println(_ctrl.getStringLlistaReproduccio(lR));
                System.out.print("Eliminar fitxer num: ");
                f = Integer.valueOf(sc.next());
                _ctrl.eliminarFitxerLlista(f, lR);
            } else {
                System.out.println("Actualment no tens dades en aquesta llista");
            }
        } else {
            System.out.println("Actualment no tens cap llista de reproducció");
        }
    }

    /**
     * Funcio que truca a controlador i desa tota la informacio del reproductor
     * es la que reb si hi ha hagut cualsevol error a l'hora de desar l'arxiu
     */
    public void desarDadesReproductor() {
        try {
            _ctrl.desarDades();
            System.out.println("Fitxer desat correctament!");
        } catch (Exception ex) {
            System.out.println("Ha hagut un error a l'hora de guardar l'arxiu");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Funcio que truca al controlador i carrega les dades del reproductor emmagatzemades
     * previament en un arxiu
     */
    public void carregarDadesReproductor() {
        try {
            _ctrl.recuperarDades();
            System.out.println("Dades carregades!");
        } catch (Exception ex) {
            System.out.println("Ha hagut un error a l'hora de recuperar l'arxiu");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Funcio que demana un fitxer de la biblioteca i el reprodueix
     * @param sc
     */
    public void reproduirFitxerAudio(Scanner sc) {
        if (_ctrl.bibliotecaHasFiles()) {
            int n;
            String frase;
            frase = "Indica el num del fitxer que vols escoltar:\n ";
            frase += _ctrl.getStringBiblioteca() + "\n";
            frase += "Escoltar fitxer num: \n";
            n = promptInt(frase, sc);
           
            
            // Si hi ha algun fitxer en reproduccio, preguntarem a l'usuari si vol afegir el nou element al final de la cua de reproduccio
            // sino, per defecte deixarem la opcio d'afegir com a false
            boolean opcioAfegir = false;
            if (_ctrl.reproductorActiu()) {
                opcioAfegir = promptBoolean("Vols continuar escoltant els elements actuals? Els nous s'afegiran al final de la cua de reproduccio!", sc);
            }
            if (opcioAfegir) {
                System.out.println(_ctrl.afegirNouFitxerCuaReproduccio(n));

            } else {
                try {
                    System.out.println(_ctrl.reproduirUnFitxer(n));
                } catch (Exception e) {
                    System.out.println("No s'ha pogut iniciar la reproduccio: " + e.toString());
                }
            }


        } else {
            System.out.println("Actualment no tens dades a la biblioteca");
        }

    }

    /**
     * Modifica l'estat play /pause i mostra la resposta per pantalla
     */
    private void premerPlayPause() {
        System.out.println(_ctrl.pushPlayPause());
    }

    /**
     * Activa / desactiva la reproduccio aleatoria i mostra la resposta per pantalla
     */
    private void pushReproduccioAleatoria() {
        if (_ctrl.premerReproduccioAleatoria()) {
            System.out.println("Reproduccio aleatoria activada");
        } else {
            System.out.println("Reproduccio aleatoria desactivada");
        }
    }

    /**
     * Activa / desactiva la reproduccio ciclica i mostra la resposta per pantalla
     */
    private void pushReproduccioCiclica() {
        if (_ctrl.premerReproduccioCiclica()) {
            System.out.println("Reproduccio ciclica activada");
        } else {
            System.out.println("Reproduccio ciclica desactivada");
        }
    }

    /**
     * Funcio per reproduir la biblioteca de musica
     */
    @SuppressWarnings("empty-statement")
    private void reproduirBiblioteca() {
        if (_ctrl.bibliotecaHasFiles()) {
            // Aqui no preguntem si vol afegir els elements al final de la cua de reproduccio, entenc
            // que com es la biblioteca, hi i son tots els fitxers, no te sentit afegirlos, ja que hi son tots
            try {
                System.out.println(_ctrl.reproduirLaBiblioteca());
                ;
            } catch (Exception e) {
                System.out.println("No s'ha pogut iniciar la reproduccio: " + e.getMessage());
            }
        } else {
            System.out.println("Actualment no tens dades a la biblioteca");
        }
    }

    /**
     * Funcio que demana una llista de reproduccio i la reprodueix
     * @param sc
     */
    private void reproduirLlistaReproduccio(Scanner sc) {
        if (comprobarExistenciaLlistes()) {
            int lR;
            String frase;
            frase = "Indica el num llista de reproduccio de la cual vols eliminar un fitxer: \n";
            frase += _ctrl.getStringLlistesActuals() + "\n";
            frase += "Reproduir llista: ";
            lR = promptInt(frase, sc);
            // Si hi ha algun fitxer en reproduccio, preguntarem a l'usuari si vol afegir la nova llista al final de la cua de reproduccio
            // sino, per defecte deixarem la opcio d'afegir com a false
            boolean opcioAfegir = false;
            if (_ctrl.reproductorActiu()) {
                opcioAfegir = promptBoolean("Vols continuar escoltant els elements actuals? Els nous s'afegiran al final de la cua de reproduccio!", sc);
            }
            if (opcioAfegir) {
            System.out.println(_ctrl.afegirNovaLlistaCuaReproduccio(lR));

            } else {

                try {
                    System.out.println(_ctrl.reproduirUnaLlista(lR));
                } catch (Exception e) {
                    System.out.println("No s'ha pogut iniciar la reproduccio: " + e.getMessage());

                }
            }
        }

    }

    /**
     * Funcio per reproduir el fitxer anterior
     */
    private void reproduirFitxerAnterior() {
        System.out.println(_ctrl.reproduirAnterior());
    }

    /**
     * Funcio per reproduir el fitxer seguent
     */
    private void reproduirFitxerSeguent() {
        System.out.println(_ctrl.reproduirSeguent());
    }

    /**
     * Funcio que finalitza la reproduccio
     */
    private void premerStop() {
        System.out.println(_ctrl.pararReproduccio());
    }
}
