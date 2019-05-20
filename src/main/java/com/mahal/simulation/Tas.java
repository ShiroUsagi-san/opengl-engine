package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import com.mahal.graphics.utils.Utils;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;


public class Tas implements Entity {
    private int size;
    private float amount;
    private final int unit = 3;
    private Drawable tas;
    private ShaderProgram shaderProgram;
    private Pos pos;
    private float INIT_AMOUNT;

    public Tas(int size, Pos pos, ShaderProgram shaderProgram, float amount) {
        this.pos = pos;
        this.size = size;
        this.amount = amount;
        INIT_AMOUNT = amount;
        this.shaderProgram = shaderProgram;
        Mesh tasMesh = MeshBuilder.buildRect(0, 0, -10, size, size);
        this.tas = new Drawable(tasMesh, 0.0f, new Pos(this.pos.getX(), this.pos.getY()), 1, new Color(.5f, .5f, .5f, 1f));

    }

    void diminuer(float q) {
        this.amount -= q;
    }
    public float getAmount() {
        return amount;
    }
    public Pos getPos() {
        return pos;
    }

    @Override
    public void render() {
        this.tas.predraw(this.shaderProgram);
        this.tas.draw();

    }
    public void reduceQuantity(Pos p) {

   }
    @Override
    public void cleanup() {
        this.tas.cleanup();
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isColliding(Pos p) {
        return (p.getX() >= this.pos.getX() &&
                p.getX() <= this.pos.getX() + this.size)
                && (p.getY() >= this.pos.getY() &&
                p.getY() <= this.pos.getY() + this.size);
    }
}
