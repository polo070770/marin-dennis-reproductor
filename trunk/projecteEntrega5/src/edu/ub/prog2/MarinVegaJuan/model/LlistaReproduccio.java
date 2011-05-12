package edu.ub.prog2.MarinVegaJuan.model;

import java.util.ArrayList;
import java.io.Serializable;
import java.sql.Time;


/**
 * This class contains an arrayList with instances of FitxerAudio
 * Have a name for identification, number of elements, and the length of all
 * elements inside the ArrayList
 * @author jmarinve7.alumnes
 */
public class LlistaReproduccio implements Serializable{
    private int maxFitxers = 100;
    private ArrayList<FitxerAudio> llista;
    private int numFitxers;
    private String nom;
    private Time duracio;

    /**
     * metode constructor amb parametre que especifica el maxim de fitxers i
     * i el nom the la llista
     * @param maxFitxers int
     */
    public LlistaReproduccio(int maxFitxers, String nom) {
        llista = new ArrayList<FitxerAudio>(maxFitxers);
        this.numFitxers = 0;
        this.nom = nom;
        this.duracio = Time.valueOf("00:00:00");
    }
    
/**
 * Metode constructor que tan sols rep un string
 * @param nom String
 */
    public LlistaReproduccio(String nom) {
        llista = new ArrayList<FitxerAudio>(maxFitxers);
        this.numFitxers = 0;
        this.nom = nom;
        this.duracio = Time.valueOf("00:00:00");
    }
    
    /**
     * Metode constructor que rep una llista de reproduccio
     * @param novaLlista LlistaReproduccio
     */
    public LlistaReproduccio(LlistaReproduccio novaLlista){
        llista = new ArrayList<FitxerAudio>(novaLlista.getLlista());
        numFitxers = novaLlista.getNumFitxers();
        nom = novaLlista.getnom();
        maxFitxers = novaLlista.getMaxFitxers();
        this.duracio = novaLlista.getDuracio();
    }
    /**
     * Metode constructor que genera una llista a partir d'un fitxer
     * @param nouFitxer
     */
    public LlistaReproduccio(FitxerAudio nouFitxer){
        llista = new ArrayList<FitxerAudio>(maxFitxers);
        llista.add(nouFitxer);
        this.numFitxers = 1;
        this.nom = "";
        this.duracio = Time.valueOf("00:00:00");
        this.duracio=Recursos.appendTime(this.duracio,nouFitxer.getDuracio());
    }

    /**
     * Afgeix el fitxer a la llista. Si la llista es al limit de capacitat
     * aumenta el seu tamany en 1 elements
     * @param nouFitxer FitxerAudio
     */
    public void afegirFitxer(FitxerAudio nouFitxer){
        if (numFitxers==maxFitxers){
            llista.ensureCapacity(maxFitxers+1);
            maxFitxers += 1;
        }
        llista.add(nouFitxer);
        this.numFitxers++;
        this.duracio=Recursos.appendTime(this.duracio,nouFitxer.getDuracio());
    }
    /**
     * Sets name of list
     * @param nom
     */
    public void setNom(String nom){
        this.nom = nom;
    }
    /**
     * Returns the name of this element
     * @return
     */
    public String getnom(){
        return this.nom;
    }
    /**
     * retorna el num Maxim de fitxers
     * @return int maxFitxers
     */
    public int getMaxFitxers() {
        return maxFitxers;
    }
    /**
     * estableix el num maxim de fitxers
     * @param maxFitxers
     */
    public void setMaxFitxers(int maxFitxers) {
        this.maxFitxers = maxFitxers;
    }
    
    /**
     * Retorna el num de fichers actual
     * @return
     */
    public int getNumFitxers() {
        return numFitxers;
    }
    /**
     * estableix el num de fitxersActuals
     * @param numFitxers
     */
    public void setNumFitxers(int numFitxers) {
        this.numFitxers = numFitxers;
    }
    

    /**
     * Retorna el fitxer que es a la posicio de la llista
     * El fitxer inicial de la llista es el 1, si es demana el fitxer 0 donara error
     * @param numFitxer int
     * @return
     */
    public FitxerAudio getFitxer(int numFitxer){
        FitxerAudio prov = llista.get(numFitxer-1);
        return prov;
    }
    /**
     * Elimina el fitxer de la llista
     * @param posicio int
     */
    public void eliminarFitxer(int posicio){
        this.duracio = Recursos.extractTime(this.duracio,llista.get(posicio-1).getDuracio());
        llista.remove(posicio);
        this.numFitxers--;
    }
    /**
     * Removes the first occurrence of the specified FitxerAudio from this LlistaReproduccio
     * @param fitxer FitxerAudio
     */
    public boolean eliminarFitxer(FitxerAudio fitxer){
        boolean accio = false;

        accio = llista.remove(fitxer);
        if (accio){
            this.numFitxers--;
            this.duracio = Recursos.extractTime(this.duracio, fitxer.getDuracio());
        }
        return accio;
    }
    /**
     * Returns an String representation of this object
     * @return
     */
    @Override
    public String toString(){
        String r;
        int i = 1;
        r=this.nom.toUpperCase()+"   ---   " + this.numFitxers+" canciones. "+Recursos.mostrarTempsLlistaReproduccio(this.duracio)+" \n\n";
        r+="Num Canción                             Artista                        Duración   Album                            Añadido\n";
        for(FitxerAudio f: llista){
            r+=Recursos.formatar(i,3)+" ";
            r+=f.toString()+"\n";
            i++;
        }
        return r;
    }

    /**
     * Returns if two LListaReproduccio are the same object
     * @param llista
     * @return
     */
    public boolean equals(LlistaReproduccio llista){
        return this.equals(llista);
    }
    /**
     * Return the length (minutes) of this element
     * @return
     */
    public Time getDuracio() {
        return duracio;
    }
    /**
     * Return if exists element in collection
     * @param f
     * @return
     */
    public boolean exists(FitxerAudio f){
        boolean accio = false;
         for ( FitxerAudio fitxer : llista){
             if(fitxer.equals(f)) accio = true;

         }
        return accio;
    }
    
    
    /**
     * Retorna true si l'element indicat existeix
     * @param num
     * @return
     */
    public boolean exists(int num){
        return this.llista.get(num)!= null;
    }
    /**
     * Retorna un boolea indicans si hi han fitxers d'audio
     * @return
     */
    public boolean haveFiles(){
        return this.numFitxers>0;
    }
    
    /**
     * Retorna la llista de reproduccio
     * @return
     */
    public ArrayList<FitxerAudio> getLlista(){
        return this.llista;
    }
    
    /**
     * substitueix la llista
     * @param llista
     */
    public void setLlista(LlistaReproduccio llista) {
        this.llista = llista.getLlista();
        this.numFitxers = llista.getNumFitxers();
        this.duracio = llista.getDuracio();
        this.llista.trimToSize();
    }
    /**
     * insereix la duracio de la llista
     * @param duracio
     */
    public void setDuracio(Time duracio) {
        this.duracio = duracio;
    }
}
