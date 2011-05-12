/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.MarinVegaJuan.controlador;

import edu.ub.prog2.MarinVegaJuan.model.ColaReproduccio;
import edu.ub.prog2.MarinVegaJuan.model.LlistaReproduccio;
import edu.ub.prog2.utils.FitxerAudioErrorException;
import edu.ub.prog2.utils.ReproductorBasic;
import edu.ub.prog2.utils.ReproductorEvent;

/**
 * Clase que s'encarrega de controlar els esdeveniments relatius a la reproduccio
 * d'audio
 * @author socrates
 */
public class ReproductorAudio extends ReproductorBasic {

    private ColaReproduccio colaReproduccio;
    private boolean PLAY = false;
    private boolean STOP = false;
    private boolean LOAD = false;
    private boolean END = false;
    private boolean random;
    private boolean loop;

    /**
     * Metode constructor que rep els valors de random y loop
     * @param random
     * @param loop
     */
    public ReproductorAudio(boolean random, boolean loop) {
        this.colaReproduccio = new ColaReproduccio();
        this.random = random;
        this.loop = loop;


    }

    /**
     * Metode que iniciala reproduccio, posa el valor LOAD a true
     * i els END i STOP a false. Carrega l'informacio del fitxer
     * indicat en colaReproduccio i crida al metode play()
     * @return missatge del reproductor
     */
    public String comenzarReproduccio(){
        this.LOAD = true;
        this.END = false;
        this.STOP = true;
        //openAudioFile(colaReproduccio.getPathFile());
        //play();
        return reproduirSeguent();
    }
    /**
     * Metode que reprodueix el seguent element de la cua de reproduccio
     * Si la llista esta en l'estat STOP, aquest metode s'invoca per reproduir el 
     * primer fitxer, si la llista esta amb reproduccio activa, aquest metode s'invoca
     * per saltar al fitxerSeguent i reproduir-ho
     * @return
     */
    public String reproduirSeguent() {
        // primer hem de saber si hi han dades a la cua de reproduccio
        if (getReproductorCarregat()) {
            // despres hem de saber si podem continuar, ja que si som a l'ultim
            // fitxer i la llista no es loop, no podrem continuar
            if (colaReproduccio.canContinue(loop)|| STOP) {
                try {
                    stop();
                    openAudioFile(this.colaReproduccio.nextFile());
                    play();
                    return "Sonant: " + getDadesReproduccioActiva();
                } catch (FitxerAudioErrorException ex) {
                    //reproduirSeguent();
                    return "Ha succeit un error";
                }
            } else {
                // Si no puc continuar vol dir que he arribat al final de la cua de reproduccio
                // per tant, activo l'estat END i invoco stop() per que s'alti l'esdeveniment STOP
                this.END = true;
                stop();
                return "Cua de reproduccio finalitzada";
            }
            
        } else {
            return "No hi han fitxers carregats al reproductor";
        }
    }

    /**
     * Metode que reprodueix el fitxerAnterior, si la reproduccio es activa
     * salta a l'element anterior en comptes de comenzar la canço per el principi
     * @return
     */
    public String reproduirAnterior() {
        if (getReproductorCarregat()) {
            // Un cop sabe si hi han dades, hem de saber si la llista no esta en
            // END, en aquest cas END simbolitza STOP, pero com cada cop que
            // fem salts entre cançons hem de premer STOP, utilitzo END per
            // diferenciar-ho
            if (!END) {
                try {
                    stop();
                    openAudioFile(this.colaReproduccio.prevFile());
                    play();
                    return "Sonant: " + getDadesReproduccioActiva();
                } catch (FitxerAudioErrorException ex) {
                    //reproduirSeguent();
                    return "Ha succeit un error";
                }
            } else {
                this.END = true;
                stop();
                return "Cua de reproduccio finalitzada";
            }
        } else {
            return "No hi han fitxers carregats al reproductor";
        }

    }
    /**
     * Funcio que retorna un boolea indican si está sonant un fitxer d'Audio
     * @return
     */
    
    public boolean isPlaying() {
        return this.PLAY;
    }
    /**
     * Funcio que retorna un String amb informacio relativa al fitxer
     * que está sonant
     * @return
     */
    public String getDadesReproduccioActiva() {
        return colaReproduccio.getInfoFitxerActual();
    }

   
   /**
    * Funcio que substitueix els fitxers d'audio de la cua de reproduccio
    * Si el bollea random es true, s'invocara al metode reOrdenar()
    * @param llista LlistaReproduccio Amb els elements nous per reproduir
    * @param random boolea que indica si la reproduccio será aleatoria
    * @param loop boolea que indica si la reproduccio será ciclica
    */
    public void introduirNovaLlista(LlistaReproduccio llista, boolean random, boolean loop) {
        this.random = random;
        this.loop = loop;
        stop();
        colaReproduccio.addLlista(llista);
        reOrdenar();
        colaReproduccio.initFilePlaying();

    }
    /**
     * Funcio que invoca al metode shuffle de la classe pare,
     * aquest metode organitzara els elements de la llista de forma aleatoria
     */
    public void reOrdenar() {
        if (random) {
            colaReproduccio.shuffle();
        }
    }

    /**
     * Esdeveniment que s'invoca cuan es resumeix la reproduccio
     * @param arg0
     */
    @Override
    public void onResume(ReproductorEvent arg0) {
        //System.out.println("Esta sonan  "+colaReproduccio.getInfoFitxerActual());
        this.PLAY = true;
        this.STOP = false;

    }
    /**
     * Esdeveniment que s'invoca quan es pausa la reproduccio
     * @param arg0
     */
    @Override
    public void onPause(ReproductorEvent arg0) {
        //System.out.println("Esta sonan  "+colaReproduccio.getInfoFitxerActual());
        this.PLAY = false;

    }
    /**
     * Esdevenimant que s'invoca cuan comencem la reproduccio
     * @param arg0
     */
    @Override
    public void onStart(ReproductorEvent arg0) {
        this.PLAY = true;
        this.END = false;
        this.STOP = false;
    }
    
    /**
     * Esdeveniment que s'invoca cuan finalitza el fitxerD'audio
     * @param argo
     */
    @Override
    public void onEndFile(ReproductorEvent argo) {
        reproduirSeguent();

    }
    
    /**
     * Esdeveniment que s'invoca un cop hem cridat la funcio stop()
     * @param argo
     */
    @Override
    public void onStop(ReproductorEvent argo) {
        this.PLAY = false;
        if (this.END) {
            this.STOP = true;
            colaReproduccio.reloop();
            colaReproduccio.initFilePlaying();
        }

    }
    /**
     * Metode que retorna si el reproductor s'ha iniciat
     * aixo nomes ocurreix cuan hem pres l'opcio de reproduir un arxiu, llista
     * o biblioteca
     * @return
     */
    public boolean getReproductorCarregat() {
        return this.LOAD;
    }
    /**
     * Metode que inicia i pausa la reproduccio en funcio dels diferents estats
     * 
     * @return String indicant l'accio que s'a dut a terme
     */
    public String pushPlayPause() {
        String missatge = "";
        // Si hi han dades al reproductor
        if (getReproductorCarregat()) {
            if (!PLAY) {
                // Si el reproductor no esta reproduint
                if (STOP) {
                    // Si esta en stop, reiniciem el comptador de la cua e iniciem 
                    // la reproduccio
                    colaReproduccio.reloop();
                    missatge = reproduirSeguent();
                    //comenzarReproduccio();
                } else {
                    //Si no esta en stop ni en play, vol dir que esta en pause
                    // llavors invoquem el metode play
                    play();
                    missatge = "Sonant: " + colaReproduccio.getInfoFitxerActual();
                }
            } else {
                // si esta en play invoquem el metode pause
                pause();
                missatge = "Reproductor pausat!";
            }
        } else {
            // Si no hi han dades al reproductor retornem el missatge
            missatge = "No hi han fitxers carregats al reproductor";
        }
        return missatge;
    }
    
    /**
     * Posem el parametre loop
     * @param loop
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    /**
     * posem el parametre random
     * @param random
     */
    public void setRandom(boolean random) {
        this.random = random;
        if (random)colaReproduccio.shuffle();
    }
    /**
     * Metode que afegeix elements a la cua de reproduccio
     * Aquest metode s'invoca mentre l'estat play estigui actiu
     * els elemnts s'afeiran al final de la cua de reproduccio
     * @param nova LlistaReproduccio
     * @return
     */
    public String afegirElements(LlistaReproduccio nova) {
        return colaReproduccio.extendreCuaReproduccio(nova);
    }

    /**
     * Metode que finalitza la reproduccio e inicia el comptador de la cua de
     * reproduccio
     * @return
     */
    String pushStop() {
        this.END = true;
        stop();
        return "Reproduccio terminada!";
    }
    public boolean getStop(){
        return this.STOP;
    }

    public ColaReproduccio getColaReproduccio() {
        return colaReproduccio;
    }
    public boolean getRandom(){
        return this.random;
    }
    public boolean getLoop(){
        return this.loop;
    }

}
