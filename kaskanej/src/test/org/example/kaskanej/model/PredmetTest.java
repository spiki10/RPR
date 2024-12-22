package org.example.kaskanej.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PredmetTest {

    @Test
    void setNaziv() {
        Predmet test1 = new Predmet("Random", 6.0);
        test1.setNaziv("Provjera");
        assertEquals("Provjera", test1.getNaziv());
        test1.setNaziv("Ne znam gdje sam");
        assertEquals("Ne znam gdje sam", test1.getNaziv());
        Exception e = assertThrows(IllegalArgumentException.class, ()->{
            test1.setNaziv("");
        });
        assertEquals("Naziv predmeta ne moze biti prazan!",e.getMessage());
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            test1.setNaziv("A");
        });
        assertEquals("Naziv predmeta mora imati između 5 i 50 karaktera!",e1.getMessage());
    }

    @Test
    void setECTS() {
        Predmet test2 = new Predmet("Random", 6.0);
        Predmet test21 = new Predmet("Random",7.0);
        Predmet test3 = new Predmet("random",10.0);
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            test2.setECTS(4.0);
        });
        assertEquals("Ects bodovi moraju biti između 5 i 20!",e.getMessage());
        Exception e2 = assertThrows(IllegalArgumentException.class, ()-> {
            test21.setECTS(5.7);
        });
        assertEquals("Ects bodovi moraju zavrsavati sa 0.5 ili 0.0!",e2.getMessage());
        test3.setECTS(15.0);
        assertEquals(15.0,test3.getECTS());
    }

}