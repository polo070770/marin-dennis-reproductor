/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ub.prog2.MarinVegaJuan.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

/**
 * This class contains all the important information of this program
 *
 * @author marin
 */
public class DadesReproductor implements Serializable{
    private int numLlistes;
    private Time duracioLlistes;
    private Time duracioBiblioteca;
    private LlistaReproduccio biblioteca;
    private ArrayList<LlistaReproduccio> llistesReproduccio;
    private boolean random;
    private boolean loop;

    public DadesReproductor(){
        biblioteca = new LlistaReproduccio("Biblioteca");
        llistesReproduccio = new ArrayList<LlistaReproduccio>();
        this.numLlistes = 0;
        this.duracioLlistes = Time.valueOf("00:00:00");
        this.duracioBiblioteca = biblioteca.getDuracio();
        this.random = false;
        this.loop = false;
                
    }
    /**
     * Insert a new instance of LlistaReproduccio in ArrayList
     * @param lP
     */
    public void afegirLlistaReproduccio(LlistaReproduccio lR){
        // Ampliem la longitud de la lista en un element
        this.llistesReproduccio.ensureCapacity(numLlistes+1);
        this.llistesReproduccio.add(lR);
        this.numLlistes++;
    }
    /**
     * Removes object of this element
     * @param lP
     */
    public void eliminarLlistaReproduccio(LlistaReproduccio lR){
        //Restem la duracio
        this.duracioLlistes= Recursos.extractTime(this.duracioLlistes,lR.getDuracio());
        //Eliminem l'elemen
        this.llistesReproduccio.remove(lR);
        //Ajustem la capacitat
        this.llistesReproduccio.trimToSize();
        //Restem el numero de llistes
        this.numLlistes--;
    }
    /**
     * Remove object of bibloteca, and then, removes object of llistesReproduccio
     * if exist's
     * @param f
     * @return
     */
    public boolean eliminarFitxerBiblioteca(FitxerAudio f){
        boolean accio = false;
        accio = biblioteca.eliminarFitxer(f);
        for(LlistaReproduccio llista: llistesReproduccio){
            while(llista.exists(f)){
                llistesReproduccio.get(llistesReproduccio.indexOf(llista)).eliminarFitxer(f);
                this.duracioLlistes = Recursos.extractTime(this.duracioLlistes,f.getDuracio());
            }
        }
        this.duracioBiblioteca = biblioteca.getDuracio();
        return accio;
    }
    /**
     * Removes object of llistaReproduccio
     * @param f
     * @param lP
     * @return
     */
    public boolean eliminarFitxerLlista(FitxerAudio f, LlistaReproduccio lR){
       boolean accio = false;
        for(LlistaReproduccio llista: llistesReproduccio){
            if(llista.equals(lR)){
                llistesReproduccio.get(llistesReproduccio.indexOf(llista)).eliminarFitxer(f);
                this.duracioLlistes = Recursos.extractTime(this.duracioLlistes, f.getDuracio());
            }

        }
        return accio;
    }

    /**
     * Apend FitxerAudio to especified llistaReproduccio
     * @param f
     * @param lR
     */
    public void afegirFitxerLlista(FitxerAudio f, LlistaReproduccio lR){
        llistesReproduccio.get(llistesReproduccio.indexOf(lR)).afegirFitxer(f);
        this.duracioLlistes = Recursos.appendTime(this.duracioLlistes,f.getDuracio());

    }
    public void afegirFitxerLlista(int f, int lR){
        getLlista(lR).afegirFitxer(biblioteca.getFitxer(f));
        this.duracioLlistes = Recursos.appendTime(this.duracioLlistes,biblioteca.getFitxer(f).getDuracio());
    }
    public void eliminarFitxerLlista(int f, int lR){
        getLlista(lR).eliminarFitxer(biblioteca.getFitxer(f));
        this.duracioLlistes = Recursos.extractTime(this.duracioLlistes,biblioteca.getFitxer(f).getDuracio());
    }
    /**
     * Apend FitxerAudio to Biblioteca
     * @param f
     */
    public void afegirFitxerBiblioteca(FitxerAudio f){
        biblioteca.afegirFitxer(f);
        this.duracioBiblioteca = biblioteca.getDuracio();
    }
    /**
     * Returns an String representation of Llistes(ArrayList)
     * @return
     */
    public String llistesActuals(){
        String n="";
        int i = 1;
        n="Llistes de reproduccio actuals ----- "+ this.numLlistes +" llistes. "+Recursos.mostrarTempsLlistaReproduccio(this.duracioLlistes)+"\n\n";
        n+="Num Nom llista\n";
        for(LlistaReproduccio llista:llistesReproduccio){
            n+=Recursos.formatar(i, 3)+" "+Recursos.formatar(llista.getnom(),40)+"\n";
            i++;
        }
        return n;
    }

    /**
     * Returns an String representation of biblioteca
     * Calling to methond biblioteca.toString();
     * @return
     */
    public String bibliotecaActual(){
        return biblioteca.toString();
    }

    /**
     * Returns a specified LlistaReproduccio
     * @param i
     * @return
     */
    public LlistaReproduccio getLlista(int i){
        LlistaReproduccio prov = llistesReproduccio.get(i-1);
        return prov;
    }
    /**
     * Return Biblioteca
     * @return
     */
    public LlistaReproduccio getBiblioteca(){
        return biblioteca;
    }
    /**
     * Retorna un boolea indicant si hi han o no llistes de reproduccio
     * @return
     */
    public boolean haveLists(){
        return this.numLlistes>0;
    }
    
    /**
     * Metode que retorna si esta en mode ciclic
     * @return
     */
    public boolean isLoop() {
        return loop;
    }
    /**
     * metode que cambia l'estat del mode ciclic
     * @param loop
     */
    public void changeLoop() {
        this.loop = !loop;
    }
    
    /**
     * metode que retorna si esta no mode random
     * @return
     */
    public boolean isRandom() {
        return random;
    }
    /**
     * metode que cambia l'estat del mode aleatori
     * i el retorna
     * @return
     */
    public boolean setRandom() {
        this.random = !random;
        return this.random;
    }
    /**
     * metode que cambia l'estat del mode ciclic
     * i el retorna
     * @return
     */
    public boolean setLoop() {
        this.loop = !loop;
        return this.loop;
    }
    public ArrayList<LlistaReproduccio> getLlistesReproduccio() {
        return llistesReproduccio;
    }
    
    

}

