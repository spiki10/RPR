package org.example.lab8z2.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.lab8z2.model.Predmet;
import org.example.lab8z2.model.PredmetModel;
import org.example.lab8z2.view.PredmetView;

import java.io.IOException;

public class PredmetController{
    private PredmetModel predmetModel;
    private PredmetView view;

    @FXML
    private TextField nazivPredmetaInput;

    @FXML
    private TextField brojEctsInput;

    @FXML
    private Label labelVerification;

    @FXML
    private Button btnDodaj;



    public PredmetController(PredmetModel predmetModel, PredmetView view){
        this.predmetModel = predmetModel;
        this.view = view;
    }

    public void dodajPredmet(Predmet predmet){
        this.predmetModel.addPredmet(predmet);
        view.setPoruka("Osoba je uspjesno dodana!");
    }

    public Predmet dajPredmetPoID(Integer inID){
        Predmet find = predmetModel.dajPredmetPoID(inID);
        if(find == null){
            view.setPoruka("Predmet nije pronadjen!");
        } else{
            view.setPoruka("Predmet pronadjen: " + find.getNaziv());
        }
        return find;
    }

    public void obrisiPredmet(Integer inputID){
        predmetModel.brisiPredmetID(inputID);
        view.setPoruka("Predmet obrisan!");
    }

    public void napuniPodatke(){
        predmetModel.napuni();
        view.setPoruka("Podaci su uspjesno ucitani!");
    }

    public void napuniPodatkeIzTxt(String filePath){
        predmetModel.ucitajIzTxtFajla(filePath);
        view.setPoruka("Podaci su uspjesno ucitani iz txt fajla!");
    }


    public void azurirajPredmet(Integer inID, Predmet predmet){
        predmetModel.azurirajPredmet(inID, predmet);
        view.setPoruka("Predmet azuriran!");
    }

    @FXML
    public void initiazlie(){
        btnDodaj.setOnAction(e -> btnClick());
    }

    @FXML
    private void btnClick() {
        labelVerification.setText("");
        labelVerification.getStyleClass().removeAll("error", "success");
        try {
            String naziv = nazivPredmetaInput.getText();
            Double ects = Double.parseDouble(brojEctsInput.getText());
            Predmet predmet = new Predmet(naziv, ects);
            dodajPredmet(predmet);
            brojEctsInput.clear();
            nazivPredmetaInput.clear();
            labelVerification.setText("Predmet uspješno dodan!");
            labelVerification.getStyleClass().add("success");
            predmetModel.getPredmeti().forEach(System.out::println);
            System.out.println("");
        } catch (IllegalArgumentException e) {
            labelVerification.getStyleClass().add("error");
            labelVerification.setText("Unesite ispravne podatke!");
            brojEctsInput.clear();
            nazivPredmetaInput.clear();
        }
    }




}