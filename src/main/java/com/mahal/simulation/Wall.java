package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;

public class Wall implements Entity {
    private Pos pos;
    private Drawable WallSprite;
    private ShaderProgram shader;
    private float rot = 0.0f;
    private float width, height;
    private Color color;
    public Wall(Pos pos, ShaderProgram shader, Color color, int width, int height) {
        this.pos = pos;
        this.height = height;
        this.width = width;
        this.color = color;
        Mesh wallMesh = MeshBuilder.buildRect(0, 0, -10, this.width, this.height);
        this.WallSprite = new Drawable(wallMesh, rot, pos, 1.f, this.color);
        this.shader = shader;
    }


    @Override
    public void render() {
        this.WallSprite.predraw(this.shader);
        this.WallSprite.draw();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void update() {

    }

    @Override
    public boolean isColliding(Pos p) {
        return (p.getX() >= this.pos.getX() &&
                p.getX() <= this.pos.getX() + this.width)
                && (p.getY() >= this.pos.getY() &&
                p.getY() <= this.pos.getY() + this.height);
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
