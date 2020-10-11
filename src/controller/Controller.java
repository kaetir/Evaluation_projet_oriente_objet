package controller;

import model.Entities.Individual;
import model.Map.Map;
import view.DisplayController;

import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    private static final int mapWidth = 20;
    private static final int mapHeight = 20;
    private static final int safeZoneWidth = 3;
    private static final int safeZoneHeight = 2;
    private static Map map = new Map(mapWidth, mapHeight, safeZoneWidth, safeZoneHeight);
    private static DisplayController displayController ;

    private static final Controller instance = new Controller();
    private Controller() {}

    public static void setDisplayController(DisplayController displayController) {
        Controller.displayController = displayController;
        Controller.displayController.setController(Controller.getInstance());
    }

    public static Controller getInstance() {return instance; }

    /// reset the simultation by rebuilding the map
    private void reset() {
    }

    public void printMap(){
        Controller.map.printMapASCII();
    }
    public void drawMap(){
        Controller.displayController.drawMap(Controller.map);
    }

    public void step(){
        this.map.step();

        // DRAW
        printMap();
        drawMap();
    }


}
