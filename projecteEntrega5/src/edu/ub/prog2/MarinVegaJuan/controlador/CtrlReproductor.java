package edu.ub.prog2.MarinVegaJuan.controlador;

import edu.ub.prog2.MarinVegaJuan.model.DadesReproductor;
import edu.ub.prog2.MarinVegaJuan.model.FitxerAudio;
import edu.ub.prog2.MarinVegaJuan.model.LlistaReproduccio;
import edu.ub.prog2.MarinVegaJuan.model.Recursos;
import edu.ub.prog2.utils.FitxerAudioErrorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.DefaultListModel;

/**
 * Esta clase establece la comunicacion entre las datos del reproductor del paquete modelo
 * y la clase reproductor del paquete vista
 * @author marin
 */
public class CtrlReproductor {

    private DadesReproductor dades;
    private String baseDeDades;
    private ReproductorAudio _reproductorAudio;


    /**
     * Metodo constructor que instancia una clase del tipo DadesReproductor
     */
    public CtrlReproductor() {
        dades = new DadesReproductor();
        _reproductorAudio = new ReproductorAudio( dades.isRandom(), dades.isLoop());
        baseDeDades = "db";
    }

    /**
     * Devuelve el objeto dadesReproductor
     * @return
     */
    public DadesReproductor getDades() {
        return dades;
    }

    /**
     * Recibe un objeto del tipo fitxerAudio y llama al metodo de dades para que lo añada
     * a la biblioteca
     * @param f FitxerAudio
     */
    public void afegirFitxerBiblioteca(FitxerAudio f) {
        dades.afegirFitxerBiblioteca(f);
    }

    /**
     * Recibe un identificador numerico y llama al metodo de dades para que elimine el fitxerAudio
     * correspondiente a ese identificador
     * @param n int
     */
    public boolean eliminarFitxerBiblioteca(int n) {
        try {
            dades.eliminarFitxerBiblioteca(dades.getBiblioteca().getFitxer(n));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
     /**
     * Recibe un array con los indices de los elementos a eliminar
     * @param n int[]
     */
    public boolean eliminarFitxerBiblioteca(int[] llistat) {
        //Creamos un listado a partir de la biblioteca
        LlistaReproduccio tmp = new LlistaReproduccio(dades.getBiblioteca());
        //Ara recuperarem un a un els objectes per tal de eliminarlos, aixo ho fem ja que no podem
        //eliminar els objectes per la seva posicio a la llista de la biblioteca, ja que podria comportar problemes
        try{
            for(int num: llistat){
                 dades.eliminarFitxerBiblioteca(tmp.getFitxer(num));
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Devuelve un string con la informacion de la biblioteca, que lo recibe de dades
     * @return
     */
    public String getStringBiblioteca() {
        return dades.bibliotecaActual();
    }

    /**
     * Recibe un objeto de tipo llistaReproduccio i llama al metodo de dades especifico
     * para añadirlo
     * @param lR LlistaReproduccio
     */
    public void afegirLlistaReproduccio(LlistaReproduccio lR) {
        dades.afegirLlistaReproduccio(lR);
    }

    /**
     * Recibe un identificador numerico y llama al metodo correspondiente de la clase dades para
     * eliminar la llistaReproduccio asociada a ese identificador
     * @param i int
     */
    public void eliminarLlistaReproduccio(int i) {
        dades.eliminarLlistaReproduccio(dades.getLlista(i));
    }

    /**
     * Recibe un identificador numerico y llama al metodo de dades que devuelve una llistaReproduccio
     * en formato String para poder mostrarla
     * @param i int
     * @return
     */
    public String getStringLlistaReproduccio(int i) {
        return dades.getLlista(i).toString();
    }

    /**
     * Devuelve un String de las listas de reproduccion actuales
     * @return
     */
    public String getStringLlistesActuals() {
        return dades.llistesActuals();
    }

    /**
     * Recibe dos identificadores, uno de un fitxerAudio y el otro de una lista de reproduccion
     * luego llama al metodo de dades, con lo cual el fichero asociado a ese identificador se incorporara
     * en la lista de reproduccion asociada al identificador correspondiente
     * @param f int
     * @param lR int
     */
    public void afegirFitxerLlista(int f, int lR) {
        dades.afegirFitxerLlista(f, lR);
    //dades.getLlista(lR).afegirFitxer(dades.getBiblioteca().getFitxer(f));
    }

    /**
     * Recibe dos identificadores, uno de un fitxerAudio y el otro de una lista de reproduccion
     * luego llama al metodo de dades, con lo cual el fichero asociado a ese identificador se eliminara
     * de la lista de reproduccion asociada al identificador correspondiente
     * @param f int
     * @param lR int
     */
    public void eliminarFitxerLlista(int f, int lR) {
        dades.eliminarFitxerLlista(f, lR);
    //dades.getLlista(lR).eliminarFitxer(dades.getBiblioteca().getFitxer(f));
    }

    /**
     * Devuelve un boolean indicando si la biblioteca de dades contiene archivos
     * @return boolean
     */
    public boolean bibliotecaHasFiles() {
        return dades.getBiblioteca().haveFiles();
    }

    /**
     * devuelve un booleano indicando si la lista de reproduccion asociada al identificador
     * recibido contiene archivos
     * @param i int
     * @return boolean
     */
    public boolean llistaHasFiles(int i) {
        return dades.getLlista(i).haveFiles();
    }

    /**
     * devuelve un booleano indicando si existen listas de reproduccion
     * @return boolean
     */
    public boolean hasLists() {
        return dades.haveLists();

    }

    /**
     * Funcio que desa l'objecte en un fitxer al disc dur
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public void desarDades() throws FileNotFoundException, IOException {
        File fitxer;
        ObjectOutputStream oos;
        FileOutputStream fout;

        //Crea l'arxiu, el conducte de sortida i l'objectOutputStream
        fitxer = new File(baseDeDades);
        fout = new FileOutputStream(fitxer);
        oos = new ObjectOutputStream(fout);

        //Desa la llista i tanca l'arxiu
        oos.writeObject(dades);
        fout.close();
    }
        public void desarDades(File nouFitxer) throws FileNotFoundException, IOException {
        File fitxer;
        ObjectOutputStream oos;
        FileOutputStream fout;
        fitxer = new File(nouFitxer.toString()+".data");
        //Crea l'arxiu, el conducte de sortida i l'objectOutputStream
        fout = new FileOutputStream(fitxer);
        oos = new ObjectOutputStream(fout);

        //Desa la llista i tanca l'arxiu
        oos.writeObject(dades);
        fout.close();
    }


    /**
     * Funcio que s'encarrega de recuperar les dades emmagatzemades en un arxiu,
     * Recupera un objecte de tipus DadesReproductor
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public void recuperarDades() throws FileNotFoundException, IOException, ClassNotFoundException {
        File fitxerLlista;
        FileInputStream fin;
        ObjectInputStream ois;
        DadesReproductor dadesARecuperar;
        //El fileInputStream
        fin = new FileInputStream(baseDeDades);
        //i l'objectInputStream
        ois = new ObjectInputStream(fin);
        //Recuperem la llista , tanquem l'arxiu i retornem la llista
        dadesARecuperar = (DadesReproductor) ois.readObject();
        fin.close();
        dades = dadesARecuperar;


    }
        /**
     * Funcio que s'encarrega de recuperar les dades emmagatzemades en un arxiu,
     * Recupera un objecte de tipus DadesReproductor
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public void recuperarDades(File fitxer) throws FileNotFoundException, IOException, ClassNotFoundException {
        File fitxerLlista;
        FileInputStream fin;
        ObjectInputStream ois;
        DadesReproductor dadesARecuperar;
        //El fileInputStream
        fin = new FileInputStream(fitxer);
        //i l'objectInputStream
        ois = new ObjectInputStream(fin);
        //Recuperem la llista , tanquem l'arxiu i retornem la llista
        dadesARecuperar = (DadesReproductor) ois.readObject();
        fin.close();
        dades = dadesARecuperar;


    }

    public String reproduirLaBiblioteca() throws FitxerAudioErrorException {
        LlistaReproduccio nova = new LlistaReproduccio(dades.getBiblioteca());
        _reproductorAudio.introduirNovaLlista(nova, dades.isRandom(), dades.isLoop());
       return _reproductorAudio.comenzarReproduccio();
    }
    public String reproduirArxiuDunaLlista(int numFitxer, int numLlista) throws FitxerAudioErrorException {
        LlistaReproduccio nova = new LlistaReproduccio(dades.getLlista(numLlista).getFitxer(numFitxer));
        _reproductorAudio.introduirNovaLlista(nova, dades.isRandom(), dades.isLoop());
       return _reproductorAudio.comenzarReproduccio();
    }
    /**
     * Funcio que posibilita reproduir un fitxer d'audio de la biblioteca
     * previament seleccionat
     * @param numFitxer
     */
    public String reproduirUnFitxer(int numFitxer) throws FitxerAudioErrorException {
        
        LlistaReproduccio nova = new LlistaReproduccio(dades.getBiblioteca().getFitxer(numFitxer));
        _reproductorAudio.introduirNovaLlista(nova, dades.isRandom(), dades.isLoop());
        return _reproductorAudio.comenzarReproduccio();
    }

    /**
     * Metode que rep un numero que identifica a una llista de reproduccio
     * Recupera aquesta llista i genera una cola de reproduccio a partir de ella
     * indicant tambe si sera en mode aleatori i ciclic
     * @param lR int que identifica una llista de reproduccio
     */
    public String reproduirUnaLlista(int lR) throws FitxerAudioErrorException {
        LlistaReproduccio nova = new LlistaReproduccio(dades.getLlista(lR));
        _reproductorAudio.introduirNovaLlista(nova, dades.isRandom(), dades.isLoop());
        return _reproductorAudio.comenzarReproduccio();

    }
    /**
     * Metode que activa / desactiva la opcio reproduccioAleatoria tant a dades
     * com al reproductor i retorna l'estat
     * @return
     */
    public boolean premerReproduccioAleatoria() {
        _reproductorAudio.setRandom(dades.setRandom());
        return dades.isRandom();
    }
    /**
     * Metode que activa / desactiva la opcio de reproduccioCiclica tant a dades com al
     * reproductor i retorna l'estat
     * @return
     */
    public boolean premerReproduccioCiclica() {
        _reproductorAudio.setLoop(dades.setLoop());
        return dades.isLoop();
    }
    /**
     * Metode que crida a la funcio de reproduir el fitxer anterior del reproductor
     * i retorna una resposta en format String, que pot ser un missatge del fitxer que sona, que el reproductor
     * esta parat o que ha donat error
     * @return
     */
    public String reproduirAnterior() {
            if (!_reproductorAudio.getStop()) return _reproductorAudio.reproduirAnterior();    
            else return "El reproductor está aturat!";
            
    }
    /**
     * metode que crida a la funcio de reproduir el fitxer seguent del reproductor
     * Retorna un String amb l'informacio del fitxer, donant un missatge de que el reproductor
     * esta aturat, que s'ha arribat al final de la cua de reproduccio o un missatge d'error
     * @return
     */
    public String reproduirSeguent() {
        if (!_reproductorAudio.getStop()){
            return  _reproductorAudio.reproduirSeguent();
        } else{
            return "El reproductor está aturat!";
        }

    }
    /**
     * Metode que activa / desactiva l'estat del reproductor a pausat o actiu
     * Retorna un missatge String indicant si esta sonant un arxiu, si s'a pausat
     * o que no hi han dades del reproductor
     * Cal dir que si hi han dades, pero esta en estat de Stop, torna a reproduir des
     * de l'inici
     * @return
     * @throws FitxerAudioErrorException
     */
    public String pushPlayPause(){
        return _reproductorAudio.pushPlayPause();
    }
    /**
     * Es cuan es prem el boto stop. finalitza la reproduccio i posa el comptador
     * del fitxer actual a l'inici
     * @return
     */
    public String pararReproduccio(){
        return _reproductorAudio.pushStop();
    }
    /**
     * Metode que retorna si hi ha algun arxiu sonant
     * @return
     */
    public boolean reproductorActiu(){
        return _reproductorAudio.isPlaying();
    }
    
    /**
     * Metode que incorpora el fitxer d'audio al final de la cua de reproduccio
     * @param numFitxer
     * @return
     */
    public String afegirNouFitxerCuaReproduccio(int numFitxer){
        LlistaReproduccio nova = new LlistaReproduccio(dades.getBiblioteca().getFitxer(numFitxer));
        return _reproductorAudio.afegirElements(nova);
    }

    public String afegirNouFitxerDeLlistaCuaReproduccio(int numFitxer,int numLlista){
        LlistaReproduccio nova = new LlistaReproduccio(dades.getLlista(numLlista).getFitxer(numFitxer));
        return _reproductorAudio.afegirElements(nova);
    }
    /**
     * Metode que incorpora els fitxers d'audio de la llista al final de la cua de reproduccio
     * @param lR
     * @return
     */
    public String afegirNovaLlistaCuaReproduccio(int lR){
         LlistaReproduccio nova = new LlistaReproduccio(dades.getLlista(lR));
         return _reproductorAudio.afegirElements(nova);
        
    }
    /**
     * Metode que retorna un model amb el nom de les llistes de reproduccio y la duracio
          * @return 
     */
    public DefaultListModel getNomLlistes(){
        DefaultListModel model = new DefaultListModel();
        model.clear();
        for(LlistaReproduccio llista: dades.getLlistesReproduccio()){
          model.addElement(Recursos.formatar(llista.getnom(), 40)+ Recursos.mostrarTempsSong(llista.getDuracio()));
        }
        return model;
      
    }
    /**
     * Metode que retorna un model amb la informacio dels fitxers de la llista seleccionada
     * @param numLlista
     * @return 
     */
    public DefaultListModel getDescripcioArxius(int numLlista){
        DefaultListModel model = new DefaultListModel();
        model.clear();
        for(FitxerAudio fitxer: dades.getLlista(numLlista).getLlista()){
          model.addElement(fitxer.toString());
        }
        return model;
        
        
    }
    /**
     * Metode que retorna un model amb els arxius actuals a la biblioteca
     * @return 
     */
    public DefaultListModel getArxiusBibliteca(){
        DefaultListModel model = new DefaultListModel();
        model.clear();
        for(FitxerAudio fitxer: dades.getBiblioteca().getLlista()){
          model.addElement(fitxer.toString());
        }
        return model;
        
        
    }
    /**
     * Metode que retorna un model amb els arxius actuals a la biblioteca
     * @return 
     */
    public DefaultListModel getArxiusCuaReproduccio(){
        DefaultListModel model = new DefaultListModel();
        model.clear();
        for(FitxerAudio fitxer: _reproductorAudio.getColaReproduccio().getLlista()){
          model.addElement(fitxer.toString());
        }
        return model;
    }
    public boolean isRandom(){
        return _reproductorAudio.getRandom();
    }
    public boolean isLoop(){
        return _reproductorAudio.getLoop();
    }

}
