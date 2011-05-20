package edu.ub.prog2.cooperatiu.interficies;

import edu.ub.prog2.QuitaquisTamayDennis.model.FitxerAudio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.DefaultListModel;

/**
 * Aquesta classe és on declarem tots els métodes abstractes que s'utilitzaran al
 * reproductor 5, de la classe BridgeCtrlReproductor
 * @author maikel
 */

public interface InterficieCtrlReproductor {
    
    ///////////////////////////////////////////
    //Métodes referenciats a DadesReproductor//
    ///////////////////////////////////////////

    public abstract void desarFitxerBiblio(FitxerAudio fa);

    public abstract void afegirLlistaReproduccio(String nom);

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

    public abstract boolean reproduirUnaLlista(int num);

    public abstract boolean reproduirArxiuDunaLlista(int pos, int num);

    public abstract void afegirNouFitxerDeLlistaCuaReproduccio(int pos, int num);

    public abstract void afegirNovaLlistaCuaReproduccio(int pos);

    public abstract boolean reproduirUnFitxer(int num);

    public abstract void afegirNouFitxerCuaReproduccio(int pos);

    public abstract boolean reproduirLaBiblioteca();

    public abstract boolean isRandom();

    public abstract boolean isLoop();

    public abstract boolean reproductorActiu();

    public abstract void reproduirAnterior();

    public abstract void reproduirSeguent();


}
