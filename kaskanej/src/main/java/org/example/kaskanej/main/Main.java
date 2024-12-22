package org.example.kaskanej.main;
import org.example.kaskanej.controller.OsobaController;
import org.example.kaskanej.model.Osoba;
import org.example.kaskanej.view.OsobaView;
import org.example.kaskanej.model.Uloga;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Osoba osoba = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        System.out.println("Osoba ima pravo na stipendiju: " + osoba.imaPravoNaStipendiju());

        OsobaView osobaView = new OsobaView();
        osobaView.setUlazniTekst("Novo ime");
        OsobaController osobaController = new OsobaController(osoba, osobaView);
        osobaController.azurirajIme();
        System.out.println("1) View ispisuje: " + osobaView.getPoruka());

        osobaController.dajOsobeIzTxtDatoteke("src/main/java/org/example/kaskanej/data/osobe.txt");
        System.out.println("2) View ispisuje: " + osobaView.getPoruka());

        osobaController.dajOsobeIzXmlDatoteke("src/main/java/org/example/kaskanej/data/osobe.xml");
        System.out.println("3) View ispisuje: " + osobaView.getPoruka());

    }
}
