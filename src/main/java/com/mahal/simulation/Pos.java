package com.mahal.simulation;

import java.util.Objects;

public class Pos {
    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Pos(int dim) {
        this.x = (int)(Math.random() * dim);
        this.y = (int)(Math.random() * dim);
    }
    public Pos posVoisine(){
        int dx = (int) (Math.random() *3)-1;
        int dy = (int) (Math.random() *3)-1;
        return new Pos(this.x+dx,this.y+dy);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pos)) return false;
        Pos pos = (Pos) o;
        return this.x == pos.x && this.y == pos.y;
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
}
