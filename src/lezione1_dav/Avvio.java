package lezione1_dav;

import lezione1_dav.controller.ZooOperationController;
import lezione1_dav.model.AnimalWithTail;
import lezione1_dav.model.Eagle;
import lezione1_dav.model.Lion;
import lezione1_dav.model.Tiger;

import java.time.LocalDate;

public class Avvio {
    public static void main(String[] args) {

        System.err.println("OPERAZIONI RICHIESTE DAVIDE");
        ZooOperationController zooOperationController=new ZooOperationController();
        Lion lion1 =new Lion("Alibabba","sugar",19, LocalDate.now(),70.0,1.50,0.50);
        Lion lion2 =new Lion("Azvegna","carne",15, LocalDate.now(),67.0,1.60,0.70);
        Lion lion3 =new Lion("Propotamo","zucca",12, LocalDate.now(),689.0,1.00,2.70);

        Tiger tiger1 =new Tiger("Pippo","tartufo",21,LocalDate.now(),55.0,1.70,1.10);
        Tiger tiger2 =new Tiger("Dada","fungo",19,LocalDate.now(),54.0,1.90,0.10);
        Tiger tiger3 =new Tiger("Wasabi","cocaina",29,LocalDate.now(),74.0,1.60,0.20);

        Eagle eagle1 =new Eagle("Gino","umano",32,LocalDate.now(),23.0,0.70,2.10);
        Eagle eagle2 =new Eagle("Egigio","verme",45,LocalDate.now(),33.0,1.00,2.20);
        Eagle eagle3 =new Eagle("Ra","persone",25,LocalDate.now(),34.0,1.10,2.25);

        zooOperationController.aggiungiLeone(lion1);
        zooOperationController.aggiungiLeone(lion2);
        zooOperationController.aggiungiLeone(lion3);
        zooOperationController.aggiungiTigre(tiger1);
        zooOperationController.aggiungiTigre(tiger2);
        zooOperationController.aggiungiTigre(tiger3);
        zooOperationController.aggiungiAquila(eagle1);
        zooOperationController.aggiungiAquila(eagle2);
        zooOperationController.aggiungiAquila(eagle3);


        System.out.println("---------------------------------Altezza maggiore-------------------------------------");
        Lion leonepiualto=zooOperationController.getLeonepiualto();
        System.out.println("Il leone più alto della specie è : "+leonepiualto.getNome());
        Tiger tigrepiualta=zooOperationController.getTigrepiualta();
        System.out.println("La tigre più alta della specie è : "+tigrepiualta.getNome());
        Eagle aquilapiualta=zooOperationController.getAquilapiualta();
        System.out.println("L'aquila più alta della specie è : "+aquilapiualta.getNome());
        System.out.println("----------------------------------Altezza minore---------------------------------------");
        Lion leonepiubasso=zooOperationController.getLeonepiubasso();
        System.out.println("Il leone più basso della specie è : "+leonepiubasso.getNome());
        Tiger tigrepiubassa=zooOperationController.getTigrepiubassa();
        System.out.println("La tigre più bassa della specie è : "+tigrepiubassa.getNome());
        Eagle aquilapiubassa=zooOperationController.getAquilapiubassa();
        System.out.println("IL'aquila più bassa della specie è : "+aquilapiubassa.getNome());
        System.out.println("----------------------------------Peso maggiore-------------------------------------------");
        Lion leonepiupesante=zooOperationController.getLeonepiupesante();
        System.out.println("Il leone più pesante della specie è : "+leonepiupesante.getNome());
        Eagle aquilapiupesante=zooOperationController.getAquilapiupesante();
        System.out.println("L'aquila più pesante della specie è : "+aquilapiupesante.getNome());
        Tiger tigrepiupesante=zooOperationController.getTigrepiupesante();
        System.out.println("La tigre più pesante della specie è : "+tigrepiupesante.getNome());
        System.out.println("-----------------------------------Peso minore----------------------------------------------");
        Lion leonepiuleggero=zooOperationController.getLeonepiuleggero();
        System.out.println("Il leone più leggero della specie è : "+leonepiuleggero.getNome());
        Tiger tigrepiuleggera=zooOperationController.getTigrepiuleggera();
        System.out.println("La tigre più leggera della specie è : "+tigrepiuleggera.getNome());
        Eagle aquilapiuleggera=zooOperationController.getAquilapiuleggera();
        System.out.println("L'aquila più leggera della specie è : "+aquilapiuleggera.getNome());
        System.out.println("--------------------------------Animali con la coda piu lunga---------------------------------");
        AnimalWithTail animalWithTail =zooOperationController.getAnimaliconlacodapiulunga();
        System.out.println("L'animale con la coda più lunga è : "+ animalWithTail.getNome());
        System.out.println("---------------------------------Uccello con l'apertura alare piu ampia-------------------------");
        Eagle aquilalarge=zooOperationController.getAquilaConAmpiezzaAlareMaggiore();
        System.out.println("L'aquila con l'apertura alare più ampia è : "+aquilalarge.getNome());

    }
}
