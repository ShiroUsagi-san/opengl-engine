package com.mahal.graphics;

public interface IGraphicsLogic {
    void init(Window window) throws Exception;

    void update(float time);

    void render(Window window);
    void cleanup();
    //TODO: maybe add input method
}
