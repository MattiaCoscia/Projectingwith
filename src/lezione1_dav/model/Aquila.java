package lezione1_dav.model;

import java.time.LocalDate;

public class Aquila extends Animale{

        private double larghezza_alare;


        public double getLarghezza_alare() {
                return larghezza_alare;
        }

        public void setLarghezza_alare(double larghezza_alare) {
                this.larghezza_alare = larghezza_alare;
        }


        public Aquila(String nome, String cibo_preferito, int eta, LocalDate data_entrata, double peso, double altezza, double larghezza_alare) {
                super(nome, cibo_preferito, eta, data_entrata, peso, altezza);
                this.larghezza_alare = larghezza_alare;
        }
}