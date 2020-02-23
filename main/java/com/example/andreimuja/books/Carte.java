package com.example.andreimuja.books;

import java.util.Calendar;

enum Aspect{
    nou,deteriorat
}

public class Carte {
    String autor;
    String titlu;
    String gen;
    double pret;
    Aspect aspect;
    Calendar dataPublicarii;

    public Carte() { }

    public Carte(String autor, String titlu, String gen, double pret, Aspect aspect, Calendar dataPublicarii) {
        this.autor = autor;
        this.titlu = titlu;
        this.gen = gen;
        this.pret = pret;
        this.aspect = aspect;
        this.dataPublicarii = dataPublicarii;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public Aspect getAspect() {
        return aspect;
    }

    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }

    public Calendar getDataPublicarii() {
        return dataPublicarii;
    }

    public void setDataPublicarii(Calendar dataPublicarii) {
        this.dataPublicarii = dataPublicarii;
    }

    @Override
    public String toString() {
        return "autor: " + autor + ", titlu: " + titlu + ", gen: " + gen;
    }
}

