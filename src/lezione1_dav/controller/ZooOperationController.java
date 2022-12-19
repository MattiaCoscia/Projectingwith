package lezione1_dav.controller;

import lezione1_dav.model.*;

import java.util.ArrayList;

public class ZooOperationController {

    ArrayList<Aquila>aquile;
    ArrayList<Tigre>tigri;
    ArrayList<Leone>leoni;



    public Leone getLeonepiualto(){
        if(leoni.isEmpty()){
            return null;
        }
        Leone leonepiualto=leoni.get(0);
        for(Leone attualion : leoni){
            if(attualion.getAltezza()>leonepiualto.getAltezza()){
                leonepiualto=attualion;}
        }
        return leonepiualto;
    }
    public Tigre getTigrepiualta(){
        if(tigri.isEmpty()){
            return null;
        }
        Tigre tigrepiualta=tigri.get(0);
        for(Tigre attualtiger : tigri){
            if(attualtiger.getAltezza()>tigrepiualta.getAltezza()){
                tigrepiualta=attualtiger;}
        }
        return tigrepiualta;
    }

    public Aquila getAquilapiualta(){
        if(aquile.isEmpty()){
            return null;
        }
        Aquila aquilapiualta=aquile.get(0);
        for(Aquila attualeagle : aquile){
            if(attualeagle.getAltezza()>aquilapiualta.getAltezza()){
                aquilapiualta=attualeagle;}
        }
        return aquilapiualta;
    }

    public Leone getLeonepiubasso(){
        if(leoni.isEmpty()){
            return null;
        }
        Leone leonepiubasso=leoni.get(0);
        for(Leone attualion : leoni){
            if(attualion.getAltezza()<leonepiubasso.getAltezza()){
                leonepiubasso=attualion;}
        }
        return leonepiubasso;
    }
    public Tigre getTigrepiubassa(){
        if(tigri.isEmpty()){
            return null;
        }
        Tigre tigrepiubassa=tigri.get(0);
        for(Tigre attualtiger : tigri){
            if(attualtiger.getAltezza()<tigrepiubassa.getAltezza()){
                tigrepiubassa=attualtiger;}
        }
        return tigrepiubassa;
    }
    public Aquila getAquilapiubassa(){
        if(aquile.isEmpty()){
            return null;
        }
        Aquila aquilapiubassa=aquile.get(0);
        for(Aquila attualeagle : aquile){
            if(attualeagle.getAltezza()<aquilapiubassa.getAltezza()){
                aquilapiubassa=attualeagle;}
        }
        return aquilapiubassa;
    }
    public Leone getLeonepiupesante(){
        if(leoni.isEmpty()){
            return null;
        }
        Leone leonepiupesante=leoni.get(0);
        for(Leone attualion : leoni){
            if(attualion.getPeso()>leonepiupesante.getPeso()){
                leonepiupesante=attualion;}
        }
        return leonepiupesante;
    }
    public Tigre getTigrepiupesante(){
        if(tigri.isEmpty()){
            return null;
        }
        Tigre tigrepiupesante=tigri.get(0);
        for(Tigre attualtiger : tigri){
            if(attualtiger.getPeso()>tigrepiupesante.getPeso()){
                tigrepiupesante=attualtiger;}
        }
        return tigrepiupesante;
    }
    public Aquila getAquilapiupesante(){
        if(aquile.isEmpty()){
            return null;
        }
        Aquila aquilapiupesante=aquile.get(0);
        for(Aquila attualeagle : aquile){
            if(attualeagle.getPeso()>aquilapiupesante.getPeso()){
                aquilapiupesante=attualeagle;}
        }
        return aquilapiupesante;
    }
    public Leone getLeonepiuleggero(){
        if(leoni.isEmpty()){
            return null;
        }
        Leone leonepiuleggero=leoni.get(0);
        for(Leone attualion : leoni){
            if(attualion.getPeso()<leonepiuleggero.getPeso()){
                leonepiuleggero=attualion;}
        }
        return leonepiuleggero;
    }
    public Tigre getTigrepiuleggera(){
        if(tigri.isEmpty()){
            return null;
        }
        Tigre tigrepiuleggera=tigri.get(0);
        for(Tigre attualtiger : tigri){
            if(attualtiger.getPeso()<tigrepiuleggera.getPeso()){
                tigrepiuleggera=attualtiger;}
        }
        return tigrepiuleggera;
    }
    public Aquila getAquilapiuleggera(){
        if(aquile.isEmpty()){
            return null;
        }
        Aquila aquilapiuleggera=aquile.get(0);
        for(Aquila attualeagle : aquile){
            if(attualeagle.getPeso()<aquilapiuleggera.getPeso()){
                aquilapiuleggera=attualeagle;}
        }
        return aquilapiuleggera;
    }

    public AnimaliConLaCoda getAnimaliconlacodapiulunga(){
        ArrayList<AnimaliConLaCoda>animaliConLeCode=new ArrayList<>();
        animaliConLeCode.addAll(leoni);
        animaliConLeCode.addAll(tigri);
        if(animaliConLeCode.isEmpty()){
            return null;
        }
        AnimaliConLaCoda animalicodati=animaliConLeCode.get(0);
        for(AnimaliConLaCoda attualqueue: animaliConLeCode){
            if(attualqueue.getLunghezza_coda()> animalicodati.getLunghezza_coda()){
                animalicodati=attualqueue;
            }
        }
    return animalicodati;
    }

    public Aquila getAquilaConAmpiezzaAlareMaggiore(){
        if(aquile.isEmpty()){
            return null;
        }
        Aquila aquilalarge=aquile.get(0);
        for(Aquila attualeagle : aquile){
            if(attualeagle.getLarghezza_alare()>aquilalarge.getLarghezza_alare()){
                aquilalarge=attualeagle;}
        }
        return aquilalarge;
    }

    public ZooOperationController() {
        this.aquile = new ArrayList<>();
        this.tigri = new ArrayList<>();
        this.leoni = new ArrayList<>();
    }
    public void aggiungiAnimale(Animale animale){
        if(animale instanceof Tigre){
            tigri.add((Tigre)animale);
        } else if (animale instanceof Leone) {
            leoni.add((Leone) animale);
        } else if (animale instanceof Aquila) {
            aquile.add((Aquila) animale);
        }
    }
}
