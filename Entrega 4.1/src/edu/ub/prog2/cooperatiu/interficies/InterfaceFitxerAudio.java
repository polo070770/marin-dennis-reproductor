/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.cooperatiu.interficies;

import edu.ub.prog2.QuitaquisTamayDennis.model.FitxerAudio;

/**
 *
 * @author marin
 */
public interface InterfaceFitxerAudio {
    /**
     * 
     * @param nom  String nombre del arxivi de audio
     * @param autor String nombre del autor
     * @param disc String nombre del disco
     * @param anyEdicio Integer Año de edicion
     * @param numSong   Integer numero de la cancion en el disco
     * @param discografica  String Nombre de la discografica
     * @param genre String Genero musical
     * @param duracio  String que simboliza la duracion en formato hh:mm:ss
     * @param path String Ruta completa del archivo de sonido incluyendo nombre y extension
     */
    abstract FitxerAudio nouFitxerAudio(String nom, String autor, String disc, int anyEdicio, int numSong, String discografica, String genre, String duracio, String path);
    
}
