package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import com.mahal.logic.Main;
import org.lwjgl.system.CallbackI;

import java.util.Random;

public class Fourmi implements Entity {
    private Pos position;
    private boolean estCharge;
    private Color color;
    private float rot = 0.0f;
    public static final int DIM = 3;
    private final static Color FOURMI_CHARGEE = Color.MAGENTA;
    private final static Color FOURMI_VIDE    = Color.GREEN;
    private Drawable fourmiEntity;
    private ShaderProgram shaderProgram;
    private Colonie colonie;
    private int duration = 100;
    private int foodAmount = 0;
    private Tas tas;
    private Dir dir = Dir.DirAleatoire();

    public Fourmi(Pos position, Colonie colonie, ShaderProgram shaderProgram) {
        this.position = position;
        this.colonie = colonie;
        this.estCharge = false;
        this.color = FOURMI_VIDE;
        this.shaderProgram = shaderProgram;
        Mesh fourmiMesh = MeshBuilder.buildQuad(0, 0, -10, this.DIM);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position, 1, this.color);
    }


    public Fourmi(Colonie colonie, ShaderProgram shaderProgram) {
        this.colonie = colonie;
        this.color = FOURMI_VIDE;
        this.shaderProgram = shaderProgram;
        Mesh fourmiMesh = MeshBuilder.buildQuad(0, 0, -10, this.DIM);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position, 1, this.color);

    }

    public Fourmi(Pos position, ShaderProgram shaderProgram) {
        this.position = position;
        this.color = FOURMI_VIDE;
        this.shaderProgram = shaderProgram;
        Mesh fourmiMesh = MeshBuilder.buildQuad(0, 0,-10, this.DIM);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position, 1, this.color);

    }
    public Fourmi(ShaderProgram shader) {
        Random rand = new Random();
        this.position = new Pos(rand.nextInt((Main.WIDTH / 2) + 1), rand.nextInt((Main.HEIGHT / 2) + 1));
        this.color = FOURMI_VIDE;
        this.shaderProgram = shader;
        Mesh fourmiMesh = MeshBuilder.buildQuad(0, 0, -10, this.DIM);
        this.fourmiEntity = new Drawable(fourmiMesh, rot, position, 1, this.color);

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
        this.fourmiEntity.setColor(this.color);
        this.fourmiEntity.setPosition(this.position);

    }

    @Override
    public boolean isColliding(Pos p) {
        return false;
    }

    public void prend(Tas tas) {
        this.estCharge = true;
        this.tas = tas;
        this.color = FOURMI_CHARGEE;
        tas.diminuer(2);
        tas.reduceQuantity(this.position);

    }

    public void retourFourmiliere() {

        if(this.position.equals(this.colonie.getpNid())){
            this.estCharge = false;
            this.color = FOURMI_VIDE;
        }
    }
    public void moveEmpty() {
        this.dir = Dir.dirVoisine(this.dir);
        Pos pos = this.position.nextPos(this.dir);
        if(!this.colonie.getZone().posValide(pos))
            return;
        if(this.colonie.getZone().checkCollision(pos) instanceof Wall) {
            return;
        }
        Entity colliding = this.colonie.getZone().checkCollision(pos);
        if(colliding instanceof Phero) {
            Phero p = (Phero) colliding;
            this.dir = new Dir(this.position, p.getAttractivity().getPos());

        }
        this.position = pos;
        if (colliding instanceof Tas && !this.estCharge)
            if (((Tas) colliding).getAmount() > 0)
                prend((Tas) colliding);


    }
    public void moveLoaded() {
        Pos pos = this.position.nextPos(new Dir(this.position,this.colonie.getpNid()));
        if( this.colonie.getZone().checkCollision(pos) instanceof Wall) {
            this.dir = new Dir(this.position.offset(4, 0),this.colonie.getpNid());
            this.position = this.position.nextPos(this.dir);
        } else {
            this.colonie.getZone().posePhero(this.position, this.tas);
            this.position = pos;
            retourFourmiliere();
        }


    }

    public void bouge() {
        if(this.estCharge)
            moveLoaded();
        else if(!this.estCharge)
            moveEmpty();

    }


    public String toString() {
        return "position: " + this.position + " colonie: " + this.colonie + " color: " + this.color;
    }
}