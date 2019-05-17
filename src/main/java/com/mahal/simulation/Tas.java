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
    private Drawable[][] tas;
    private ShaderProgram shaderProgram;
    private Pos pos;
    private float INIT_AMOUNT;

    public Tas(int size, Pos pos, ShaderProgram shaderProgram, float amount) {
        this.pos = pos;
        this.size = size;
        this.amount = amount;
        INIT_AMOUNT = amount;
        this.tas = new Drawable[size][size];
        this.shaderProgram = shaderProgram;
        Mesh tasMesh = MeshBuilder.buildRect(0, 0, -10, unit, unit);
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++ )
                this.tas[x][y] = new Drawable(tasMesh, 0.0f, new Pos(this.pos.getX() + this.unit * x, this.pos.getY() + this.unit * y), 1, new Color(.5f, .5f, .5f, 1f));
        }
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

        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++ ) {
                this.tas[x][y].predraw(this.shaderProgram);
                this.tas[x][y].draw();
            }
        }

    }
    public void reduceQuantity(Pos p) {
        float fading_rate = Utils.map(this.amount / (size*size), 0, INIT_AMOUNT / (size * size), 1, 0);
        System.out.println(fading_rate + " amount: " + amount);
        Pos ref = new Pos((int)(Math.random() * (size - 1)), (int)(Math.random() * (size - 1)));
        while(this.tas[ref.getX()][ref.getY()].getColor().getA() == 0 )
            ref = new Pos((int)(Math.random() * (size - 1)), (int)(Math.random() * (size - 1)));
        this.tas[ref.getX()][ref.getY()].getColor().fadeA(fading_rate);
   }
    @Override
    public void cleanup() {
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                this.tas[x][y].cleanup();
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isColliding(Pos p) {
        return (p.getX() >= this.pos.getX() &&
                p.getX() <= this.pos.getX() + this.unit * this.size)
                && (p.getY() >= this.pos.getY() &&
                p.getY() <= this.pos.getY() + this.unit * this.size);
    }
}
