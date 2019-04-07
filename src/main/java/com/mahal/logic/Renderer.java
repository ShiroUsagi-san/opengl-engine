package com.mahal.logic;

import com.mahal.graphics.*;
import com.mahal.graphics.utils.Transformations;
import com.mahal.graphics.utils.Utils;
import org.joml.Matrix4f;


import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    private final static float Z_NEAR = 0.01f;
    private final static float Z_FAR  = 10000.0f;
    private Transformations transformations;
    private ShaderProgram shaderProgram;

    public Renderer() {
        transformations = new Transformations();
    }


    public void init(Window window) throws Exception {
        shaderProgram = new ShaderProgram();

        shaderProgram.createVertexShader(Utils.loadResource("/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/fragments.fs"));
        shaderProgram.link();

        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelMatrix");

        window.setClearColor(0.f, 0.f, 0.f, 0.f);

    }

    public ShaderProgram getShaderProgram() {
        return this.shaderProgram;
    }

    public void render(Window window) {
        clear();
        Matrix4f projectionMatrix = transformations.getOrthoMatrix(window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

    }
    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }

    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
