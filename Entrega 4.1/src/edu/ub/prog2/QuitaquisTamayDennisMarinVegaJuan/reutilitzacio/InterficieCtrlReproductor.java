package edu.ub.prog2.QuitaquisTamayDennisMarinVegaJuan.reutilitzacio;

import edu.ub.prog2.QuitaquisTamayDennis.model.FitxerAudio;
import edu.ub.prog2.QuitaquisTamayDennis.model.LlistaReproduccio;
import edu.ub.prog2.utils.FitxerAudioErrorException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.DefaultListModel;

/**
 * Aquesta classe és on declarem tots els métodes abstractes que s'utilitzaran al
 * reproductor 5, de la classe BridgeCtrlReproductor
 * @author maikel
 */

//Interficie conte tots els metodes que implementara qualsevol classe
//Conte els metodes que utilitzem a la vista --> edu.ub.prog2.MarinVegaJuan.vista
//Tenim:
//La classe reproductor 5
//

public interface InterficieCtrlReproductor {
    
    ///////////////////////////////////////////
    //Métodes referenciats a DadesReproductor//
    ///////////////////////////////////////////
    public abstract void afegirLlistaReproduccio(LlistaReproduccio lr);

    public abstract void eliminarLListaReproduccio(int num); //No esta implementado en la vista

    public abstract void afegirFitxerBiblioteca(FitxerAudio f);

    public abstract void eliminarFitxerBiblioteca(int[]llistat);

    public abstract DefaultListModel getNomLlistes();

    public abstract DefaultListModel getDescripcioArxius(int num);

    public abstract DefaultListModel getArxiusBiblioteca();

    public abstract DefaultListModel getArxiusCuaReproduccio();

    public abstract void recuperarDades(File fitxer) throws FileNotFoundException, IOException, ClassNotFoundException;

    public abstract void desarDades(File fitxer) throws FileNotFoundException, IOException;

    ///////////////////////////////////////////
    //Metodes referenciats a ReproductorAudio//
    ///////////////////////////////////////////
    public abstract void pararReproduccio();

    public abstract void pushPlayPause();

    public abstract void premerReproduccioCiclica();

    public abstract void premerReproduccioAleatoria();

    public abstract void reproduirUnaLlista(int num) throws FitxerAudioErrorException;

    public abstract void reproduirArxiuDunaLlista(int pos, int num) throws FitxerAudioErrorException;

    public abstract void afegirNouFitxerDeLlistaCuaReproduccio(int pos, int num);

    public abstract void afegirNovaLlistaCuaReproduccio(int pos);

    public abstract void reproduirUnFitxer(int num)throws FitxerAudioErrorException;

    public abstract void afegirNouFitxerCuaReproduccio(int pos);

    public abstract void reproduirLaBiblioteca()throws FitxerAudioErrorException;

    public abstract boolean isRandom();

    public abstract boolean isLoop();

    public abstract boolean reproductorActiu();

    public abstract void reproduirAnterior();

    public abstract void reproduirSeguent();


}
