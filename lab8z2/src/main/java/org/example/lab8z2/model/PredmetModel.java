package org.example.lab8z2.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.lab8z2.model.Predmet;

public class PredmetModel {
    private ObservableList<Predmet> predmeti;

    public PredmetModel() {
        this.predmeti = FXCollections.observableArrayList();
    }

    public void addPredmet(Predmet predmet) {
        this.predmeti.add(predmet);
    }

    public String brisiPredmetID(Integer inID){
        return this.predmeti.removeIf(predmet -> predmet.getId().equals(inID)) ? "Predmet obrisan" : "Predmet nije pronađen";
    }

    public Predmet dajPredmetPoID(Integer inID){
        for(Predmet predmet : this.predmeti){
            if(predmet.getId().equals(inID)){
                return predmet;
            }
        }
        return null;
    }

    public String azurirajPredmet(Integer inID, Predmet predmet){
        for(Predmet p : this.predmeti){
            if(p.getId().equals(inID)){
                p.setNaziv(predmet.getNaziv());
                p.setEcts(predmet.getEcts());
                return "Predmet azuriran";
            }
        }
        return "Predmet nije pronadjen";
    }

    public ObservableList<Predmet> getPredmeti() {
        return predmeti;
    }

    public void napuni(){
        this.predmeti.add(new Predmet("Razvoj softvera",  6.0));
        this.predmeti.add(new Predmet("Baze podataka", 6.0));
        this.predmeti.add(new Predmet("Operativni sistemi", 6.0));
        this.predmeti.add(new Predmet("Web programiranje", 6.0));
        this.predmeti.add(new Predmet("Mreze racunara", 6.0));
    }

    public void ucitajIzTxtFajla(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    String naziv = values[0].trim();
                    Integer id = Integer.parseInt(values[1].trim());
                    Double ects = Double.parseDouble(values[2].trim());
                    this.predmeti.add(new Predmet(naziv, ects));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}