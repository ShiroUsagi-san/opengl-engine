package com.mahal.graphics.entity;

import com.mahal.graphics.geometry.Mesh;

public interface Renderable {
    public void render();
    public void update(float interval);
    public void cleanup();
}
