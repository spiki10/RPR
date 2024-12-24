package org.example.lab8.controller;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.lab8.model.OsobaModel;
import org.example.lab8.model.Uloga;
import org.example.lab8.view.OsobaView;
import org.example.lab8.model.Osoba;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class OsobaController{
    private OsobaModel model;
    private OsobaView view;

    @FXML
    private Label ucitavanjeLabel;
    @FXML
    private ListView<Osoba> osobeListView;
    @FXML
    private TextField imeField;
    @FXML
    private TextField prezimeField;
    @FXML
    private TextField adresaField;
    @FXML
    private DatePicker datumRodjenjaPicker;
    @FXML
    private TextField maticniBrojField;
    @FXML
    private ChoiceBox<Uloga> ulogaChoiceBox;
    @FXML
    private Button azurirajOsobuButton;
    @FXML
    private Label porukaLabel;

    private ObservableList<Osoba> osobeObservableList = FXCollections.observableArrayList();

    private ObjectProperty<Osoba> izabranaOsoba = new SimpleObjectProperty<>();

    public OsobaController(OsobaModel model) {
        this.model = model;
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

    private void azurirajOsobu() {
        if(izabranaOsoba != null) {
            // procitaj sadrzaj input polja
            String ime = imeField.getText();
            String prezime = prezimeField.getText();
            String adresa = adresaField.getText();
            LocalDate datumRodjenjaLocal = datumRodjenjaPicker.getValue();
            String maticniBroj = maticniBrojField.getText();
            Uloga uloga = ulogaChoiceBox.getValue();
            // validacija polja forme
            if (ime.isEmpty() || prezime.isEmpty() || adresa.isEmpty() || maticniBroj.isEmpty() || datumRodjenjaLocal==null || uloga==null) {
                porukaLabel.setVisible(true);
                porukaLabel.setText("Sva polja moraju biti popunjena!");
                return;
            }
            Date datumRodjenja = Date.from(datumRodjenjaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
            String poruka = model.azurirajOsobu(izabranaOsoba.get().getId(), ime, prezime, adresa, datumRodjenja, maticniBroj, uloga);
            porukaLabel.setVisible(true);
            porukaLabel.setText(poruka);
            osobeListView.refresh();
        }
    }


    @FXML
    public void initialize() {
        model.napuni();

        ucitavanjeLabel.setText("Ucitani podaci");
        ucitavanjeLabel.setStyle("-fx-background-color: green;");

        azurirajOsobuButton.setText("Azuriraj");

        ulogaChoiceBox.getItems().addAll(Uloga.STUDENT, Uloga.NASTAVNO_OSOBLJE);

        osobeObservableList.addAll(model.dajSveOsobe());
        osobeListView.setItems(osobeObservableList);

        azurirajOsobuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                azurirajOsobu();
            }});

        osobeListView.getSelectionModel().selectedItemProperty().addListener((observable, staraVrijednost, novaVrijednost) -> {
            if (novaVrijednost != null) {
                // azuriranje varijable koja predstavlja trenutno izabranu osobu
                izabranaOsoba.setValue(novaVrijednost);
                // ispunjavanje polja detaljima izabrane osobe
                // ispuniPolja(novaVrijednost);

                // sakrij labelu koja sadrzi poruku
                porukaLabel.setVisible(false);
            }});

        izabranaOsoba.addListener((observable, oldValue, newValue) -> {
            if(oldValue != null) {
                imeField.textProperty().unbindBidirectional(oldValue.imeProperty());
                prezimeField.textProperty().unbindBidirectional(oldValue.prezimeProperty());
            }
            if(newValue != null) {
                imeField.textProperty().bindBidirectional(newValue.imeProperty());
                prezimeField.textProperty().bindBidirectional(newValue.prezimeProperty());
            }

            /*
            Povezivanje u jednom pravcu
            izabranaOsoba.addListener((observable, oldValue, newValue) -> {
               if(oldValue != null) {
                   imeField.textProperty().unbind();
                   prezimeField.textProperty().unbind();
               }
               if(newValue != null) {
                   imeField.textProperty().bind(newValue.imeProperty());
                   prezimeField.textProperty().bind(newValue.prezimeProperty());
               }
            });
             */
        });

    }



    private void ispuniPolja(Osoba osoba) {
        imeField.setText(osoba.getIme());
        prezimeField.setText(osoba.getPrezime());
        adresaField.setText(osoba.getAdresa());
        datumRodjenjaPicker.setValue(osoba.getDatumRodjenja().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        maticniBrojField.setText(osoba.getMaticniBroj());
        ulogaChoiceBox.setValue(osoba.getUloga());
    }








}