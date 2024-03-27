package com.example.proiect;

public class Contacte {

    private String nume;
    private String prenume;
    private String numarTelefon;

    public Contacte(String nume, String prenume, String numarTelefon)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.numarTelefon = numarTelefon;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public void setPrenume(String prenume)
    {
        this.prenume = prenume;
    }
    public void setNumarTelefon(String numarTelefon)
    {
        this.numarTelefon = numarTelefon;
    }

    public String getNume()
    {
        return nume;
    }
    public String getPrenume()
    {
        return prenume;
    }

    public String getNumarTelefon()
    {
        return numarTelefon;
    }
}
