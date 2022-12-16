package lezione1_dav.model;

import java.time.LocalDate;

public class Leone extends Animale{


    private double lunghezza_coda;


    public double getLunghezza_coda() {
        return lunghezza_coda;
    }

    public void setLunghezza_coda(double lunghezza_coda) {
        this.lunghezza_coda = lunghezza_coda;
    }

    public Leone(String nome, String cibo_preferito, int eta, LocalDate data_entrata, double peso, double altezza, double lunghezza_coda) {
        super(nome, cibo_preferito, eta, data_entrata, peso, altezza);
        this.lunghezza_coda = lunghezza_coda;
    }
}