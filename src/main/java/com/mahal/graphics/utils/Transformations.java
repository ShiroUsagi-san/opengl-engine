package com.mahal.graphics.utils;

import org.joml.*;

public class Transformations {
    private Matrix4f projectionMatrix;
    private  Matrix4f modelMatrix;

    public Transformations() {
        projectionMatrix = new Matrix4f();
        modelMatrix = new Matrix4f();
    }

    public final Matrix4f getOrthoMatrix(float width, float height, float zNear, float zFar) {
        projectionMatrix.identity();
        projectionMatrix.ortho(0.0f, width, 0.0f, height, zNear, zFar);
        return projectionMatrix;
    }

    public Matrix4f getModelMatrix(Vector3f offset, Quaternionf rotation, float scale) {
        modelMatrix.identity().translationRotateScale(offset, new Quaternionf(rotation), scale);
        return modelMatrix;
    }
}