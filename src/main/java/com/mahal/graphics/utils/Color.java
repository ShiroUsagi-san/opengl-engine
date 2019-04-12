package com.mahal.graphics.utils;

import com.mahal.graphics.geometry.Mesh;
import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

public class Color {
    private float r;
    private float g;
    private float b;
    public static Color BLUE            = new Color(0, 0, 1.0f);
    public static Color RED             = new Color(1.f, 0, 0);
    public static Color GREEN           = new Color(0, 1.f, 0);
    public static Color BLACK           = new Color(0, 0, 0);
    public static Color WHITE           = new Color(1.f, 1.0f, 1.0f);
    public static Color YELLOW          = new Color(1.0f, 1.0f, 0);
    public static Color CYAN            = new Color(0.0f, 1.0f, 1.0f);
    public static Color MAGENTA         = new Color(1.0f, 0, 1.0f);
    public static Color GREY            = new Color(.5f, .5f, .5f);

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public Vector3f getColor() {
        return new Vector3f(this.r, this.g, this.b);
    }
    public static Color pickRandomColor() {
       return new Color((float) Math.random(), (float) Math.random(),(float) Math.random());
    }
    public static Color newColor(float r, float g, float b) {
        return new Color(r, g, b);
    }
    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }
}
