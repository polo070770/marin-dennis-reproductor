/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ub.prog2.MarinVegaJuan.model;

import java.sql.Time;

/**
 * Conte metodes utils
 * @author marin
 */
public class Recursos {

    /**
     * Dona format al text
     * @param text Text al que donarli format
     * @param longitud longitud de caractes en la que es formatara el text
     * @return
     */
     public static String formatar(String text, int longitud){
        text+="                                                                 ";
        return text.substring(0, longitud);
    }
    /**
     * Dona format a un valor determinar
     * Retorna el valor inserit amb tants  0's a la dreta fins que s'arribi
     * a la longitud especificada al parametre (max 13)
     * @param valor
     * @param longitud
     * @return
     */
    public static String formatar(int valor, int longitud){
        String cero ="000000000000";
        String text = cero+String.valueOf(valor);
        return text.substring(text.length()-longitud);
    }
    /**
     * Dona format a un double, principalment l'usarem per transformar el total
     * de temps en hores minuts y segons
     * @param temps
     * @return
     */
    //OBSOLETO
    public static String formatar(double temps){
        int hores, minuts, segons;
        minuts = (int) temps;
        if(minuts+0.0<=temps-0.60){
            temps-=0.60;
            temps+=1;
        }
        minuts = (int) temps;
        segons = (int)((temps-minuts)*100);
        hores = (int)temps /60;
        for(int i = hores; i>0; i--){
            minuts -=60;
        }
        if (hores!=0)return String.valueOf(hores)+":"+String.valueOf(minuts)+":"+String.valueOf(segons);
        else return  String.valueOf(minuts)+":"+String.valueOf(segons);
    }

    /**
     * Funcion que utilizamos para sumar variables de tiempo
     * @param a
     * @param b
     * @return
     */
    public static Time appendTime(Time a, Time b){
        Time resultant;
        int h, m, s;
        // añadimos a las variables h,m,s la suma de los tiempos a y b como enteros
        // luego las convertimos a un objeto del tipo Time otra vez
        h = a.getHours()+ b.getHours();
        m = a.getMinutes()+ b.getMinutes();
        s = a.getSeconds() + b.getSeconds();
        resultant = Time.valueOf(h+":"+m+":"+s);
        return resultant;
    }
    /**
     * Funcion que utilizamod para restar variables de tiempo
     * @param a
     * @param b
     * @return
     */
    public static Time extractTime(Time a, Time b){
        Time resultant;
        int h, m, s;
        //Añadimos a las variables h,m,s la diferencia de los tiempos de a i b
        //a es el tiempo al cual le restamos b, luego lo volvemos a convertir un objeto del tipo tiempo
        h = a.getHours()- b.getHours();
        m = a.getMinutes()- b.getMinutes();
        s = a.getSeconds() - b.getSeconds();
        resultant = Time.valueOf(h+":"+m+":"+s);
        return resultant;
    }
    /**
     * funcion que utilizamos para mostrar el tiempo de la lista de reproduccion
     * por ahora falla cuando se superan las 24 h, los dias se pierden, y los meses tambien
     * @param a
     * @return
     */
    public static String mostrarTempsLlistaReproduccio(Time a){
        String resultant="", meses, dias;
        try{
            meses=a.getMonth()+" meses, ";
        }catch(Exception e){
            meses="";
        }
        try{
           dias=a.getDate()+" días, ";
        }catch(Exception e){
            dias="";
        }
        resultant+=meses+dias;
        if(a.getHours()!=0) resultant+=a.getHours()+" horas, ";
        if(a.getMinutes()!=0) resultant+=a.getMinutes()+" minutos";
        if(a.getSeconds()!=0)resultant+=" y "+a.getSeconds()+" segundos";
        return resultant;
    }

    /**
     * funcion para mostrar la duracion de las canciones, si las horas son 0 no se muestran
     * @param a
     * @return
     */
    public static String mostrarTempsSong(Time a){
        String resultant = "";
        if(a.getHours()!=0) resultant+=a.getHours()+":";
        resultant+=a.getMinutes()+":";
        resultant+=a.getSeconds();
        return resultant;
    }
}
