package com.mahal.logic;

import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.IGraphicsLogic;
import com.mahal.graphics.Window;
import com.mahal.simulation.Colonie;
import com.mahal.simulation.Pos;
import com.mahal.simulation.Zone;

import java.util.ArrayList;

public class Context implements IGraphicsLogic {

    private float color = 0.0f;
    private ArrayList<Drawable> items = new ArrayList<>();
    private final Renderer renderer;

    public Context() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Zone zone = new Zone(500);

        Pos p1 = new Pos(200, 320);
        Pos p3 = new Pos(360, 380);
        Pos p4 = new Pos(250, 250);
        Pos pNid = new Pos(300, 300);
        zone.metTas(p1);
        zone.metTas(p3);
        zone.metTas(p4);
        Colonie c = new Colonie(10, pNid);
    }
    public void addItem(Drawable item) {
        this.items.add(item);
    }

    public ArrayList<Drawable> getItems() {
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
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for(Drawable item : items) {
            item.cleanup();
        }
    }
}

