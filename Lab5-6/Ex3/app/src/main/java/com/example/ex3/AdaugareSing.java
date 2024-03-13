package com.example.ex3;

import com.example.ex3.Persoana;

import java.util.ArrayList;
import java.util.List;

public class AdaugareSing {
    private static AdaugareSing instanta;
    private List<Persoana> persoane;

    private AdaugareSing() {
        persoane = new ArrayList<>();
    }

    public static AdaugareSing getInstance() {
        if (instanta == null) {
            instanta = new AdaugareSing();
        }
        return instanta;
    }

    public List<Persoana> getPersoaneList() {
        return persoane;
    }
    public void adaugaPersoana(Persoana persoana) {
        persoane.add(persoana);
    }

}