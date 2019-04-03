package com.mahal.logic;

import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.IGraphicsLogic;
import com.mahal.graphics.Window;
import com.mahal.graphics.utils.Color;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Context implements IGraphicsLogic {

    private float color = 0.0f;
    private ArrayList<Drawable> items = new ArrayList<Drawable>();
    private final Renderer renderer;

    public Context() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Quaternionf rot = new Quaternionf();
        for(int i = 0; i < 10000; i++) {
            Mesh rect = MeshBuilder.buildRect(100, 100, (float) (Math.random() * Main.WIDTH), (float) (Math.random() * Main.HEIGHT), Color.pickRandomColor());

            items.add(new Drawable(rect, rot, new Vector3f(0, 0, 0), 1));
        }
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
        renderer.render(window, items);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for(Drawable item : items) {
            item.getMesh().cleanUp();
        }
    }
}

