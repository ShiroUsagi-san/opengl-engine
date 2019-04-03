package com.mahal.simulation;

import com.mahal.graphics.utils.Color;

import java.util.Random;

public class Fourmi {
    private Pos position;
    private boolean estCharge;
    private Color color;

    private final static Color FOURMI_CHARGEE = Color.MAGENTA;
    private final static Color FOURMI_VIDE    = Color.RED;

    private Colonie colonie;

    public Fourmi(Pos position, Colonie colonie) {
        this.position = position;
        this.colonie = colonie;
        this.estCharge = false;
        this.color = FOURMI_VIDE;
    }

    public Fourmi(Colonie colonie) {
        this.colonie = colonie;
        this.color = FOURMI_VIDE;
    }

    public Fourmi(Pos position) {
        this.position = position;
        this.color = FOURMI_VIDE;
    }
    public void render(){

    }
    public Fourmi() {
        Random rand = new Random();
        this.position = new Pos(rand.nextInt(Fourmiliere.DIM + 1), rand.nextInt(Fourmiliere.DIM + 1));
        this.color = FOURMI_VIDE;
    }
    public void bouge(){

    }
    @Override
    public String toString() {
        return "Fourmi{" +
                "position=" + position +
                ", estCharge=" + estCharge +
                ", color=" + color +
                ", colonie=" + colonie +
                '}';
    }
}
