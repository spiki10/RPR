package org.example.labv7.controller;

import org.example.labv7.model.OsobaModel;
import org.example.labv7.view.OsobaView;
import org.example.labv7.model.Osoba;

import java.io.IOException;
import java.util.List;

public class OsobaController {
    private OsobaModel model;
    private OsobaView view;

    public OsobaController(OsobaModel model, OsobaView view)
    {
        this.model = model;
        this.view = view;
    }


    public Osoba dajOsobuPoId(Integer id){
        Osoba osobe = model.dajOsobuPoId(id);
        if (osobe == null) {
            view.setPoruka("Osoba nije pronadjena");
        } else {
            view.setPoruka("Osoba je pronadjena");
        }
        return osobe;
    }

    public void azurirajIme(Integer id){
        try {
            model.azurirajOsobu(id, view.getUlazniTekst(), null, null, null, null, null);
            view.setPoruka("Ime je uspjesno azurirano!");
        }
        catch(Exception e){
            view.setPoruka("Greska: " + e.getMessage());
        }
    }



    public void dajOsobeIzTxtDatoteke(String filePath) {
        try {
            model.napuniPodatkeIzTxtDatoteke(filePath);
            view.setPoruka("Osobe uspješno učitane iz TXT datoteke.");
        } catch (Exception e) {
            view.setPoruka("Greška pri učitavanju iz TXT datoteke: " + e.getMessage());
        }
    }


    public void dajOsobeIzXmlDatoteke(String filePath) throws IOException {
        try {
            model.napuniPodatkeIzXmlDatoteke(filePath);
            view.setPoruka("Osobe uspješno učitane iz XML datoteke.");
        } catch (Exception e) {
            view.setPoruka("Greška pri učitavanju iz XML datoteke: " + e.getMessage());
        }
    }




}