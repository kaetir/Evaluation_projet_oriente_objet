package controller;

import model.Entities.*;
import model.Map.Map;
import view.DisplayController;

import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    private static final int mapWidth = 20;
    private static final int mapHeight = 20;
    private static final int safeZoneWidth = 3;
    private static final int safeZoneHeight = 2;
    private static final int seed = 0;
    private static Map map = new Map(mapWidth, mapHeight, safeZoneWidth, safeZoneHeight);
    private static DisplayController displayController ;

    private static final Controller instance = new Controller();
    private Controller() {}

    public static void setDisplayController(DisplayController displayController) {
        Controller.displayController = displayController;
        Controller.displayController.setController(Controller.getInstance());
        Controller.displayController.drawMap(map);
    }

    public static Controller getInstance() {return instance; }

    /// reset the simultation by rebuilding the map
    public void reset(long seed) {
        PseudoRandom.reset(seed);
        map =  new Map(mapWidth, mapHeight, safeZoneWidth, safeZoneHeight);
        drawMap();
    }

    /// reset the simultation by rebuilding the map
    public void reset() {
        reset(0);
    }

    public void printMap(){
        Controller.map.printMapASCII();
    }
    public void drawMap(){
        Controller.displayController.drawMap(Controller.map);
    }

    public void step(){
        boolean success = this.map.step();

        if (!success) System.out.println("Nobody can move anymore!");

        ArrayList<Master> winners = this.map.checkWin();
        if (winners.size() > 0) {
            if (winners.contains(MasterBritish.getInstance())) System.out.println("British Won!");
            if (winners.contains(MasterUndead.getInstance())) System.out.println("Undeads Won!");
            if (winners.contains(MasterPirate.getInstance())) System.out.println("Pirates Won!");
            if (winners.contains(MasterMerchant.getInstance())) System.out.println("Merchants Won!");
        }

        // DRAW
        //printMap();
        drawMap();
        displayController.fillDetails(this.map.getMap());
    }


}
