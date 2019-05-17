package com.mahal.graphics.geometry;


public class MeshBuilder {

    public static Mesh buildRect(float x, float y,float z,  float width, float height) {
        float[] positions = new float[]{
                x, y, z,
                x, y + height, z,
                x + width, y + height, z,
                x + width, y, z,

        };
        int[] indices = new int[]{
                0, 1, 2, 2, 0, 3
        };

        return new Mesh(positions, indices);
    }

    public static Mesh buildQuad(float x, float y,float z,  float square) {
        float[] positions = new float[]{
                x, y, z,
                x, y + square, z,
                x + square, y + square, z,
                x + square, y, z,

        };
        int[] indices = new int[]{
                0, 1, 2, 2, 0, 3
        };

        return new Mesh(positions, indices);
    }


}
