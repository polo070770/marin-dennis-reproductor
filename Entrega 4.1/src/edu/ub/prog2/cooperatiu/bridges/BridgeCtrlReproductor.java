package edu.ub.prog2.cooperatiu.bridges;

import edu.ub.prog2.QuitaquisTamayDennis.model.LlistaReproduccio;
import edu.ub.prog2.QuitaquisTamayDennis.controlador.CtrlReproductor;
import edu.ub.prog2.QuitaquisTamayDennis.model.DadesReproductor;
import edu.ub.prog2.QuitaquisTamayDennis.model.FitxerAudio;
import edu.ub.prog2.cooperatiu.interficies.InterficieCtrlReproductor;
import edu.ub.prog2.utils.FitxerAudioErrorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 * Aquesta es la classe que implementa la classe InterficieCtrlReproductor i que
 * defineix tots els metodes abstractes declarats a la interficie, amb les crides
 * fetes al CtrlReproductor del paquet edu.ub.prog2.QuitaquisTamayDennis.controlador
 *
 * @author maikel
 */
public class BridgeCtrlReproductor implements InterficieCtrlReproductor {

    //Declaracions
    CtrlReproductor ctrl;

    /**
     * Contructor de la classe que inicialitza el CtrlReproductor de
     * edu.ub.prog2.QuitaquisTamayDennis.controlador.CtrlControlador
     */
    public BridgeCtrlReproductor() {

        ctrl = new CtrlReproductor();

    }

    //-------------------------------------------------------------------------//
    //---------Gestió-Biblioteca-i-Llistes-De-Reproduccio----------------------//
    //-------------------------------------------------------------------------//
    /**
     * Fem una llista de reproducció nova i la guarda en el conjunt de llistes.
     * @param nom - nom de la llista de reproducció nova
     */
    @Override
    public void afegirLlistaReproduccio(String nom) {

        ctrl.crearLlistaRepro(nom);

    }

    /**
     * Elimina una llista de reproducció del conjunt de llistes
     * @param num - posicio de la llista de reproducció a eliminar
     */
    @Override
    public void eliminarLListaReproduccio(int num) {
        ctrl.eliminarLlistaDeConjLlistesRepro(num);
    }

    /**
     * Elimina els fitxers seleccionats. de la biblioteca
     * @param llistat - llista de les posicions dels fitxers a eliminar
     */
    @Override
    public void eliminarFitxerBiblioteca(int[] llistat) {

        for (int i = 0; i < llistat.length; i++) {

            ctrl.eliminarFitxerMiBiblioteca(i);

        }

    }

    /**
     * Afegeix un fitxer a la biblioteca
     * @param f - fitxerAudio
     */
    @Override
    public void afegirFitxerBiblioteca(FitxerAudio f) {

        ctrl.afegirFitxerMiBiblioteca(f);

    }

    /**
     * Afegeix un fitxer a la biblioteca
     * @param fa
     */
    @Override
    public void desarFitxerBiblio(FitxerAudio fa) {

        ctrl.afegirFitxerMiBiblioteca(fa);

    }

    /**
     * Del conjunt de les llistes de reprodució obtenim de cada llista el seu nom
     * @return - DefaultListModel amb l'informació de les llistes
     */
    @Override
    public DefaultListModel getNomLlistes() {

        DefaultListModel list = new DefaultListModel();
        for (LlistaReproduccio llista : ctrl.obteConjLlistes()) {

            list.addElement(llista.getNomLlista());

        }

        return list;
    }

    /**
     * Del conjunt de FitxersAudio que hi ha en una llista de reproducció obtenim
     * informació
     * @param num - posició de la llista
     * @return DefaultListModel amb l'informació del llistat de fitxers
     */
    @Override
    public DefaultListModel getDescripcioArxius(int num) {

        DefaultListModel list = new DefaultListModel();
        for (FitxerAudio fa : ctrl.obteConjLlistes().get(num).getLlistaAudio()) {

            list.addElement(fa.getNomExtensio());

        }
        return list;

    }

    /**
     * Del conjunt de FitxersAudio que hi ha a la biblioteca obtenim informació
     * @return - DefaultListModel amb l'informació del llista de fitxers
     */
    @Override
    public DefaultListModel getArxiusBiblioteca() {
        DefaultListModel list = new DefaultListModel();
        for (FitxerAudio fa : ctrl.obteMiBiblioteca()) {

            list.addElement(fa.getNomExtensio());

        }
        return list;
    }

    /**
     * Del conjunt de FitxersAudio que hi ha a la cua de reproducció obtenim
     * informació
     * @return DefaultListModel amb l'informació del llista de fitxers
     */
    @Override
    public DefaultListModel getArxiusCuaReproduccio() {
        DefaultListModel list = new DefaultListModel();
        for (FitxerAudio fa : ctrl.getCuaReproduccio().getLlistaAudio()) {

            list.addElement(fa.getNomExtensio());

        }
        return list;
    }

    /**
     * Recupera les dades guardades
     * @param fitxer
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void recuperarDades(File fitxer) throws FileNotFoundException, IOException, ClassNotFoundException {

        DadesReproductor dadesRecuperades;

        FileInputStream f = new FileInputStream(fitxer);
        ObjectInputStream carregar = new ObjectInputStream(f);
        dadesRecuperades = (DadesReproductor) carregar.readObject();

        f.close();


        ctrl.setDades(dadesRecuperades);


    }

    /**
     * Guarda les dades que hi ha actualment a dadesReproductor
     * @param fitxer
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public void desarDades(File fitxer) throws FileNotFoundException, IOException {

        File dadesaRecuperar = new File(fitxer.toString() + ".data");

        FileOutputStream f = new FileOutputStream(dadesaRecuperar);

        ObjectOutputStream escriure = new ObjectOutputStream(f);

        escriure.writeObject(ctrl.getDades());

        f.close();
    }

    //-------------------------------------------------------------------------//
    //--------------------------Opcions-Control-de-reproducció-----------------//
    //-------------------------------------------------------------------------//
    /**
     * Atura la reproducció
     */
    @Override
    public void pararReproduccio() {
        ctrl.parar();
    }

    /**
     * Alterna entre play o pause la reproducció
     */
    @Override
    public void pushPlayPause() {

        ctrl.playPause();
    }

    /**
     * Activa la reproducció cíclica
     */
    @Override
    public void premerReproduccioCiclica() {
        System.out.println(ctrl.reproCiclic());
    }

    /**
     * Activa la reproducció aleatoria
     */
    @Override
    public void premerReproduccioAleatoria() {
        System.out.println(ctrl.reproAleatori());
    }

    /**
     * Reprodueix una llista seleccionada en una posició concreta
     * @param num - posicio de la llista
     * @return
     */
    @Override
    public boolean reproduirUnaLlista(int num) {
        try {
            ctrl.reproduir(ctrl.obteArrayListRepro(num));
        } catch (FitxerAudioErrorException ex) {
            Logger.getLogger(BridgeCtrlReproductor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Reprodueix un arxiu d'una llista concreta
     * @param pos - posicio de del arxiu a la llista
     * @param num - posicio de la llista al conjunt de llistes
     * @return
     */
    @Override
    public boolean reproduirArxiuDunaLlista(int pos, int num) {
        try {
            ctrl.reproduir(ctrl.obteArrayListRepro(pos).get(num));
        } catch (FitxerAudioErrorException ex) {
            Logger.getLogger(BridgeCtrlReproductor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Afegeix un arxiu d'una llista a la cua de reproducció
     * @param pos - posició de la llista
     * @param num - posició del arxiu a la llista
     */
    @Override
    public void afegirNouFitxerDeLlistaCuaReproduccio(int pos, int num) {
        ctrl.getCuaReproduccio().afegirAudio(ctrl.obteArrayListRepro(pos).get(num));
    }

    /**
     * Afegeix tots els fitxers d'una llista de reproducció a la cua de reproducció
     * @param pos
     */
    @Override
    public void afegirNovaLlistaCuaReproduccio(int pos) {
        for (FitxerAudio f : ctrl.obteArrayListRepro(pos)) {

            ctrl.getCuaReproduccio().afegirAudio(f);

        }
    }

    /**
     * Reprodueix un fitxer de la biblioteca
     * @param num - posició del fitxer a la biblioteca
     * @return
     */
    @Override
    public boolean reproduirUnFitxer(int num) {
        try {
            ctrl.reproduir(ctrl.obteMiBiblioteca().get(num));
        } catch (FitxerAudioErrorException ex) {
            Logger.getLogger(BridgeCtrlReproductor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     *Afegeix un fitxer de la biblioteca a la cua de reproducció
     * @param pos - posició del fitxer a la biblioteca
     */
    @Override
    public void afegirNouFitxerCuaReproduccio(int pos) {
        ctrl.getCuaReproduccio().afegirAudio(ctrl.obteMiBiblioteca().get(pos));
    }

    /**
     * Reprodueix tota la biblioteca
     * @return
     */
    @Override
    public boolean reproduirLaBiblioteca() {
        try {
            ctrl.reproduir(ctrl.obteMiBiblioteca());
        } catch (FitxerAudioErrorException ex) {
            Logger.getLogger(BridgeCtrlReproductor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Esta activada la reproducció o no
     * @return - estat del reproductor
     */
    @Override
    public boolean reproductorActiu() {

        return ctrl.isPlay();

    }

    /**
     * Reproduir el fitxer anterior
     */
    @Override
    public void reproduirAnterior() {

        ctrl.anterior();

    }

    /**
     * reproduir el fitxer seguent
     */
    @Override
    public void reproduirSeguent() {

        ctrl.seguent();

    }

    /**
     * Estat de la reproducció aleatoria
     * @return - l'estat de la reproducció aleatoria
     */
    @Override
    public boolean isRandom() {

        return ctrl.isRandom();

    }

    /**
     * Estat de la reproducció cíclica
     * @return - l'estat de la reproducció cíclica
     */
    @Override
    public boolean isLoop() {

        return ctrl.isCyclic();

    }
}
