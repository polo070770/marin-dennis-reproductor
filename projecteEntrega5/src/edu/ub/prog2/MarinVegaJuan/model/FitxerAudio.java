package edu.ub.prog2.MarinVegaJuan.model;

import java.io.Serializable;
import java.sql.Time;

/**
 * This class contains some information of music files
 * It's an extension of File class
 * @author jmarinve7.alumnes
 */
public class FitxerAudio extends Fitxer implements Serializable{

    private String nom;
    private String autor;
    private String disc;
    private int anyEdicio;
    private int numSong;
    private String discografica;
    private String genre;
    private Time duracio;

    /**
     * Metode constructor, crida al metode constructor de la clase que hereda
     * @param nom String
     * @param autor String
     * @param disc  String
     * @param anyEdicio int
     * @param numSong  int
     * @param discografica  String
     * @param genre String
     * @param duracio   float
     * @param superLocalitzacio String
     * @param superNom  String
     * @param superExtensio String
     */
    public FitxerAudio(String nom, String autor, String disc, int anyEdicio, int numSong, String discografica, String genre, String duracio, String superLocalitzacio, String superNom, String superExtensio){
        super(superNom,superExtensio, superLocalitzacio);
        this.nom = nom;
        this.autor = autor;
        this.disc = disc;
        this.anyEdicio = anyEdicio;
        this.numSong = numSong;
        this.discografica = discografica;
        this.genre = genre;
        this.duracio = Time.valueOf(duracio);
    }
    public FitxerAudio(String nom, String autor, String disc, int anyEdicio, int numSong, String discografica, String genre, String duracio, String path){
            super(path);
            this.nom = nom;
            this.autor = autor;
            this.disc = disc;
            this.anyEdicio = anyEdicio;
            this.numSong = numSong;
            this.discografica = discografica;
            this.genre = genre;
            this.duracio = Time.valueOf(duracio);
        }
    public FitxerAudio() {
    }
    /**
     * Returns the length (in minutes) of this element
     * @return
     */
    public Time getDuracio(){
        return this.duracio;
    }
    @Override
     /**
     * Returns an string representation of this object
     */
    public String toString(){
        String r;
        //r=super.toString();
        r=Recursos.formatar(this.nom, 35) + " "+ Recursos.formatar(this.autor,30) + " " + Recursos.formatar(Recursos.mostrarTempsSong(this.duracio),10);
        return r+" "+ Recursos.formatar(this.disc, 30)+"   "+ super.getData();
    }
    public String shortToString(){
         String r;
        //r=super.toString();
        r=Recursos.formatar(this.nom, 20) + " De  "+ Recursos.formatar(this.autor,20) + " Duracio " + Recursos.formatar(Recursos.mostrarTempsSong(this.duracio),10);
        return r+"A l'album "+ Recursos.formatar(this.disc, 20);
        
    }


}
