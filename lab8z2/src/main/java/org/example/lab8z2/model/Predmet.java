package org.example.lab8z2.model;

public class Predmet {
    private String naziv;
    private static int nextId = 1;
    private Double ects;
    private int id;


    public Predmet(String naziv, Double ects) {
        this.id = nextId++;
        this.setNaziv(naziv);
        this.setEcts(ects);
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        if(naziv.length()<5 || naziv.length()>100){
            throw new IllegalArgumentException("Naziv predmeta mora imati između 5 i 100 karaktera");
        }
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getEcts() {
        return ects;
    }

    public void setEcts(Double ects) {
        if(ects<5 || ects>20){
            throw new IllegalArgumentException("ECTS bodovi moraju biti između 0 i 20");
        }
        if(ects%0.5!=0){
            throw new IllegalArgumentException("ECTS bodovi moraju biti cijeli broj ili pola broja");
        }
        this.ects = ects;
    }


    @Override
    public String toString(){
        return id + ", " + naziv + ", " + ects;
    }


}