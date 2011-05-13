package edu.ub.prog2.QuitaquisTamayDennis.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Aquesta classe gestiona la biblioteca, i el conjunt de llistes.
 * Conté els mètodes de afegir, eliminar i obtindre FitxerAudio o LlistaReproduccio,
 * en la Biblioteca o en el conjunt de LListes, respectivament.
 * @author Maikel
 */
public class DadesReproductor implements Serializable {

    //Atributs
    private LlistaReproduccio miBiblioteca;
    private ArrayList<LlistaReproduccio> conjLlistes;

    //Constructor
    /**
     * Constructor que inicialitza la biblioteca, que serà una LlistaReproduccio.
     * A més s'inicialitza un ArrayList de tipus LlistaReproduccio, que serà 
     * el conjunt de Llistes.
     */
    public DadesReproductor() {

        miBiblioteca = new LlistaReproduccio("Mi Biblioteca");
        conjLlistes = new ArrayList();

    }

    //Metodes
    //-------------------------------------------------------------------------//
    //--------------------------Gestio-Biblioteca------------------------------//
    //-------------------------------------------------------------------------//
    /**
     * Afegeix un archiu a la biblioteca
     * @param fa - Fitxer audio
     */
    public void afegirAudioBiblio(FitxerAudio fitxerAudio) {

        miBiblioteca.afegirAudio(fitxerAudio);

    }

    /**
     * Elimina un fitxer d'audio de la biblioteca
     * @param pos - posició del fitxer a la biblioteca
     */
    public void eliminarAudioBiblio(int posicio) {

        miBiblioteca.eliminarAudioDeLlista(posicio);

    }

    /**
     * Metode pel qual obtenim la biblioteca
     * @return Una ArrayList() del tipus FitxerAudio
     */
    public ArrayList<FitxerAudio> getMiBbibliteca() {

        return miBiblioteca.getLlistaAudio();

    }

    //-------------------------------------------------------------------------//
    //--------------------------Gestio-Llistes-De-Reproduccio------------------//
    //-------------------------------------------------------------------------//
    /**
     * Fa una llista de reproducció nova i la guarda al conjunt de llistes
     * @param nomLlista - nom de la llista que tindrà la llista de reproducció
     */
    public void crearLlistaRepro(String nomLlista) {

        LlistaReproduccio llistaNova = new LlistaReproduccio(nomLlista);
        conjLlistes.add(llistaNova);

    }

    /**
     * Eliminar una llista de reproducció del conjunt de llistes
     * @param posicio - posicio de la llista a eliminar
     */
    public void eliminarLlistaDeConjLlistes(int posicio) {

        conjLlistes.remove(posicio);

    }

    /**
     * Obtenim el conjunt de llistes
     * @return - Una ArrayList() del tipus LlistaReproduccio
     */
    public ArrayList<LlistaReproduccio> getConjLlistes() {


        return conjLlistes;

    }

}
