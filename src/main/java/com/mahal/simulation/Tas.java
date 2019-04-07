package com.mahal.simulation;

import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import org.joml.Quaternionf;

public class Tas {
    private int quantite;
    private final int DIMENSION =  10;
    private Drawable tas;
    private Pos pos;

    public Tas(Pos pos) {
        this.pos = pos;
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
}
