package com.mahal.logic;

import com.mahal.graphics.IGraphicsLogic;
import com.mahal.graphics.Window;
import com.mahal.graphics.entity.Entity;
import com.mahal.simulation.Colonie;
import com.mahal.simulation.Pos;
import com.mahal.simulation.Zone;
import java.util.ArrayList;

public class Context implements IGraphicsLogic {

    private float color = 0.0f;
    private ArrayList<Entity> items = new ArrayList<>();
    private final Renderer renderer;

    public Context() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Zone zone = new Zone(500);
        Pos pNid = new Pos(50, 50);
        Colonie c = new Colonie(1, pNid, renderer.getShaderProgram());
        items.add(c);
    }
    public void addItem(Entity item) {
        this.items.add(item);
    }

    public ArrayList<Entity> getItems() {
        return items;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void render(Window window) {
        window.setClearColor(color, color, color, 0.0f);
        renderer.render(window);
        renderer.getShaderProgram().bind();
        for(Entity e: items) {
            e.render();
        }
        renderer.getShaderProgram().unbind();
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for(Entity item : items)
            item.cleanup();
    }
}

