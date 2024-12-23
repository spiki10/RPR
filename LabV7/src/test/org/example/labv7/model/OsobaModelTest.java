package org.example.labv7.model;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OsobaModelTest {
    private OsobaModel modelTest;
    @BeforeEach
    public void setUp(){
        modelTest = new OsobaModel();
    }

    @Test
    void napuni() {
        modelTest.napuni();
        assertFalse(modelTest.dajSveOsobe().isEmpty());
    }

    @Test
    void napuniProvjera(){
        modelTest.napuni();
        assertEquals(2, modelTest.dajSveOsobe().size());
        assertEquals(Uloga.STUDENT, modelTest.dajSveOsobe().get(0).getUloga());
    }


    @Test
    void testAzurirajOsobuIDPostoji(){
        String result = modelTest.dodajOsobu(5, "Ime", "Prezime", "Adresa", new Date(94,0,10), "1001994000000", Uloga.STUDENT);
        assertEquals("Osoba je uspjesno dodana!", result);
        assertEquals("Osoba je uspjesno azurirana!", modelTest.azurirajOsobu(5,"Ibro","Carin","Sarajevo",new Date(94,0,10),"1001994000000",Uloga.PROFESOR));
    }

    @Test
    void testAzurirajOsobuIDNePostoji(){
        assertEquals("Osoba nije pronadjena!", modelTest.azurirajOsobu(46464,"Ibro","Carin","Sarajevo",new Date(94,0,10),"1001994000000",Uloga.PROFESOR));
    }

    @Test
    void testAzurirajOsobuNekaPoljaFale(){
        assertEquals("Osoba nije pronadjena!", modelTest.azurirajOsobu(5,"Ibro",null,null,new Date(),"1234567891234",Uloga.PROFESOR));
    }










}