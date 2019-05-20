package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import com.mahal.graphics.utils.Utils;

public class Phero implements Entity {
    private Pos pos;
    private int duration;
    private Drawable pheroMesh;
    private final int DIM = 5;
    private ShaderProgram shader;
    private Zone zone;
    private Dir dir;
    private Tas tas;
    private Color color = new Color(1.f, 1.f, 0f, 0.5f);
    public Phero(Zone zone, Pos p, int duration, ShaderProgram s, Tas t){
        this.pos = p;
        this.tas = t;
        this.zone = zone;
        this.dir = dir;
        this.duration = duration;
        this.shader = s;
        Mesh mesh = MeshBuilder.buildQuad(0, 0, -20,this.DIM);
        this.pheroMesh = new Drawable(mesh, 0, this.pos, 1,this.color);

    }
    public Phero(Zone zone, Pos p, ShaderProgram s, Tas t){
        this.pos = p;
        this.tas = t;
        this.dir = dir;
        this.zone = zone;
        this.shader = s;
        this.duration = 500;
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
        this.color.setA(Utils.map(this.duration, 100, 0, 0.5f, 0));
        if (this.duration <= 0)
            this.zone.addToRemove(this);

    }

    @Override
    public boolean isColliding(Pos p) {
        return (p.getX() >= this.pos.getX() &&
                p.getX() <= this.pos.getX() + DIM)
                && (p.getY() >= this.pos.getY() &&
                p.getY() <= this.pos.getY() + DIM);
    }

    public Dir getDir() {
        return dir;
    }

    public Tas getAttractivity() {
        return tas;
    }

    public int getDuration() {
        return duration;
    }
}
