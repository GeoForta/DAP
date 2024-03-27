package com.example.ex4;

public class Persoane {
    private String nume;
    private String prenume;
    private int scor;

    private int probleme;


    public Persoane(String nume, String prenume, int scor, int probleme)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.scor = scor;
        this.probleme = probleme;
    }

    public Persoane()
    {

    }


    public void setNume(String nume)
    {
        this.nume = nume;
    }
    public void setPrenume(String prenume)
    {
        this.prenume = prenume;
    }

    public void setScor(int scor)
    {
        this.scor = scor;
    }

    public void setProbleme(int probleme)
    {
        this.probleme = probleme;
    }

    public String getNume()
    {
        return nume;
    }

    public String getPrenume()
    {
        return prenume;
    }

    public int getScor()
    {
        return scor;
    }

    public int getProbleme()
    {
        return probleme;
    }
}
