package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Entity;

import java.util.ArrayList;

public class Colonie implements Entity {
    private Fourmi[] population;
    private int taille;
    private Pos pNid;
    private int quantiteNid;
    private ShaderProgram shaderProgram;
    private Zone zone;

    public Colonie(int taille, Pos pNid, ShaderProgram shaderProgram, Zone zone) {
        this.taille = taille;
        this.pNid = pNid;
        this.population = new Fourmi[taille];
        this.zone = zone;
        this.shaderProgram = shaderProgram;
        for(int i = 0; i < taille; i++){
            this.population[i] = new Fourmi(pNid, this, shaderProgram);
        }
    }
    public void render(){
        for(Fourmi f: population) {
            f.render();
        }
    }

    @Override
    public void cleanup() {
        for (Fourmi f: population) {
            f.cleanup();
        }
    }

    @Override
    public void update() {
        for(Fourmi f: population) {
            f.update();
        }
    }

    private void bouge() {
        for(Fourmi f: population) {
            f.bouge();
        }
    }
    public void bouge(int n) {
        for(int i = 0; i < n; i++)
            bouge();
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

    public Zone getZone() {
        return this.zone;
    }

}
