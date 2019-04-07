package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import org.joml.Quaternionf;

import java.util.Random;

public class Fourmi implements Entity {
    private Pos position;
    private boolean estCharge;
    private Color color;
    private Quaternionf rot = new Quaternionf();
    private final static Color FOURMI_CHARGEE = Color.MAGENTA;
    private final static Color FOURMI_VIDE    = Color.RED;
    private Drawable fourmiEntity;
    private ShaderProgram shaderProgram;
    private Colonie colonie;

    public Fourmi(Pos position, Colonie colonie, ShaderProgram shaderProgram) {
        this.position = position;
        this.colonie = colonie;
        this.estCharge = false;
        this.color = FOURMI_VIDE;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10, this.color);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position.getPosition(), 1);
        this.shaderProgram = shaderProgram;
    }


    public Fourmi(Colonie colonie, ShaderProgram shaderProgram) {
        this.colonie = colonie;
        this.color = FOURMI_VIDE;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10, this.color);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position.getPosition(), 1);
        this.shaderProgram = shaderProgram;
    }

    public Fourmi(Pos position, ShaderProgram shaderProgram) {
        this.position = position;
        this.color = FOURMI_VIDE;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10, this.color);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position.getPosition(), 1);
        this.shaderProgram = shaderProgram;
    }
    public void render(){
        this.fourmiEntity.predraw(this.shaderProgram);
        this.fourmiEntity.draw();
    }

    @Override
    public void cleanup() {
        this.fourmiEntity.cleanup();
    }

    public void prend() {

    }
    public Fourmi() {
        Random rand = new Random();
        this.position = new Pos(rand.nextInt(Fourmiliere.DIM + 1), rand.nextInt(Fourmiliere.DIM + 1));
        this.color = FOURMI_VIDE;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10, this.color);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position.getPosition(), 1);

    }
    public void bouge(){

    }
    public String toString() {
        return "position: " + this.position + " colonie: " + this.colonie;
    }
}
