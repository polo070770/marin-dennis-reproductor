package edu.ub.prog2.QuitaquisTamayDennis.model;

import java.io.Serializable;
import java.util.Date;
/**
 * Aquesta classe defineix els atributs generals de un fitxer.
 * @author Maikel
 */

public class Fitxer implements Serializable {
    
    //Atributs
    private String nomFitxer;
    private String extensioFitxer;
    private String rutaFitxer;
    private Date dataInsercio;
    
    //Getters
    /**
     * Data d'inserció del fitxer
     * @return Data d'inserció
     */
    public String getDataInsercio() {
        return dataInsercio.toString();
    }

    /**
     * Extensió del fitxer
     * @return Extensió
     */
    public String getExtensioFitxer() {
        return extensioFitxer;
    }

    /**
     * Ruta del fitxer
     * @return Ruta
     */
    public String getRutaFitxer() {
        return rutaFitxer;
    }

    /**
     * Nom del fitxer
     * @return Nom
     */
    public String getNomFitxer() {
        return nomFitxer;
    }

    //Constructor
    /**
     * Es crea un fitxer.
     * Amb les seguents dades:
     * @param localitzacioFitxer - ubicació del Fitxer
     * @param nomFitxer - nom del Fitxer
     * @param extensioFitxer - extensió del Fitxer     
     */
    public Fitxer(String ruta, String nom, String extensio) {

        this.rutaFitxer = ruta;
        this.nomFitxer = nom;
        this.extensioFitxer = extensio;
        this.dataInsercio = new Date();

    }
        
}
