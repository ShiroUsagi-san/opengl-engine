package com.mahal.logic;

import com.mahal.graphics.IGraphicsLogic;
import com.mahal.graphics.Window;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.utils.Color;
import com.mahal.simulation.Colonie;
import com.mahal.simulation.Pos;
import com.mahal.simulation.Zone;
import java.util.ArrayList;

public class Context implements IGraphicsLogic {

    private Color color;
    private ArrayList<Entity> items = new ArrayList<>();
    private final Renderer renderer;

    public Context() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Zone zone = new Zone(Main.WIDTH, Main.HEIGHT, renderer.getShaderProgram());
        Pos pNid = new Pos(50, 50);
        Pos p1 = new Pos(200, 200);
        zone.metTas(p1);
        Colonie c = new Colonie(10, pNid, renderer.getShaderProgram());
        items.add(c);
        items.add(zone);
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
        for(Entity e: items)
            e.update();

    }

    @Override
    public void render(Window window) {
        window.setClearColor(Color.BLACK);
        renderer.getShaderProgram().bind();
        renderer.render(window);
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

