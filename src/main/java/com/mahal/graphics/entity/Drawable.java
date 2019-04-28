package com.mahal.graphics.entity;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.utils.Color;
import com.mahal.graphics.utils.Transformations;
import org.joml.Matrix4f;
import org.joml.Vector3f;


public class Drawable {
    private Mesh mesh;
    //private Quaternionf rotation;
    private Vector3f position;
    private float scale;
    private float rot;
    private Transformations transformations;
    private Color color;
    public Drawable(Mesh mesh, float Zrotation, Vector3f position, float scale, Color color) {
        this.transformations = new Transformations();
        this.mesh = mesh;
        this.rot = Zrotation;
        this.position = position;
        this.scale = scale;
        this.color = color;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {

        this.position = position;
    }
    public Transformations getTransformations() {
        return this.transformations;
    }
    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(){
        this.mesh.render();
    }
    public void cleanup() {
        this.mesh.cleanUp();
    }
    public void predraw(ShaderProgram shader) {
        Matrix4f worldMatrix = transformations.getModelMatrix(this.getPosition(), this.getRot(), this.getScale());
        shader.setUniform("modelMatrix", worldMatrix);
        shader.setUniform("inColor", this.color.getColor());

    }

}
