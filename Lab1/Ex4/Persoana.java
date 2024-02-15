public class Persoana {
    private String nume;
    private String prenume;
    private int varsta;

    public Persoana(String nume, String prenume, int varsta)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
    }

    public String getNume()
    {
        return this.nume;
    }
    public String getPrenume()
    {
        return this.prenume;
    }

    public int getVarsta()
    {
        return this.varsta;
    }

}
