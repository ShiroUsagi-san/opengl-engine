package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import com.mahal.logic.Main;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class Fourmi implements Entity {
    private Pos position;
    private boolean estCharge;
    private Color color;
    private float rot = 0.0f;
    private final static Color FOURMI_CHARGEE = Color.MAGENTA;
    private final static Color FOURMI_VIDE    = Color.GREEN;
    private Drawable fourmiEntity;
    private ShaderProgram shaderProgram;
    private Colonie colonie;
    private int duration = 2;

    private Dir next_dir = Dir.DirAleatoire();

    public Fourmi(Pos position, Colonie colonie, ShaderProgram shaderProgram) {
        this.position = position;
        this.colonie = colonie;
        this.estCharge = false;
        this.color = FOURMI_VIDE;
        this.shaderProgram = shaderProgram;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position.getPosition(), 1, this.color);
    }


    public Fourmi(Colonie colonie, ShaderProgram shaderProgram) {
        this.colonie = colonie;
        this.color = FOURMI_VIDE;
        this.shaderProgram = shaderProgram;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position.getPosition(), 1, this.color);

    }

    public Fourmi(Pos position, ShaderProgram shaderProgram) {
        this.position = position;
        this.color = FOURMI_VIDE;
        this.shaderProgram = shaderProgram;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position.getPosition(), 1, this.color);

    }
    public Fourmi(ShaderProgram shader) {
        Random rand = new Random();
        this.position = new Pos(rand.nextInt((Main.WIDTH / 2) + 1), rand.nextInt((Main.HEIGHT / 2) + 1));
        this.color = FOURMI_VIDE;
        this.shaderProgram = shader;
        Mesh fourmiMesh = MeshBuilder.buildRect(this.position.getX(), this.position.getY(), 10, 10);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position.getPosition(), 1, this.color);

    }
    public void render(){
        this.fourmiEntity.predraw(this.shaderProgram);
        this.fourmiEntity.draw();
    }

    @Override
    public void cleanup() {
        this.fourmiEntity.cleanup();
    }

    @Override
    public void update() {
        this.duration --;
        this.bouge();

        this.fourmiEntity.setPosition(this.position.getPosition());
        this.fourmiEntity.setColor(this.color);

    }

    public void prend(Tas tas) {
        this.estCharge = true;
        this.color = FOURMI_CHARGEE;
        tas.diminuer(3);
    }

    public void RetourFourmiliere() {
        Pos pos = this.position.nextPos(new Dir(this.colonie.getpNid(), this.position));
        this.position = pos;
        if(this.position.equals(this.colonie.getpNid())){
            this.estCharge = false;
            this.color = FOURMI_VIDE;
        }
    }

    public void bouge() {
        System.out.println("[fourmi] " + this.position);
        this.position.setX(this.position.getX() + 1);
        Tas tas = this.colonie.getZone().isInTas(this.position);
        if(tas != null)
            System.out.println(tas.getPos());
        /*
      //  if(Fourmiliere.zone.posValide(next_pos)) {
            if (!this.estCharge) {
                if(this.duration <= 0) {
                    this.duration = 100;
                    this.next_dir = Dir.DirAleatoire();
                }
                this.position = this.position.nextPos(this.next_dir);

                Tas tas = this.colonie.getZone().isInTas(this.position);

                if ( tas!= null) {
                    if(tas.getQuantite() > 0.0f) {
                        prend(tas);
                    }
                }
            } else if (this.estCharge) {
                RetourFourmiliere();
            }

       // }
       */

    }


    public String toString() {
        return "position: " + this.position + " colonie: " + this.colonie + " color: " + this.color;
    }
}