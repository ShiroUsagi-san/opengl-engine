package com.mahal.logic;

import com.mahal.graphics.GraphicsEngine;
import com.mahal.graphics.IGraphicsLogic;
import com.mahal.simulation.Fourmiliere;

public class Main {
    public static int WIDTH = 1200;
    public static int HEIGHT = 860;
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGraphicsLogic gameLogic = new Fourmiliere(WIDTH, HEIGHT);
            GraphicsEngine gameEng = new GraphicsEngine("GAME",
                    WIDTH, HEIGHT, false, vSync, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}

