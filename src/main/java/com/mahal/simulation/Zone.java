package com.mahal.simulation;

import com.mahal.graphics.ShaderProgram;
import com.mahal.graphics.entity.Entity;
import com.mahal.graphics.utils.Color;

import java.util.ArrayList;

public class Zone implements Entity {
    private int Xdim, Ydim;      // la dimension du territoire
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> removedEntities = new ArrayList<>();
    private int nbTas = 0; // le nombre de tas
    private ShaderProgram shaderProgram;
    /*************************************************
     * constructeur de la Zone
     * @param Xdim  la dimension du terrain
     * <ul>une zone est modelisee par ces informations :
     * <li>   le tableau t representant le territoire des fourmis
     * <li>    - t[i][j] = Integer.MAX_VALUE => obstacle
     * <li>    - t[i][j] > 0 => quantit� de nourriture
     * <li>    - t[i][j] = 0 => libre
     * <li>    - t[i][j] < 0 => ph�romone (pour la suite)
     * <li>  la dimension de la Zone(le cote du carre)
     * <li>  le nombre maximum de tas
     * <li>  le nombre de tas de nourriture
     * <li>  la taille des tas
     * <li>  la position des tas de nourriture
     * <ul>
     * ***********************************************/
    public Zone(int Xdim, int Ydim, ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
        this.Xdim = Xdim;
        this.Ydim = Ydim;
    }

    /*************************************************
     * accesseur qui rend la dimension
     * @return la dimension
     * ***********************************************/
    public int getXdim() {
        return Xdim;
    }
    public int getYdim() {
        return Ydim;
    }
    /***********************************************************************
     * teste si une position est valide ( situ�e sur la Zone)
     * @param p  la position � tester
     * @return vrai si OK
     * ********************************************************************/
    public boolean posValide(Pos p) {

        return (p.getX() > 0 && p.getX() < this.Xdim && p.getY() > 0 && p.getY() < this.Ydim);
    }
    public boolean isColliding(Pos p){
        return false;

    }

    public Entity checkCollision(Pos p){
        for(Entity t: entities)
            if (t.isColliding(p))
                return t;
        return null;
    }


    public void metTas(int size, float amount, Pos p) {
        this.entities.add(new Tas(size, p, shaderProgram, amount));
    }
    public void addWall(Pos p, Color c, int width, int height) {
        this.entities.add(new Wall(p, shaderProgram, c, width, height));
    }
    /***************************************************************
     * transforme en String la Zone : dimension - nid - tas
     * @return l'objet Zone transform� en String
     * ******************************************************************/

    /********************************************************************
     * pose de la ph�romone � la position p, direction d
     * @param p la position
     * @param d la direction
     * *****************************************************************/
    public void posePhero(Pos p, Tas t) {
        Phero phero = new Phero(this, p, this.shaderProgram, t);

        this.entities.add(phero);
    }
    public void addToRemove(Entity e) {
        this.removedEntities.add(e);
    }
    @Override
    public void render() {
        for (Entity t: entities)
            t.render();
    }

    @Override
    public void cleanup() {
        for(Entity t: entities)
            t.cleanup();

    }

    @Override
    public void update() {
        entities.removeAll(removedEntities);
        for(Entity t: entities) {
            t.update();
        }
    }

}
