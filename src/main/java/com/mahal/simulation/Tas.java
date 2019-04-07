package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import org.joml.Quaternionf;

public class Tas implements Entity {
    private int quantite;
    private final int DIMENSION =  50;
    private Drawable tas;
    private ShaderProgram shaderProgram;
    private Pos pos;

    public Tas(Pos pos, ShaderProgram shaderProgram) {
        this.pos = pos;
        this.shaderProgram = shaderProgram;
        Mesh tasMesh = MeshBuilder.buildRect(this.pos.getX(), this.pos.getY(), DIMENSION, DIMENSION, Color.GREY);
        this.tas = new Drawable(tasMesh, new Quaternionf(), this.pos.getPosition(), 1);

    }

    void diminuer(int q) {
        this.quantite -= q;
    }
    public int getQuantite() {
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
        this.tas.setPosition(this.pos.getPosition());
    }
}
