package controller;

import model.Map;

public class Controller {

    private boolean paused = true;
    private boolean started = false;

    private StepTask stepTask;

    private Map map;
    private final int mapWidth = 20;
    private final int mapHeight = 20;
    private final int safeZoneWidth = 3;
    private final int safeZoneHeight = 3;

    private static final Controller instance = new Controller();
    private Controller() { }

    public static final Controller getInstance() {return instance; }

    /// start the simulation if not already started
    public boolean start() {

        // check if already started
        if (this.started) return false;

        // Creating the map
        this.map = new Map(this.mapWidth, this.mapHeight, this.safeZoneWidth, this.safeZoneHeight);
        this.stepTask = new Controller.StepTask(this.map);

        this.started = true;
        this.paused = false;
        while (!this.paused){
            this.stepTask.run();
        }
        return true;
    }

    ///
    private boolean pause() {
        if (this.paused || !this.started) return false;
        this.paused = true;
        return true;
    }

    private boolean resume() {
        if (!this.paused || !this.started) return false;

        this.paused = false;
        return true;
    }

    private void reset() {

    }

    static class StepTask  {

        private Map map;

        public StepTask(Map map) {
            this.map = map;
        }

        public void run() {
            this.map.printMap();
        }
    }

}
