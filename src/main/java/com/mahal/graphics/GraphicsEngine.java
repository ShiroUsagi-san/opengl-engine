package com.mahal.graphics;


import com.mahal.graphics.utils.Timer;

//THE GRAPHIC ENGINE WILL BE RUN IN A DIFFERENT THREAD SO IT MUST IMPLEMENTS RUNNABLE
public class GraphicsEngine implements Runnable{
    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;
    private final Thread GraphicsEngineThread;
    private final Window window;
    private final Timer timer;
    private final IGraphicsLogic graphicsLogic;

    public GraphicsEngine(String windowTitle, int width, int height, boolean isCentered, boolean vsync, IGraphicsLogic graphicsLogic) throws Exception {
        this.GraphicsEngineThread = new Thread(this, "Graphic Engine Thread");
        this.window = new Window(width, height, windowTitle, isCentered, vsync);
        this.graphicsLogic = graphicsLogic;
        this.timer = new Timer();

    }
    public void start() {
        this.GraphicsEngineThread.start();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void gameLoop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!window.isVsync()) {
                sync();
            }
        }
    }
    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }

    protected void init() throws Exception{
        System.out.println(window + " " + timer + " " + graphicsLogic);
        window.init();
        timer.init();
        graphicsLogic.init(window);

    }
    protected void update(float time) {
        graphicsLogic.update(time);
    }
    protected  void render() {
        graphicsLogic.render(window);
        window.update();
    }
}
