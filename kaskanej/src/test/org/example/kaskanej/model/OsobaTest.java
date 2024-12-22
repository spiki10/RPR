package org.example.kaskanej.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OsobaTest {

    @Test
    void testImaPravoNaStipendiju() {
        Osoba osoba = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        assertTrue(osoba.imaPravoNaStipendiju());
    }

    @Test
    void testDuzinaMaticnibroj(){
        Osoba osoba = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        assertEquals(13, osoba.getMaticniBroj().length());
    }

    @Test
    void testDuzinaMaticnibrojDuzinaShort(){
        Osoba osoba2 = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            osoba2.setMaticniBroj("1000");
        });
        assertEquals("Maticni broj mora imati tacno 13 karaktera!", exception.getMessage());
    }

    @Test
    void testDuzinaMaticnibrojLong() {
        // The maticniBroj must match the datumRodjenja (10.01.2004)
        // 10 -> Day, 01 -> Month, 004 -> Year adjusted for ProvjeriMaticniBroj logic
        String validMaticniBroj = "1001994124000";

        // Create the Osoba object with the valid maticniBroj
        Osoba osoba3 = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(94, 0, 10), validMaticniBroj, Uloga.STUDENT);

        // Test invalid maticniBroj length
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            osoba3.setMaticniBroj("2509997123456721412231");
        });

        // Verify the exception message
        assertEquals("Maticni broj mora imati tacno 13 karaktera!", exception.getMessage());
    }


    @Test
    void testMaticniBrojNePoklapaSaDatumomRodjenja(){
        Osoba osoba4 = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            osoba4.setMaticniBroj("2609997123457");
        });
        assertEquals("Maticni broj se ne poklapa sa datumom rodjenja!", exception.getMessage());
    }

    @Test
    void testIspravnoKreiranaOsoba(){
        Osoba osoba5 = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        assertEquals(1, osoba5.getId());
        assertEquals("Neko", osoba5.getIme());
        assertEquals("Nekic", osoba5.getPrezime());
        assertEquals("Neka adresa", osoba5.getAdresa());
        assertEquals(new Date(97, 8, 25), osoba5.getDatumRodjenja());
        assertEquals("2509997123456", osoba5.getMaticniBroj());
        assertEquals(Uloga.STUDENT, osoba5.getUloga());
    }

    @Test
    void testIspravnoIme(){
        Osoba osoba6 = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        osoba6.setIme("Neko");
        assertEquals("Neko", osoba6.getIme());
    }

    @Test
    void testNeispravnoIme(){
        Osoba osoba7 = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            osoba7.setIme("");
        });
        assertEquals("Ime mora imati izmedju 2 i 50 znakova.", exception.getMessage());
    }

    @Test
    void testPutanjaDatotekeTxt(){
        Osoba osoba8 = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        Exception exception = assertThrows(IOException.class, ()->{
            osoba8.ucitajOsobeIzTxtDatoteke("src/main/java/org/example/kaskanej/data/osobeRandom.txt");
        });
        assertEquals("Greska: Datoteka ne postoji!", exception.getMessage());
    }

    @Test
    void testPutanjaDatotekeXml(){
        Osoba osoba9 = new Osoba(1, "Neko", "Nekic", "Neka adresa", new Date(97, 8, 25), "2509997123456", Uloga.STUDENT);
        Exception exception = assertThrows(IOException.class, () -> {
           osoba9.ucitajOsobeIzXmlDatoteke("src/main/java/org/example/kaskanej/data/osobeRandom.xml");
        });
        assertEquals("Greska: Datoteka ne postoji!", exception.getMessage());
    }









}