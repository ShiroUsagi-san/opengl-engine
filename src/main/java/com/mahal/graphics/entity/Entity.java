package com.mahal.graphics.entity;

import com.mahal.simulation.Pos;

public interface Entity {
    void render();
    void cleanup();
    void update();
    boolean isColliding(Pos p);
}
