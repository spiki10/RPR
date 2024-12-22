package org.example.kaskanej.controller;

import org.example.kaskanej.view.PredmetView;
import org.example.kaskanej.model.Predmet;

public class PredmetController {
    private Predmet model;
    private PredmetView view;

    public PredmetController(Predmet model, PredmetView view){
        this.model = model;
        this.view = view;
    }

    public void setNazivPredmeta(){
        model.setNaziv(view.getNaziv());
    }

    public void setECTSPredmeta(){
        model.setECTS(view.getECTS());
    }

    public void updateView(){
        view.prikaziPredmet(model);
    }
}
