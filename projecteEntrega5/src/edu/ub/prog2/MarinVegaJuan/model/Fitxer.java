package edu.ub.prog2.MarinVegaJuan.model;

import java.io.Serializable;
import java.util.Calendar;


/**
 * This class defines the general atributes of computer file
 * @author jmarinve7.alumnes
 */
public class Fitxer implements Serializable{
    Calendar c = Calendar.getInstance();
    private String nom;
    private String extensio;
    private Data data;
    private String localitzacio;
    private String completePath;
    /**
     * New instance of Fixter Class
     * @param nom
     * @param extensio
     * @param localitzacio
     */
    public Fitxer(String nom, String extensio, String localitzacio){
        String dia, mes, any, insercio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        any = Integer.toString(c.get(Calendar.YEAR));
        insercio = dia+"/"+mes+"/"+any;
        this.localitzacio = localitzacio;
        this.nom = nom;
        this.extensio = extensio;
        this.data = new Data(insercio);

    }
    public Fitxer(String path){
        String dia, mes, any, insercio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        any = Integer.toString(c.get(Calendar.YEAR));
        insercio = dia+"/"+mes+"/"+any;
        this.completePath = path;
        this.data = new Data(insercio);

    }
    public Fitxer(){
    }
    /**
     * Return tdata of files
     * @return String
     */
    public String getData(){
        return this.data.toString();
    }
    /**
     * Sets the data of file
     * @param d
     */
    public void setData(String d){
        this.data = new Data(d);
    }
    /**
     * Return the extension of file
     * @return String
     */
    public String getExtensio() {
        return extensio;
    }
    /**
     * Sets the extension of file
     */
    public void setExtensio(String extensio) {
        this.extensio = extensio;
    }
    /**
     * Return the location of file
     * @return String
     */
    public String getLocalitzacio() {
        return localitzacio;
    }
    /**
     * Sets the location of file
     * @param localitzacio
     */
    public void setLocalitzacio(String localitzacio) {
        this.localitzacio = localitzacio;
    }

    public String getNom() {
        return nom;
    }
    /**
     * Sets the name of file
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

     /**
      * Compara dues dates
      * @param data String
      * @return boolean
      */
     public boolean dataIsEquals(String data){
         Data provisional = new Data(data);
         return this.data.equals(provisional);
     }

       @Override
       /**
        * Returns an String representation of this object
        */
    public String toString(){
        return Recursos.formatar(nom+"."+extensio,20)+Recursos.formatar(data.toString(),15)+Recursos.formatar(localitzacio,30);

    }
       /**
        * funcio que retorna el nom del fitxer complet
        * @return nom y extensio
        */
    public String getCompleteFileName(){
        return this.nom+"."+this.extensio;
    }
    
    /**
     * Funcio que retorna la ruta completa del fitxer, incloent el nom
     * @return path, nom, extensio
     */
    public String getCompletePath(){
        if(this.completePath.length()!=0) return this.completePath;
        if (this.localitzacio.equals("")){
            return this.getCompleteFileName();
        }else{
            return this.localitzacio+"/"+getCompleteFileName();    
        }
        
    }
}

/**
 * Implements custom Class Data
 * @author marin
 */
class Data implements Serializable{
    private int day;
    private int month;
    private int year;

    /**
     * Create a new Data object from integer values
     * @param day int
     * @param month int
     * @param year int
     */
    public Data(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Create a new data object from String
     * it values will be cast to an integer
     * @param d String in numeric format ex: 11/01/2010
     */
    public Data(String d){
        String[] data = d.split("/");

        this.day =  Integer.parseInt(data[0]);
        this.month = Integer.parseInt(data[1]);
        this.year = Integer.parseInt(data[2]);
    }
    /**
     * Returns true when data is equals
     * @param d Data
     * @return boolean
     */
    public boolean equals(Data d){
        return (this.day == d.day && this.month == d.month && this.year == year);
    }
    @Override
    /**
     * Returns an string representation of object
     */
    public String toString(){
        return day+"/"+month+"/"+year;
    }
}
