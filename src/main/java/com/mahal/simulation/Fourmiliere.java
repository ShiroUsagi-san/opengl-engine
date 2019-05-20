package com.mahal.simulation;

import com.mahal.graphics.IGraphicsLogic;
import com.mahal.graphics.Window;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.utils.Color;
import com.mahal.logic.Main;
import com.mahal.logic.Renderer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.glViewport;

public class Fourmiliere implements IGraphicsLogic{
    public Zone zone;
    public static final Pos origine  = new Pos(Main.WIDTH / 2 , Main.HEIGHT / 2);
    private ArrayList<Entity> entites = new ArrayList<>();
    private final Renderer renderer;

    public Fourmiliere(int windowWidth, int windowHeight) {
        renderer = new Renderer();
        zone = new Zone(windowWidth, windowHeight, this.renderer.getShaderProgram());

    }
    public ArrayList<Entity> getEntites() {
        return entites;
    }

    public void addEntity(Entity entity) {
        this.entites.add(entity);
    }
    public void removeEntity(Entity entity) {
        this.entites.remove(entity);
    }
    public void setEntites(ArrayList<Entity> entites) {
        this.entites = entites;
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        zone = new Zone(Main.WIDTH, Main.HEIGHT, renderer.getShaderProgram());
        Pos p1 = new Pos(400, 200);
        Pos p2 = new Pos(Main.WIDTH / 2, 100);
        zone.metTas(11,500f, p1 );
        zone.metTas(11,500f, p2);

        zone.addWall(new Pos(Main.WIDTH / 2, Main.HEIGHT / 2 - 20), Color.BLUE, 30, 10);
        zone.addWall(new Pos(Main.WIDTH / 2, Main.HEIGHT / 2 + 20), Color.BLUE, 30, 10);
        Colonie c = new Colonie(100, origine, renderer.getShaderProgram(), zone);
        entites.addAll(Arrays.asList(c.getPopulation()));
        entites.add(zone);
    }

    @Override
    public void update(float interval) {
        for(Entity e: entites)
            e.update();

    }

    @Override
    public void render(Window window) {
        if(window.isResized()) {
            glViewport(0,0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }
        window.setClearColor(Color.BLACK);
        renderer.getShaderProgram().bind();

        renderer.render(window);
        for(Entity e: entites) {
            e.render();


        }
        renderer.getShaderProgram().unbind();
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for(Entity item : entites)
            item.cleanup();
    }
}
