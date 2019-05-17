package com.mahal.simulation;

import org.joml.Vector3f;

public class Pos {
    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pos(int dim) {
        this.x = Math.round((float)Math.random() * dim);
        this.y = Math.round((float)Math.random() * dim);
    }

    public Pos posVoisine(){
        int dx = Math.round((int)(Math.random()*3))-1;
        int dy = Math.round((int)(Math.random() *3))-1;
        return new Pos(this.x+dx*2,this.y+dy*2);
    }

    public Vector3f toVec3f() {
        return new Vector3f(x, y, 0);
    }
    public  Pos nextPos(Dir dir) {
        return new Pos(this.x + dir.dx() , this.y + dir.dy());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pos)) return false;
        Pos pos = (Pos) o;
        return this.x == pos.x && this.y == pos.y;
    }

    public Vector3f getPosition() {
        return new Vector3f(this.x, this.y, 0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "Pos{" +
                "x:" + x +
                ", y:" + y +
                '}';
    }
}
