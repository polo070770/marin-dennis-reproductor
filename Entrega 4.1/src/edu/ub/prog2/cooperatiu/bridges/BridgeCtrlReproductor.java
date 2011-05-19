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

    //Declaraciones
    CtrlReproductor ctrl;
    DadesReproductor dades;

    public BridgeCtrlReproductor() {

        ctrl = new CtrlReproductor();

    }

    //Mi controlador para crear una lista de reproduccion tiene un entrada de parametro
    //que es el nombre de la lista.
    //O modificas tu vista para que solo pida el nombre de la lista y seguidamente llamar a este metodo.
    //o dejamos tal cual
    /**
     * 
     * @param lr
     */
    @Override
    public void afegirLlistaReproduccio(String nom) {

        ctrl.crearLlistaRepro(nom);

    }


    @Override
    public void eliminarLListaReproduccio(int num) {
        ctrl.eliminarLlistaDeConjLlistesRepro(num);
    }

    @Override
    public void eliminarFitxerBiblioteca(int[] llistat) {

        for (int i = 0; i < llistat.length; i++) {

            ctrl.eliminarFitxerMiBiblioteca(i);

        }

    }

    @Override
    public void afegirFitxerBiblioteca(FitxerAudio f) {

        ctrl.afegirFitxerMiBiblioteca(f);

    }

    @Override
    public DefaultListModel getNomLlistes() {

        DefaultListModel list = new DefaultListModel();
        for (LlistaReproduccio llista : ctrl.obteConjLlistes()) {

            list.addElement(llista.getNomLlista());

        }

        return list;
    }

    @Override
    public DefaultListModel getDescripcioArxius(int num) {

        DefaultListModel list = new DefaultListModel();
        for (FitxerAudio fa : ctrl.obteConjLlistes().get(num).getLlistaAudio()) {

            list.addElement(fa.getNomExtensio());

        }
        return list;

    }

    @Override
    public DefaultListModel getArxiusBiblioteca() {
        DefaultListModel list = new DefaultListModel();
        for (FitxerAudio fa : ctrl.obteMiBiblioteca()) {

            list.addElement(fa.getNomExtensio());

        }
        return list;
    }

    @Override
    public DefaultListModel getArxiusCuaReproduccio() {
        DefaultListModel list = new DefaultListModel();
        for (FitxerAudio fa : ctrl.getCuaReproduccio().getLlistaAudio()) {

            list.addElement(fa.getNomExtensio());

        }
        return list;
    }

    @Override
    public void recuperarDades(File fitxer) throws FileNotFoundException, IOException, ClassNotFoundException {

        DadesReproductor dadesRecuperades;

        FileInputStream f = new FileInputStream(fitxer);
        ObjectInputStream carregar = new ObjectInputStream(f);
        dadesRecuperades = (DadesReproductor) carregar.readObject();

        f.close();


        dades = dadesRecuperades;


    }

    @Override
    public void desarDades(File fitxer) throws FileNotFoundException, IOException {

        File dadesaRecuperar = new File(fitxer.toString() + ".data");

        FileOutputStream f = new FileOutputStream(dadesaRecuperar);

        ObjectOutputStream escriure = new ObjectOutputStream(f);

        escriure.writeObject(dades);

        f.close();
    }

    @Override
    public void pararReproduccio() {
        ctrl.parar();
    }

    @Override
    public void pushPlayPause() {

        ctrl.playPause();
    }

    @Override
    public void premerReproduccioCiclica() {
        ctrl.reproCiclic();
    }

    @Override
    public void premerReproduccioAleatoria() {
        ctrl.reproAleatori();
    }

    @Override
    public void reproduirUnaLlista(int num) throws FitxerAudioErrorException {
        ctrl.reproduir(ctrl.obteArrayListRepro(num));
    }

    @Override
    public void reproduirArxiuDunaLlista(int pos, int num) throws FitxerAudioErrorException {
        ctrl.reproduir(ctrl.obteArrayListRepro(pos).get(num));
    }

    @Override
    public void afegirNouFitxerDeLlistaCuaReproduccio(int pos, int num) {
        ctrl.getCuaReproduccio().afegirAudio(ctrl.obteArrayListRepro(pos).get(num));
    }

    @Override
    public void afegirNovaLlistaCuaReproduccio(int pos) {
        for (FitxerAudio f : ctrl.obteArrayListRepro(pos)) {

            ctrl.getCuaReproduccio().afegirAudio(f);

        }
    }

    @Override
    public void reproduirUnFitxer(int num) throws FitxerAudioErrorException {

        ctrl.reproduir(ctrl.obteMiBiblioteca().get(num));

    }

    @Override
    public void afegirNouFitxerCuaReproduccio(int pos) {
        ctrl.getCuaReproduccio().afegirAudio(ctrl.obteMiBiblioteca().get(pos));
    }

    @Override
    public void reproduirLaBiblioteca() throws FitxerAudioErrorException {
        ctrl.reproduir(ctrl.obteMiBiblioteca());
    }

    @Override
    public boolean reproductorActiu() {

        return ctrl.isPlay();

    }

    @Override
    public void reproduirAnterior() {

        ctrl.anterior();

    }

    @Override
    public void reproduirSeguent() {

        ctrl.seguent();

    }

    @Override
    public boolean isRandom() {

        return ctrl.isRandom();

    }

    @Override
    public boolean isLoop() {

        return ctrl.isCyclic();

    }

    @Override
    public void desarFitxerBiblio(FitxerAudio fa) {

        ctrl.afegirFitxerMiBiblioteca(fa);

    }
}
