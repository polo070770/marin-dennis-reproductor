package edu.ub.prog2.QuitaquisTamayDennis.controlador;

import edu.ub.prog2.QuitaquisTamayDennis.model.DadesReproductor;
import edu.ub.prog2.QuitaquisTamayDennis.model.Fitxer;
import edu.ub.prog2.QuitaquisTamayDennis.model.FitxerAudio;
import edu.ub.prog2.QuitaquisTamayDennis.model.LlistaReproduccio;
import edu.ub.prog2.utils.FitxerAudioErrorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Aquesta classe fa de pas mig entre DadesReproductor que gestion el paquet model
 * i el ReproductorAudio del paquet controlador.
 * @author Maikel
 */
public class CtrlReproductor {

    //Atributs
    private DadesReproductor dadesReproductor;
    private ReproductorAudio mi_reproductor;

    //Constructor
    /**
     * Inicialitzem la classe DadesReproductor i ReproductorAudio
     */
    public CtrlReproductor() {

        dadesReproductor = new DadesReproductor();
        mi_reproductor = new ReproductorAudio();

    }

    //Mètodes
    //-------------------------------------------------------------------------//
    //--------------------------Gestio-Biblioteca------------------------------//
    //-------------------------------------------------------------------------//
    /**
     * Afegeix un fitxer a la biblioteca
     * @param fa - fitxer audio
     */
    public void afegirFitxerMiBiblioteca(FitxerAudio fitxerAudio) {

        dadesReproductor.afegirAudioBiblio(fitxerAudio);

    }

    /**
     * Métode que tracta la ruta que entra l'usuari en AfegirFitxerAudio
     * @param RutaUsuari - Ruta entra pel usuari
     * @return - retorna un objecte tipus Fitxer
     */
    public Fitxer tractarRuta(String RutaUsuari) {

        String nom = "";
        String extensio = "";
        String ubicacioTotal = "";

        //De la direccioFitxer que ens entra l'usuari
        //separem la rutaTotal de l'extensió

        int m = 0;
        while (RutaUsuari.charAt(m) != '.') {

            ubicacioTotal += RutaUsuari.charAt(m);
            m++;

        }

        //Obtenim l'extensió
        int n = RutaUsuari.length() - 1;
        while (RutaUsuari.charAt(n) != '.') {

            extensio = RutaUsuari.charAt(n) + extensio;
            n--;

        }

        //De la ubicació total obtenim el nom
        nom = (new File(ubicacioTotal)).getName();
        ubicacioTotal = (new File(ubicacioTotal)).getPath();

        Fitxer fitxer;
        fitxer = new Fitxer(ubicacioTotal, nom, extensio);

        return fitxer;

    }

    /**
     * Elimina un fitxer de la biblioteca
     * @param posicio - posicio del fitxer
     */
    public void eliminarFitxerMiBiblioteca(int posicio) {

        dadesReproductor.eliminarAudioBiblio(posicio);

    }

    /**
     * Obte un string amb la informacio (Nom, Ruta, Autor, Duracio, DataInsercio)
     * del audio obtingut de la biblioteca.
     * @param pos
     * @return Informacio del Audio
     */
    public String mostraAudioMiBiblio(FitxerAudio f) {
        String r;
        r = formatar(f.getNomAudio(), 30)
                + " " + formatar(f.getAutorAudio(), 30)
                + " " + formatar(f.getDuracioAudio(), 30);

        return r + " " + formatar(f.getDiscAudio(), 30)
                + "   " + f.getDataInsercio();

    }

    /**
     * Metode el qual obtenim un objecte del tipus ArrayList<FitxerAudio> de la biblioteca
     * @return Una ArrayList() del tipus FitxerAudio
     */
    public ArrayList<FitxerAudio> obteMiBiblioteca() {

        return dadesReproductor.getMiBbibliteca();

    }

    /**
     * Metode que estableix una separació optima entre les dades del fitxer al 
     * cridar-ho per mostrar la biblio.
     * @param text
     * @param longitud
     * @return 
     */
    public static String formatar(String text, int longitud) {

        text += "                                                                 ";
        return text.substring(0, longitud);

    }

    //-------------------------------------------------------------------------//
    //--------------------------Gestio-Llistes-De-Reproduccio------------------//
    //-------------------------------------------------------------------------//
    /**
     * Fem una llista de reproducció nova i la guarda en el conjunt de llistes.
     * @param nomLlista - nom de la llista de reproducció nova
     */
    public void crearLlistaRepro(String nomLlista) {

        dadesReproductor.crearLlistaRepro(nomLlista);

    }

    /**
     * Eliminar una llista de reproduccio del conjunt de llistes
     * @param posicio - posicio de la llista de reproducció a eliminar
     */
    public void eliminarLlistaDeConjLlistesRepro(int posicio) {

        dadesReproductor.eliminarLlistaDeConjLlistes(posicio);

    }

    /**
     * Obtenim un objecte del tipus llista de reproduccio del conjunt de llistes
     * @param posicio - posicio de la llista de reproduccio
     * @return - LlistaReproduccio
     */
    public LlistaReproduccio obteLlistaRepro(int posicio) {

        return dadesReproductor.getConjLlistes().get(posicio);

    }

    /**
     * Obtenim un objecte del tipus ArrayList<FitxerAudio> del conjunt de llistes
     * @param posicio - posició de la llista de reproducció
     * @return - Una ArrayList() del tipus FitxerAudio
     */
    public ArrayList<FitxerAudio> obteArrayListRepro(int posicio) {

        return dadesReproductor.getConjLlistes().get(posicio).getLlistaAudio();
    }

    /**
     * Obtenim un objecte del tipus ArrayList<LlistaReproduccio> del conjunt de llistes
     * @return - Una ArrayList() del tipus LlistaReproduccio
     */
    public ArrayList<LlistaReproduccio> obteConjLlistes() {


        return dadesReproductor.getConjLlistes();

    }

    //-------------------------------------------------------------------------//
    //--------------------------Opcions-Control-de-reproducció-----------------//
    //-------------------------------------------------------------------------//
    public void reproduir(String ruta){
        
        mi_reproductor.Play(ruta);
        
    }
    
    public void reproduir(FitxerAudio audio) throws FitxerAudioErrorException {

        mi_reproductor = new ReproductorAudio();

        mi_reproductor.obteCuaReproduccio().afegirAudio(audio);
        System.out.println(audio.toString());
        mi_reproductor.Play();

    }

    public void reproduir(ArrayList<FitxerAudio> al) throws FitxerAudioErrorException {

        mi_reproductor = new ReproductorAudio();

        parar();

        for (FitxerAudio audio : al) {

            mi_reproductor.obteCuaReproduccio().afegirAudio(audio);
        }
        mi_reproductor.Play();


    }

    public String reproAleatori() {

        String estat = "";

        if (!mi_reproductor.isRANDOM()) {

            mi_reproductor.randomCuaReproduccio();
            estat = "Reproduccio aleatoria activada";

        } else {

            mi_reproductor.randomCuaReproduccio();
            estat = "Reproduccio aleatori desactivada";

        }

        return estat;


    }

    public String reproCiclic() {

        String estat = "";

        if (!mi_reproductor.isCYCLIC()) {

            mi_reproductor.setCYCLIC(true);
            estat = "Reproducció cíclica activada!";

        } else {

            mi_reproductor.setCYCLIC(false);
            estat = "Reproduccio cíclica desactivada";

        }

        return estat;

    }

    public LlistaReproduccio getCuaReproduccio() {
        
        return mi_reproductor.getCua(); 
        
    }

    public FitxerAudio getFitxerAudioSonant(){

        return obteMiBiblioteca().get(mi_reproductor.getPista());
        
    }
    //-------------------------------------------------------------------------//
    //--------------------------Menu-Reproductor-------------------------------//
    //-------------------------------------------------------------------------//
    public String playPause() {

        String estat = "";

        if (mi_reproductor.isPLAY()) {

            mi_reproductor.pause();
            estat = "PAUSAT";

        } else if (mi_reproductor.isSTOP()) {

            mi_reproductor.setPista(0);
            mi_reproductor.Play();
            estat = "Continuant des del principi...";

        } else {

            mi_reproductor.play();
            estat = "Continuant reproducció...";

        }

        return estat;

    }

    public void parar() {

        mi_reproductor.stop();

    }

    public void seguent() {

        mi_reproductor.reproSeguent();

    }

    public void anterior() {

        mi_reproductor.reproAnterior();

    }

    public boolean isPlay() {

        return mi_reproductor.isPLAY();

    }

    public boolean isRandom(){

        return mi_reproductor.isRANDOM();

    }

    public boolean isCyclic(){

        return mi_reproductor.isCYCLIC();

    }
    //-------------------------------------------------------------------------//
    //--------------------------Guardar-dades----------------------------------//
    //-------------------------------------------------------------------------//

    /**
     * Guarda les dades que hi ha actualment a dadesReproductor en un fitxer txt amb
     * el nom de "dadesReproductor.txt".
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void guardarDadesReproductor() throws FileNotFoundException, IOException {

        File dades = new File("dadesReproductor.txt");

        FileOutputStream fitxer = new FileOutputStream(dades);

        ObjectOutputStream escriure = new ObjectOutputStream(fitxer);

        escriure.writeObject(dadesReproductor);

        fitxer.close();

    }

    //-------------------------------------------------------------------------//
    //--------------------------Carregar-dades---------------------------------//
    //-------------------------------------------------------------------------// 
    /**
     * Recupera les dades guardades amb el nom de "dadesReproductor.txt", en la carpeta del projecte
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void carregarDadesReproductor() throws FileNotFoundException, IOException, ClassNotFoundException {

        DadesReproductor dadesRecuperades;
        File dades = new File("dadesReproductor.txt");

        FileInputStream fitxer = new FileInputStream(dades);
        ObjectInputStream carregar = new ObjectInputStream(fitxer);
        dadesRecuperades = (DadesReproductor) carregar.readObject();

        fitxer.close();
        dadesReproductor = dadesRecuperades;

    }
    public DadesReproductor getDades(){
        return this.dadesReproductor;
    }
    public boolean setDades(DadesReproductor dades){
        this.dadesReproductor = dades;
        return true;
    }
}
