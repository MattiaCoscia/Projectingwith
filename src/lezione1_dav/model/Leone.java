package lezione1_dav.model;

import java.time.LocalDate;

public class Leone extends AnimaliConLaCoda{


    public Leone(String nome, String cibo_preferito, int eta, LocalDate data_entrata, double peso, double altezza, double lunghezza_coda) {
        super(nome, cibo_preferito, eta, data_entrata, peso, altezza, lunghezza_coda);
    }
}