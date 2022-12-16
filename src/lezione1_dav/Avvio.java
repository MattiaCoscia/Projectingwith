package lezione1_dav;

import lezione1_dav.controller.ZooOperationController;
import lezione1_dav.model.AnimaliConLaCoda;
import lezione1_dav.model.Aquila;
import lezione1_dav.model.Leone;
import lezione1_dav.model.Tigre;

import java.time.LocalDate;

public class Avvio {
    public static void main(String[] args) {

        System.out.println("OPERAZIONI RICHIESTE DAVIDE");
        ZooOperationController zooOperationController=new ZooOperationController();
        Leone leone1=new Leone("Alibabba","sugar",19, LocalDate.now(),70.0,1.50,0.50);
        Leone leone2=new Leone("Azvegna","carne",15, LocalDate.now(),67.0,1.60,0.70);

        Tigre tigre1=new Tigre("pippo","tartufo",21,LocalDate.now(),55.0,1.70,1.10);
        Tigre tigre2=new Tigre("dada","fungo",19,LocalDate.now(),54.0,1.90,0.10);

        Aquila aquila1=new Aquila("gino","umano",32,LocalDate.now(),23.0,0.70,2.10);
        Aquila aquila2=new Aquila("egigio","verme",45,LocalDate.now(),33.0,1.00,2.20);

        zooOperationController.aggiungiAnimale(leone1);
        zooOperationController.aggiungiAnimale(leone2);
        zooOperationController.aggiungiAnimale(tigre1);
        zooOperationController.aggiungiAnimale(tigre2);
        zooOperationController.aggiungiAnimale(aquila1);
        zooOperationController.aggiungiAnimale(aquila2);


        System.out.println("Altezza maggiore");
        Leone leonepiualto=zooOperationController.getLeonepiualto();
        System.out.println("Il leone più alto della specie è : "+leonepiualto.getNome());
        Tigre tigrepiualta=zooOperationController.getTigrepiualta();
        System.out.println("La tigre più alta della specie è : "+tigrepiualta.getNome());
        Aquila aquilapiualta=zooOperationController.getAquilapiualta();
        System.out.println("L'aquila più alta della specie è : "+aquilapiualta.getNome());
        System.out.println("Altezza minore");
        Leone leonepiubasso=zooOperationController.getLeonepiubasso();
        System.out.println("Il leone più basso della specie è : "+leonepiubasso.getNome());
        Tigre tigrepiubassa=zooOperationController.getTigrepiubassa();
        System.out.println("La tigre più bassa della specie è : "+tigrepiubassa.getNome());
        Aquila aquilapiubassa=zooOperationController.getAquilapiubassa();
        System.out.println("IL'aquila più bassa della specie è : "+aquilapiubassa.getNome());
        System.out.println("Peso maggiore");
        Leone leonepiupesante=zooOperationController.getLeonepiupesante();
        System.out.println("Il leone più pesante della specie è : "+leonepiupesante.getNome());
        Aquila aquilapiupesante=zooOperationController.getAquilapiupesante();
        System.out.println("L'aquila più pesante della specie è : "+aquilapiupesante.getNome());
        Tigre tigrepiupesante=zooOperationController.getTigrepiupesante();
        System.out.println("La tigre più pesante della specie è : "+tigrepiupesante.getNome());
        System.out.println("Peso minore");
        Leone leonepiuleggero=zooOperationController.getLeonepiuleggero();
        System.out.println("Il leone più leggero della specie è : "+leonepiuleggero.getNome());
        Tigre tigrepiuleggera=zooOperationController.getTigrepiuleggera();
        System.out.println("La tigre più leggera della specie è : "+tigrepiuleggera.getNome());
        Aquila aquilapiuleggera=zooOperationController.getAquilapiuleggera();
        System.out.println("L'aquila più leggera della specie è : "+aquilapiuleggera.getNome());
        System.out.println("Animali con la coda piu lunga");
        AnimaliConLaCoda animaliConLaCoda=zooOperationController.getAnimaliconlacodapiulunga();
        System.out.println("L'animale con la coda più lunga è : "+animaliConLaCoda.getNome());


    }
}
