package controller;

import model.Map;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    private long timing = 1000; /// Time between every frame in ms
    private boolean paused = true;
    private boolean started = false;

    private Timer timer;
    private StepTask stepTask;

    private Map map;
    private int mapWidth = 14;
    private int mapHeight = 8;
    private int safeZoneWidth = 3;
    private int safeZoneHeight = 3;

    public Controller() {
        this.timer = new Timer();
    }

    public boolean start() {
        if (this.started) return false;

        this.map = new Map(this.mapWidth, this.mapHeight, this.safeZoneWidth, this.safeZoneHeight);
        this.stepTask = new Controller.StepTask(this.map);

        this.started = true;
        this.paused = false;

        this.timer.scheduleAtFixedRate(this.stepTask, 0, this.timing);
        return true;
    }

    private boolean pause() {
        if (this.paused || !this.started) return false;

        this.timer.cancel();

        this.paused = true;
        return true;
    }

    private boolean resume() {
        if (!this.paused || !this.started) return false;

        this.timer.scheduleAtFixedRate(this.stepTask, 0, this.timing);

        this.paused = false;
        return true;
    }

    private void reset() {

    }

    static class StepTask extends TimerTask {

        private Map map;

        public StepTask(Map map) {
            this.map = map;
        }

        @Override
        public void run() {
            this.map.printMap();
        }
    }

}
