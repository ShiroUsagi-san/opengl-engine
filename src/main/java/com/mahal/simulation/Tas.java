package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;


public class Tas implements Entity {
    private float quantite;
    private final float DIMENSION =  50;
    private  float MAX_QUANTITE;
    private Drawable tas;
    private ShaderProgram shaderProgram;
    private Pos pos;

    public Tas(float quantite, Pos pos, ShaderProgram shaderProgram) {
        this.pos = pos;
        this.quantite = quantite;
        MAX_QUANTITE = quantite;
        this.shaderProgram = shaderProgram;
        Mesh tasMesh = MeshBuilder.buildRect(this.pos.getX(), this.pos.getY(), DIMENSION, DIMENSION);
        this.tas = new Drawable(tasMesh, 0.0f, this.pos.getPosition(), 1, Color.GREY);

    }
    public boolean isIn(Pos p) {
        return (p.getX() >= this.pos.getX() &&
                p.getX() <= this.pos.getX() + DIMENSION * this.tas.getScale())
                && (p.getY() >= this.pos.getY() &&
                p.getY() <= this.pos.getY() + DIMENSION * this.tas.getScale() );
    }
    void diminuer(float q) {
        this.quantite -= q;
        float newScale = this.getQuantite() / MAX_QUANTITE;
        this.tas.setScale(newScale);
    }
    public float getQuantite() {
        return quantite;
    }

    public Pos getPos() {
        return pos;
    }

    @Override
    public void render() {
        this.tas.predraw(this.shaderProgram);
        this.tas.draw();
    }

    @Override
    public void cleanup() {
        this.tas.cleanup();
    }

    @Override
    public void update() {
    }
}
