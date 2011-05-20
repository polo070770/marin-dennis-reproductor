package edu.ub.prog2.QuitaquisTamayDennis.controlador;

import edu.ub.prog2.QuitaquisTamayDennis.model.CuaReproduccio;
import edu.ub.prog2.utils.FitxerAudioErrorException;
import edu.ub.prog2.utils.ReproductorBasic;
import edu.ub.prog2.utils.ReproductorEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que hereta de la classe ReproductorBasic, que es troba en les llibreries 
 * facilitades per el campus.
 * Aquesta classe reproduirà una cua de reproducció, i a mes tindrà dos modes de
 * reproduir: ciclicament i/o aleatoriament.
 * @author Maikel
 */
public class ReproductorAudio extends ReproductorBasic {

    //Atributs
    private boolean PLAY = false;
    private boolean PAUSE = false;
    private boolean STOP = false;
    private boolean RANDOM = false;
    private boolean CYCLIC = false;
    private CuaReproduccio cua;
    private CuaReproduccio cuaTmp = cua;
    private int pista = 0;

    //Getter i Setters
    public boolean isPAUSE() {
        return PAUSE;
    }

    public void setPAUSE(boolean PAUSE) {
        this.PAUSE = PAUSE;
    }

    public boolean isPLAY() {
        return PLAY;
    }

    public void setPLAY(boolean PLAY) {
        this.PLAY = PLAY;
    }

    public int getPista() {
        return pista;
    }

    public void setPista(int pista) {
        this.pista = pista;
    }

    public boolean isSTOP() {
        return STOP;
    }

    public void setSTOP(boolean STOP) {
        this.STOP = STOP;
    }

    public boolean isRANDOM() {
        return RANDOM;
    }

    public void setRANDOM(boolean RANDOM) {
        this.RANDOM = RANDOM;
    }

    public boolean isCYCLIC() {
        return CYCLIC;
    }

    public void setCYCLIC(boolean CYCLIC) {
        this.CYCLIC = CYCLIC;
    }

    public CuaReproduccio getCua() {
        return cua;
    }

    public void setCua(CuaReproduccio cua) {
        this.cua = cua;
    }

    //Constructor
    public ReproductorAudio() {

        super();
        cua = new CuaReproduccio();

    }

    //Metodes
    //-------------------------------------------------------------------------//
    //--------------------------OVERWRITES-------------------------------------//
    //-------------------------------------------------------------------------//
    @Override
    public void onStart(ReproductorEvent re) {
        this.PLAY = true;
        this.STOP = false;

    }

    @Override
    public void onResume(ReproductorEvent re) {

        this.PLAY = true;
        this.PAUSE = false;
        this.STOP = false;

    }

    @Override
    public void onPause(ReproductorEvent re) {

        this.PAUSE = true;
        this.PLAY = false;

    }

    @Override
    public void onStop(ReproductorEvent re) {
        this.PLAY = false;
        this.STOP = true;
    }

    @Override
    public void onEndFile(ReproductorEvent re) {

        canContinue();

    }

    public void canContinue() {

        if (cua.getLlistaAudio().size() == 1) {
            //Reproduciendo un fichero

            if (this.CYCLIC) {
                this.pista = 0;
                Play();

            } else {

                stop();
            }
        } else {

            this.pista++;
            //Reproduciendo una lista 
            if (this.CYCLIC && this.pista == cua.getLlistaAudio().size()) {

                this.pista = 0;
                Play();

            } else if (this.CYCLIC && this.pista == 0) {

                this.pista = cua.getLlistaAudio().size() - 1;
                Play();

            } else if (this.pista >= cua.getLlistaAudio().size()) {

                this.pista = cua.getLlistaAudio().size();
                stop();

            } else {

                Play();

            }

        }

    }

    public void Play() {

        boolean buida = buidaCuaReproduccio();

        if (!buida) {

            try {

                stop();
                System.out.println(cua.getRutaCompleta(pista));
                openAudioFile(cua.getRutaCompleta(pista));

            } catch (FitxerAudioErrorException ex) {

                ex.getMessage();
            }

            play();
            //System.out.println("Sonant: " + cua.getNomExtensioAudio(pista));
        }

    }

    public void Play(String ruta) {

        try {

            stop();
            openAudioFile(ruta);

        } catch (FitxerAudioErrorException ex) {

            ex.getMessage();
        }

    }

    //-------------------------------------------------------------------------//
    //--------------------------Metodes-CuaReproduccio-------------------------//
    //-------------------------------------------------------------------------//
    public CuaReproduccio obteCuaReproduccio() {

        return this.cua;

    }

    public boolean buidaCuaReproduccio() {

        return cua.isEmpty();

    }

    public void randomCuaReproduccio() {
        if (!this.RANDOM) {

            this.cuaTmp = this.cua;
            this.cua.shuffle();
            this.RANDOM = true;

        } else {

            this.cua = this.cuaTmp;
            this.RANDOM = false;
            this.pista = -1;

        }

    }

    //-------------------------------------------------------------------------//
    //--------------------------Menu-Reproductor-------------------------------//
    //-------------------------------------------------------------------------//
    public void reproSeguent() {

        canContinue();

    }

    public void reproAnterior() {

        this.pista = this.pista - 2;
        if (this.pista < -1) {

            this.pista = -1;
            canContinue();

        } else {

            canContinue();

        }
    }
}
