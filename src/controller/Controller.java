package controller;

import javafx.application.Platform;
import model.entities.*;
import model.map.Map;
import view.DisplayController;

import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {


    private Timer timer;
    private boolean paused = true;
    private boolean started = false;
    private StepTask stepTask;


    // For the map
    private static final int mapWidth = 20;
    private static final int mapHeight = 20;
    private static final int safeZoneWidth = 3;
    private static final int safeZoneHeight = 2;
    private static int seed = 0;
    private static Map map = new Map(mapWidth, mapHeight, safeZoneWidth, safeZoneHeight);
    private static DisplayController displayController ;

    // For Singleton
    private static final Controller instance = new Controller();
    private Controller() { this.timer = new Timer(); this.stepTask = new Controller.StepTask(this); }

    /**
     * Configure the display controller for the associated.
     * @param displayController
     */
    public static void setDisplayController(DisplayController displayController) {
        Controller.displayController = displayController;
        Controller.displayController.setController(Controller.getInstance());
        Controller.displayController.drawMap(map);
    }

    public static Controller getInstance() {return instance; }

    public void start(){
        start(1000);
    }

    /**
     *
     * @param timing Time between every frame in ms
     * @return
     */
    public boolean start(int timing){
        if (this.started && !this.paused) return false;

        this.started = true;
        this.paused = false;

        // For the auto step
        this.timer.scheduleAtFixedRate(this.stepTask, 0, timing);
        return true;
    }

    public boolean pause() {
        if (this.paused || !this.started) return false;

        this.timer.cancel();
        this.timer = new Timer();
        this.stepTask = new Controller.StepTask(this);

        this.paused = true;

        return true;
    }

    public boolean finish() {
        this.timer.cancel();

        return true;
    }

        static class StepTask extends TimerTask {

        private final Controller controller;
        public StepTask(Controller controller) { this.controller = controller;}

        @Override
        public void run() {
            this.controller.step();
        }
    }

    /**
     * Button for reseting the simulation, reset the simulation by rebuilding the map
     * @param seed for initialisation of the random
     */
    public void reset(long seed) {
        this.pause();
        this.paused = false;
        this.started = false;
        Controller.seed = (int) seed;
        PseudoRandom.reset(seed);
        map =  new Map(mapWidth, mapHeight, safeZoneWidth, safeZoneHeight);
        drawMap();
    }

    /// reset the simulation by rebuilding the map
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
        boolean success = map.step();

        if (!success) System.out.println("Nobody can move anymore!");

        ArrayList<Master> winners = map.checkWin();
        if (winners.size() > 0) {
            if (winners.contains(MasterBritish.getInstance())) System.out.println("British Won!");
            if (winners.contains(MasterUndead.getInstance())) System.out.println("Undead Won!");
            if (winners.contains(MasterPirate.getInstance())) System.out.println("Pirates Won!");
            if (winners.contains(MasterMerchant.getInstance())) System.out.println("Merchants Won!");
            this.pause();
        }

        // DRAW
        //printMap();
        drawMap();
        Platform.runLater(()->{
            Controller.displayController.fillDetails(map.getMap());
            return;
        });
    }


}
