/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.MarinVegaJuan.model;

/**
 *
 * @author socrates
 */
public class ColaReproduccio extends LlistaReproduccio {

    private String nom = "Cua de Reproduccio";
    private int filePlaying;

    /**
     * Metode constructor que rep una llista de reproduccio
     * y els parametres que indicaran si es reproduira de forma aleatoria, ciclica
     * @param llista es un objecte del tipus LlistaReproduccio
     */
    public ColaReproduccio(LlistaReproduccio llista) {
        // Fem un crida al metode constructor del pare
        super(llista);
        super.setNom(this.nom);

        this.filePlaying = 1;

        //Agafem la llista, y la retallem fins al numero d'elements de 
        //l'arraylist ocupats
        super.getLlista().trimToSize();


    }

    /**
     * Metode constructor que rep un fitxer d'audio
     * @param fitxer FitxerAudio que s'incorporara directament a la cola de reproduccio
     */
    public ColaReproduccio(FitxerAudio fitxer) {
        super(fitxer);
        super.setNom(this.nom);
        this.filePlaying = 1;


        //Agafem la llista, y la retallem fins al numero d'elements de 
        //l'arraylist ocupats
        super.getLlista().trimToSize();

    }
    
    /**
     * Metode constructor que no rep cap parametre
     */
    public ColaReproduccio(){
        super("Cua de Reproduccio");
        this.filePlaying = 1;
        
        
    }

    /**
     * Metode que afegeix fitxers de audio a la cua de reproduccio
     * @param nova
     * @return
     */
    public String extendreCuaReproduccio(LlistaReproduccio nova) {
        for (FitxerAudio f:nova.getLlista()){
            super.afegirFitxer(f);
        }
        return "S'han afegit els nous elements";
    }

    /**
     * Funcio que desordena la Cola de reproduccio
     */
    public void shuffle() {
        for (int i = super.getLlista().size() - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            FitxerAudio temp = super.getLlista().get(i);
            super.getLlista().set(i, super.getLlista().get(rand));
            super.getLlista().set(rand, temp);
        }
        this.filePlaying = 1;
    }



    /**
     * Metode que retorna el numero d'arxiu que s'esta reproduint
     * @return
     */
    public int getPlaying() {
        return filePlaying;
    }


    /**
     * Metode que va al seguent fitxer, si l'actual va al primer fa un reloop
     * i retorna la direccio del mateix
     */
    public String nextFile() {
        return super.getFitxer(this.filePlaying).getCompletePath();
    }

    /**
     * Metode que va al seguent fitxer, si el actual es el primer
     * i retorna la direccio del mateix
     * salta a l'ultim
     */
    public String prevFile() {
        if (!(this.filePlaying == 1)) {
            this.filePlaying--;
        } else {
            this.filePlaying = super.getNumFitxers();
        }

        return super.getFitxer(this.filePlaying).getCompletePath();
    }

    /**
     * Metode que posa el fitxer actual a 0
     */
    public void reloop() {
        this.filePlaying = 1;
    }

    /**
     * Metode que retorna la direccio del fitxer actual
     */
    public String getPathFile() {
        return super.getFitxer(this.filePlaying).getCompletePath();

    }

    /**
     * Funcio que retorna les dades del fitxer actual com a string, pero en un format
     * mes curt que el toString()
     * @return
     */
    public String getInfoFitxerActual(){
        return super.getFitxer(filePlaying).shortToString();
    }
    /**
     * Afgeix el fitxer a la llista. S
     */
 
    public void afegirFitxerALlista(FitxerAudio nouFitxer){
        super.afegirFitxer(nouFitxer);

    }
    
    /**
     * substitueix la llista que esta sonant per una nova
     * @param llista
     */
    public void addLlista(LlistaReproduccio llista){
        llista.setNom(nom);
        super.setLlista(llista);
        reloop();
        
    }
    /**
     * Funcio que retorna true si la reproduccio pot continuar, vol dir si es pot
     * reproduir o no el seguent arxiu,
     * Te en compte el parametre loop d'entrada, que es un boolea
     * @param loop boolean
     * @return
     */
    public boolean canContinue(boolean loop){
        if (super.getNumFitxers() == this.filePlaying) {
            if (loop){
                reloop();
                return true;
            }else{
                return false;
            }
            
        } else {
            this.filePlaying++;
            return true;
        }
    }

    public void initFilePlaying() {
        this.filePlaying = 0;
    }
}
