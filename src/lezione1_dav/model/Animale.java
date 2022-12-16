package lezione1_dav.model;

import java.time.LocalDate;

public class Animale {
    private String nome;
    private String cibo_preferito;
    private int eta;
    private LocalDate data_entrata;
    private double peso;
    private double altezza;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCibo_preferito() {
        return cibo_preferito;
    }

    public void setCibo_preferito(String cibo_preferito) {
        this.cibo_preferito = cibo_preferito;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public LocalDate getData_entrata() {
        return data_entrata;
    }

    public void setData_entrata(LocalDate data_entrata) {
        this.data_entrata = data_entrata;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }
    public Animale(){

    }

    public Animale(String nome, String cibo_preferito, int eta, LocalDate data_entrata, double peso, double altezza) {
        this.nome = nome;
        this.cibo_preferito = cibo_preferito;
        this.eta = eta;
        this.data_entrata = data_entrata;
        this.peso = peso;
        this.altezza = altezza;
    }
}
