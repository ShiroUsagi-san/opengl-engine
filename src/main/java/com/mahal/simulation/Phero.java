package com.mahal.simulation;

import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;

public class Phero implements Entity {
    private Pos pos;
    private int duration;
    private Drawable pheroMesh;

    public Phero(Pos p, int duration){
        this.pos = p;
        this.duration = duration;
        Mesh mesh = MeshBuilder.buildRect(0, 0, 10, 10);
        this.pheroMesh = new Drawable(mesh, 0, this.pos, 1,Color.YELLOW);

    }
    public Phero(Pos p){
        this.pos = p;
        this.duration = 1000;
        Mesh mesh = MeshBuilder.buildRect(0,0 , 10, 10);
        this.pheroMesh = new Drawable(mesh, 0, this.pos, 1,Color.YELLOW);
    }

    @Override
    public void render() {
        this.pheroMesh.draw();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void update() {
        this.duration --;
    }
}
