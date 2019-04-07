package com.mahal.graphics.entity;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.utils.Transformations;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;


public class Drawable {
    private Mesh mesh;
    private Quaternionf rotation;
    private Vector3f position;
    private int scale;
    private Transformations transformations;
    public Drawable(Mesh mesh, Quaternionf quaternion, Vector3f position, int scale) {
        this.mesh = mesh;
        this.rotation = quaternion;
        this.position = position;
        this.scale = scale;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void draw(){
        this.mesh.render();
    }
    public void cleanup() {
        this.mesh.cleanUp();
    }
    public void predraw(ShaderProgram shader) {
        Matrix4f worldMatrix = transformations.getModelMatrix(this.getPosition(), this.getRotation(), this.getScale());
        shader.setUniform("modelMatrix", worldMatrix);
    }

}
