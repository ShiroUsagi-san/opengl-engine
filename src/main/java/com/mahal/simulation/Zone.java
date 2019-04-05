package com.mahal.simulation;

import com.mahal.graphics.entity.Drawable;
import com.mahal.graphics.entity.Renderable;
import com.mahal.graphics.geometry.Mesh;
import com.mahal.graphics.geometry.MeshBuilder;
import com.mahal.graphics.utils.Color;
import org.joml.Quaternionf;

import java.util.ArrayList;

public class Zone implements Renderable {
    private int dim;      // la dimension du territoire
    private ArrayList<Tas> tas = new ArrayList<>(); // les tas de nourriture
    private int nbTas = 0; // le nombre de tas

    /*************************************************
     * constructeur de la Zone
     * @param dim  la dimension du terrain
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
    public Zone(int dim) {
        this.dim = dim;
    }

    /*************************************************
     * accesseur qui rend la dimension
     * @return la dimension
     * ***********************************************/
    public int getDim() {
        return dim;
    }

    /***********************************************************************
     * teste si une position est valide ( situ�e sur la Zone)
     * @param p  la position � tester
     * @return vrai si OK
     * ********************************************************************/
    public boolean posValide(Pos p) {
        return (p.getX() > 0 && p.getX() < this.dim && p.getY() > 0 && p.getY() < this.dim);
    }


    public void metTas(Pos p) {
        this.tas.add(new Tas(p));
    }
    /***************************************************************
     * transforme en String la Zone : dimension - nid - tas
     * @return l'objet Zone transform� en String
     * ******************************************************************/
    public String toString() {
        String resul = "dim=" + this.dim + "\n";
        for (int i = 0; i < nbTas; i++) {
            resul += tas.toString() + " ";
        }
        return resul;
    }

    /********************************************************************
     * pose de la ph�romone � la position p, direction d
     * @param p la position
     * @param d la direction
     * *****************************************************************/
    public void posePhero(Pos p, int d) {
        //TODO: A IMPLEMENTER
       // t[p.getX()][p.getY()] = -(d + 1);//-1 � -8 pb si 0
    }

    @Override
    public void render() {
        for(Tas tas :tas){
            tas.render();
        }
    }

    @Override
    public void update(float interval) {
        for(Tas tas: tas) {
            tas.update(interval);
        }

    }

    @Override
    public void cleanup() {
        for(Tas tas: tas) {
            tas.cleanup();
        }
    }
}
