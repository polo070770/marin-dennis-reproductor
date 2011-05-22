package edu.ub.prog2.QuitaquisTamayDennis.model;

/**
 * Classe que heretara de la classe LlistaReproduccio. Aquesta classe guardarà
 * en una ArrayList tots el fitxers audio que es vulgui reproduir.
 * @author Maikel
 */
public class CuaReproduccio extends LlistaReproduccio {

    //Constructor
    public CuaReproduccio() {

        super("Cua Reproduccio");

    }

    //Metodes
    public String getRutaCompleta(int pos) {

        return super.getLlistaAudio().get(pos).getRutaNomExtensio();

    }

    public String getNomExtensioAudio(int pos) {

        return super.getLlistaAudio().get(pos).getNomExtensio();

    }

    /**
     * Funcio que desordena la cua de reproducció
     */
    public void shuffle() {
        System.out.println("fem un aleatori");
        for (int i = super.getLlistaAudio().size() - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            FitxerAudio temp = super.getLlistaAudio().get(i);
            super.getLlistaAudio().set(i, super.getLlistaAudio().get(rand));
            super.getLlistaAudio().set(rand, temp);
        }
    }
}
