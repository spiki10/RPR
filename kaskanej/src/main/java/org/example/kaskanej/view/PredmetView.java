package org.example.kaskanej.view;

import org.example.kaskanej.model.Predmet;
import java.util.Scanner;

public class PredmetView {
    private Scanner ulaz = new Scanner(System.in);


    public String getNaziv(){
        System.out.println("Unesite naziv predmeta: ");
        return ulaz.nextLine();
    }

    public double getECTS(){
        System.out.println("Unesite ECTS bodove: ");
        return ulaz.nextDouble();
    }

    public void prikaziPredmet(Predmet view){
        System.out.println("Naziv predmeta: " + view.getNaziv());
        System.out.println("ECTS bodovi: " + view.getECTS());
    }


}
