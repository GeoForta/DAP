package com.example.ex3;

public class Persoana {
    static String nume;
    static String prenume;
    static String telefon;
    static String adresa;

    public Persoana(String nume, String prenume, String telefon, String adresa) {
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
        this.adresa = adresa;
    }

    public static String getNume()
    {
        return nume;
    }
    public static String getPrenume()
    {
        return prenume;
    }
    public static String getAdresa()
    {
        return adresa;
    }
    public static String getTelefon()
    {
        return telefon;
    }

}
