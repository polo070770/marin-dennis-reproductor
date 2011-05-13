package edu.ub.prog2.QuitaquisTamayDennis.vista;

import edu.ub.prog2.QuitaquisTamayDennis.controlador.CtrlReproductor;

public class Reproductor4 {

    private MainGui gui;

    /**
     * Constructor de la classe que inicialitza la classe MainGui
     */
    public Reproductor4() {
        
        gui = new MainGui(new CtrlReproductor());

    }
    
    /**
     * Métode main que inicialita el constructor de la classe
     * @param Args 
     */
    public static void main(String[] Args) {

        Reproductor4 repro = new Reproductor4();
        repro.setVisible();

    }

    /**
     * Métode que fa visible la interface
     */
    public void setVisible() {

        gui.setVisible(true);

    }
}
