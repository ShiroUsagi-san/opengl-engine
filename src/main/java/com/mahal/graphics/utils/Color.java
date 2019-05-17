package com.mahal.graphics.utils;

import com.mahal.graphics.geometry.Mesh;
import org.joml.Vector3f;
import org.joml.Vector4d;
import org.joml.Vector4f;
import org.lwjgl.system.CallbackI;

public class Color {
    private float r;
    private float g;
    private float b;
    private float a;
    public static Color BLUE            = new Color(0, 0, 1.0f, 1f);
    public static Color RED             = new Color(1.f, 0, 0, 1f);
    public static Color GREEN           = new Color(0, 1.f, 0, 0.7f);
    public static Color BLACK           = new Color(0, 0, 0, 1f);
    public static Color WHITE           = new Color(1.f, 1.0f, 1.0f, 1f);
    public static Color YELLOW          = new Color(1.0f, 1.0f, 0,1f);
    public static Color CYAN            = new Color(0.0f, 1.0f, 1.0f, 1.f);
    public static Color MAGENTA         = new Color(1.0f, 0, 1.0f, 1.f);
    public static Color GREY            = new Color(.5f, .5f, .5f, 1f);

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public Vector4f getColor() {
        return new Vector4f(this.r, this.g, this.b, this.a);
    }
    public static Color pickRandomColor() {
       return new Color((float) Math.random(), (float) Math.random(),(float) Math.random(), (float) Math.random());
    }
    public static Color newColor(float r, float g, float b, float a) {
        return new Color(r, g, b, a);
    }
    public float getR() {
        return r;
    }
    public void setA(float a)  {
        this.a = a;
    }
    public void fadeA(float fade) {
        this.a -= fade;
    }
    public float getG() {
        return g;
    }
    public float getB() {
        return b;
    }
    public float getA() { return a; }
}
