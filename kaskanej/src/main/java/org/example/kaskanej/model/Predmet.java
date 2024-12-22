package org.example.kaskanej.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Predmet {
    private String naziv;
    private Double ECTS;

    public Predmet(String naziv, Double ECTS) {
        this.naziv = naziv;
        this.ECTS = ECTS;
    }

    public void setNaziv(String naziv) throws IllegalArgumentException {
        if(naziv == null || naziv.isEmpty()) {
            throw new IllegalArgumentException("Naziv predmeta ne moze biti prazan!");
        }
        if(naziv.length()<5 || naziv.length()>50) {
            throw new IllegalArgumentException("Naziv predmeta mora imati između 5 i 50 karaktera!");
        }
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public Double getECTS() {
        return ECTS;
    }

    public void setECTS(Double input){
        Double provjera = Math.floor(input*100)%100;
        if (provjera != 5 && provjera != 0) {
            throw new IllegalArgumentException("Ects bodovi moraju zavrsavati sa 0.5 ili 0.0!");
        }
        if(input<5.0 || input>20.0){
            throw new IllegalArgumentException("Ects bodovi moraju biti između 5 i 20!");
        }
        this.ECTS = input;
    }

    @Override
    public String toString(){
        return "Predmet: " + naziv + ", ECTS: " + ECTS;
    }

    // Loader je isti kao za Osoba samo drugaciji path
    public static List<Predmet> ucitajPredmeteIzTxtDatoteke(String putanjaDoDatoteke) throws IOException, ParseException, ParseException {
        File file = new File(putanjaDoDatoteke);
        if (!file.exists()) {
            throw new IOException("Greska: Datoteka ne postoji!");
        }
        List<Predmet> predmeti = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(putanjaDoDatoteke));
        String linija;
        while ((linija = reader.readLine()) != null) {
            String[] polja = linija.split(",");
            if (polja.length == 7) {
                String name = polja[0];
                Double broj = Double.parseDouble(polja[1]);
                Predmet predmet = new Predmet(name, broj);
                predmeti.add(predmet);
            }
        }
        reader.close();

        return predmeti;
    }




}
