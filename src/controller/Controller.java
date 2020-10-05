package controller;

import model.Map.Map;
import view.DisplayController;

public class Controller {

    private boolean paused = true;
    private boolean started = false;

    private static final int mapWidth = 20;
    private static final int mapHeight = 20;
    private static final int safeZoneWidth = 3;
    private static final int safeZoneHeight = 3;
    private static Map map = new Map(mapWidth, mapHeight, safeZoneWidth, safeZoneHeight);
    private static DisplayController displayController ;

    private static final Controller instance = new Controller();
    private Controller() {}

    public static void setDisplayController(DisplayController displayController) {
        Controller.displayController = displayController;
        Controller.displayController.setController(Controller.getInstance());
    }

    public static Controller getInstance() {return instance; }

    /// start the simulation if not already started
    public void start() {
        // check if already started
        if (this.started) return;

        // Creating the map
        map = new Map(mapWidth, mapHeight, safeZoneWidth, safeZoneHeight);

        this.started = true;
        this.paused = false;
//        while (!this.paused){
//            this.stepTask.run();
//        }
    }

    /// Pause the simulation
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

    /// reset the simultation by rebuilding the map
    private void reset() {
    }

    public void printMap(){
        Controller.map.printMap();
    }
    public void drawMap(){
        Controller.displayController.drawMap(Controller.map);
    }

    public void step(){
        drawMap();
        printMap();
    }


}
