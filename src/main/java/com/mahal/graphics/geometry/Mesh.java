package com.mahal.graphics.geometry;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;
import static org.lwjgl.system.MemoryUtil.memFree;

public class Mesh {
    private final int vboId;

    private final int vaoId;
    private final int idxVboId;
    private final int vertexCount;

    public Mesh(float[] positions, int[] indices) {
        FloatBuffer verticesBuffer = null;
        IntBuffer indicesBuffer = null;
        try {

            vertexCount = indices.length;

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            //POSITION VBO
            vboId = glGenBuffers();
            verticesBuffer = memAllocFloat(positions.length);
            verticesBuffer.put(positions).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            verticesBuffer.flip();
            //INDICES VBO
            idxVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
            indicesBuffer.flip();


            glBindVertexArray(0);
        } finally {
            memFree(verticesBuffer);
            memFree(indicesBuffer);
        }

    }

    public void render() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glBindVertexArray(this.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, this.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
        glDisable((GL_BLEND));
    }
    public void cleanUp() {
        glDisableVertexAttribArray(0);

        // Delete the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboId);
        glDeleteBuffers(idxVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }


    public int getVboId() {
        return vboId;
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
