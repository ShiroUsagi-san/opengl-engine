package com.mahal.simulation;

import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Renderable;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import org.joml.Quaternionf;

import java.util.Random;

public class Fourmi implements Renderable {
    private Pos position;
    private boolean estCharge;
    private Color color;
    private Quaternionf rot = new Quaternionf();
    private final static Color FOURMI_CHARGEE = Color.MAGENTA;
    private final static Color FOURMI_VIDE    = Color.RED;
    private Drawable fourmiEntity;

    private Colonie colonie;

    public Fourmi(Pos position, Colonie colonie) {
        this.position = position;
        this.colonie = colonie;
        this.estCharge = false;
        this.color = FOURMI_VIDE;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10, this.color);
        this.fourmiEntity = new Drawable(fourmiMesh, new Quaternionf(0,0,0,0), position.getPosition(), 1);
    }

    public Fourmi(Colonie colonie) {
        this.colonie = colonie;
        this.color = FOURMI_VIDE;
    }

    public Fourmi(Pos position) {
        this.position = position;
        this.color = FOURMI_VIDE;
    }
    @Override
    public void render() {
        this.fourmiEntity.getMesh().render();
    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void cleanup() {
        this.fourmiEntity.getMesh().cleanUp();
    }


    public void prend() {

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
