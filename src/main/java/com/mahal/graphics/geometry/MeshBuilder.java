package com.mahal.graphics.geometry;


public class MeshBuilder {

    public static Mesh buildRect(float x, float y, float width, float height) {
        float[] positions = new float[]{
                x, y, -10,
                x, y + height, -10,
                x + width, y + height, -10,
                x + width, y, -10,

        };
        int[] indices = new int[]{
                0, 1, 2, 2, 0, 3
        };

        return new Mesh(positions, indices);
    }


}
