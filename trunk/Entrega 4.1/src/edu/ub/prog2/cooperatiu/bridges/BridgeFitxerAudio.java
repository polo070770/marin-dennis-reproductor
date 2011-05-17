/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.cooperatiu.bridges;

import edu.ub.prog2.QuitaquisTamayDennis.model.FitxerAudio;
import edu.ub.prog2.cooperatiu.interficies.InterfaceFitxerAudio;

/**
 *
 * @author marin
 */
public class BridgeFitxerAudio implements InterfaceFitxerAudio{

    @Override
    public FitxerAudio nouFitxerAudio(String nom, String autor, String disc, int anyEdicio, int numSong, String discografica, String genre, String duracio, String path) {
        // Aqui hay que separar el nombre del archivo, la extension y la ruta, todo esto viene en la variable path
        // Yo no fuy capaz de hacerlo, y me fue mas facil hacer otro metodo constructor en la clase audio
        // pero bueno, ya veremos que hacemos
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
