package edu.ub.prog2.QuitaquisTamayDennis.model;

import java.io.Serializable;

/**
 * Aquesta classe hereta de la classe Fitxer, i defineix els atributs que conte un fitxer d'audio. 
 * @author Maikel
 */
public class FitxerAudio extends Fitxer implements Serializable {
    
    //Atributs
    private String nomAudio;
    private String autorAudio;
    private String discAudio;
    private String discograficaAudio;
    private String genereMusicalAudio;
    private String duracioAudio;
    private int anyEdicioAudio;
    private int numeroAudioDisc;

    //Getters   
    /**
     * Nom de la música
     * @return Nom del audio
     */
    public String getNomAudio() {
        return nomAudio;
    }
    
    /**
     * Autor de la música
     * @return Autor del audio
     */
    public String getAutorAudio() {
        return autorAudio;
    }

    /**
     * Disc on està la música
     * @return Nom del disc
     */
    public String getDiscAudio() {
        return discAudio;
    }

    /**
     * Discografica del disc
     * @return Discografica
     */
    public String getDiscograficaAudio() {
        return discograficaAudio;
    }

    /**
     * Duració del Audio
     * @return Duració
     */
    public String getDuracioAudio() {
        return duracioAudio;
    }

    /**
     * Gènere musical del audio
     * @return gènere musical
     */
    public String getGenereMusicalAudio() {
        return genereMusicalAudio;
    }

    /**
     * Any d'edició del audio
     * @return Any d'edicio
     */
    public int getAnyEdicioAudio() {
        return anyEdicioAudio;
    }

    /**
     * Número del audio dins del disc
     * @return Número del audio
     */
    public int getNumeroAudioDisc() {
        return numeroAudioDisc;
    }

    //Constructor
    /**
     * Constructor de la classe que defireix de la classe Fitxer que guarda la informació de un fitxer audio
     * @param localitzacioFitxer - ubicació
     * @param nomFitxer - nom
     * @param extensioFitxer - extensió
     * @param nomAudio - nom
     * @param autorAudio - actor
     * @param discAudio - disc
     * @param anyEdicioAudio - any d'edició
     * @param numeroAudioDisc - numero de la canço al disc
     * @param discografiaAudio - nom del disc de la canço
     * @param genereMusicalAudio - génere musical
     * @param duracioAudio - duracio de la canço
     */
    public FitxerAudio(String localitzacioFitxer,
            String nomFitxer,
            String extensioFitxer,
            String nomAudio,
            String autorAudio,
            String discAudio,
            int anyEdicioAudio,
            int numeroAudioDisc,
            String discografiaAudio,
            String genereMusicalAudio,
            String duracioAudio) {

        super(localitzacioFitxer, nomFitxer, extensioFitxer);

        this.nomAudio = nomAudio;
        this.autorAudio = autorAudio;
        this.discAudio = discAudio;
        this.anyEdicioAudio = anyEdicioAudio;
        this.numeroAudioDisc = numeroAudioDisc;
        this.discograficaAudio = discografiaAudio;
        this.genereMusicalAudio = genereMusicalAudio;
        this.duracioAudio = duracioAudio;

    }
    
    public FitxerAudio(String localitzacioFitxer,
            String nomFitxer,
            String extensioFitxer){
        
        super(localitzacioFitxer, nomFitxer, extensioFitxer);
        
    }

    //Metodes
    /**
     * Metode que ajunta la ruta el nom i la extensio del fitxer i la retorna
     * @return rutaCompleta
     */
    public String getRutaNomExtensio() {
        
        String rutaCompleta = super.getRutaFitxer() +"." + super.getExtensioFitxer();
        return rutaCompleta;

    }
    
    /**
     * Metode que adjunta el nom i la extensio del fixer i la retorna
     * @return nom + extensio
     */
    public String getNomExtensio(){
        
        String nomComplet = super.getNomFitxer()+ "." +super.getExtensioFitxer();
        return nomComplet;
    }

}
