package com.mahal.simulation;

public class Colonie {
    private Fourmi[] population;
    private int taille;
    private Pos pNid;
    private int quantiteNid;

    public Colonie(int taille, Pos pNid) {
        this.taille = taille;
        this.pNid = pNid;
        this.population = new Fourmi[taille];
        for(int i =0; i < taille; i++){
            this.population[i] = new Fourmi(pNid);
        }
    }
    private void bouge() {
        //TODO: implementation
    }
    public Fourmi[] getPopulation() {
        return population;
    }

    public void setPopulation(Fourmi[] population) {
        this.population = population;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Pos getpNid() {
        return pNid;
    }

    public void setpNid(Pos pNid) {
        this.pNid = pNid;
    }

    public int getQuantiteNid() {
        return quantiteNid;
    }

    public void setQuantiteNid(int quantiteNid) {
        this.quantiteNid = quantiteNid;
    }
}
