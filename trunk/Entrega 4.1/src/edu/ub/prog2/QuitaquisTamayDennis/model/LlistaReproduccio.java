package edu.ub.prog2.QuitaquisTamayDennis.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Aquesta classe genera una llista d'audio del tipus ArrayList.
 * Cada nova llista conté el nom propi, a més conté els métodes per eliminar,
 * afegir o obtindre un fitxer audio d'aquesta llista. També hi ha el métode
 * d'obtindre el tamany de la llista.
 * @author Maikel
 */
public class LlistaReproduccio implements Serializable {

    //Atributs
    private ArrayList<FitxerAudio> llistaAudio;
    private String nomLlista;

    //Getters
    
    public ArrayList<FitxerAudio> getLlistaAudio(){
        
        return llistaAudio;
        
    }
    
    /**
     * Obtenidre el nom de la llista
     * @return nomLlista
     */
    public String getNomLlista() {

        return nomLlista;

    }

    //Constructor
    /**
     * Contructor de la classe que crea una ArrayList i s'indica el nom de la llista
     * passat per parametre.
     * @param nomLlista - Nom de la llista
     */
    public LlistaReproduccio(String nomLlista) {

        llistaAudio = new ArrayList();
        this.nomLlista = nomLlista;

    }

    //Metodes
    /**
     * Afegir un archiu a la llista d'audio
     * @param fa - Fitxer Audio
     */
    public void afegirAudio(FitxerAudio fa) {

        llistaAudio.add(fa);

    }

    /**
     * Eliminar un fitxer d'audio de la llista d'audio
     * @param posicio - Posició del fitxer
     */
    public void eliminarAudioDeLlista(int posicio) {

        llistaAudio.remove(posicio);

    }

    /**
     * Obté un boolean indicant si esta buida o no la llista d'audio
     * @return - buida o no la llista
     */
    public boolean isEmpty() {

        return llistaAudio.isEmpty();

    }
    
    public void buidaLlista(){
        
        llistaAudio.removeAll(llistaAudio);
        
    }
    
    public void novaLlista(){
        
        llistaAudio = new ArrayList();
        
    }
}
