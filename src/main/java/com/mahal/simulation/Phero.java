package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;

public class Phero implements Entity {
    private Pos pos;
    private int duration;
    private Drawable pheroMesh;
    private final int DIM = 5;
    private ShaderProgram shader;
    private Zone zone;
    private Color color = new Color(1.f, 1.f, 0f, 0.5f);
    public Phero(Zone zone, Pos p, int duration, ShaderProgram s){
        this.pos = p;
        this.zone = zone;
        this.duration = duration;
        this.shader = s;
        Mesh mesh = MeshBuilder.buildQuad(0, 0, -20,this.DIM);
        this.pheroMesh = new Drawable(mesh, 0, this.pos, 1,this.color);

    }
    public Phero(Zone zone, Pos p, ShaderProgram s){
        this.pos = p;
        this.zone = zone;
        this.shader = s;
        this.duration = 1000;
        Mesh mesh = MeshBuilder.buildQuad(0,0 , -20, this.DIM);
        this.pheroMesh = new Drawable(mesh, 0, this.pos, 1,this.color);
    }

    @Override
    public void render() {
        this.pheroMesh.predraw(this.shader);
        this.pheroMesh.draw();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void update() {
        this.duration --;
        if (this.duration <= 0)
            this.zone.addToRemove(this);

    }

    @Override
    public boolean isColliding(Pos p) {
        return false;
    }

    public int getDuration() {
        return duration;
    }
}
