package main.java.Evaluation_projet_oriente_objet.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import main.java.Evaluation_projet_oriente_objet.model.entities.*;
import main.java.Evaluation_projet_oriente_objet.model.map.Map;
import main.java.Evaluation_projet_oriente_objet.view.DisplayController;

import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Singleton controlling the Map and simulation
 */
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

    /**
     * Get the singleton instance of the controller
     * @return the singleton
     */
    public static Controller getInstance() {return instance; }

    public void start(){
        start(1000);
    }

    /**
     * Start simulation with a timing step time
     * @param timing Time between every frame in ms
     * @return
     */
    public boolean start(int timing){
        if (this.started && !this.paused) return false;

        this.started = true;
        this.paused = false;

        // For the auto step
        this.timer.schedule(this.stepTask, 0, timing);
        return true;
    }

    /**
     * Pause the simulation
     * @return a pause success
     */
    public boolean pause() {
        if (this.paused || !this.started) return false;

        this.timer.cancel();
        this.timer = new Timer();
        this.stepTask = new Controller.StepTask(this);

        this.paused = true;

        return true;
    }

    /**
     * Finish the simulation
     * @return a finish success
     */
    public boolean finish() {
        this.timer.cancel();

        return true;
    }

    /**
     * A subclass which permit a timer creation
     */
    static class StepTask extends TimerTask {

        private final Controller controller;
        public StepTask(Controller controller) { this.controller = controller;}

        /**
         * Execute Timer
         */
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

    /**
     * Print map in console
     */
    public void printMap(){
        Controller.map.printMapASCII();
    }

    /**
     * Draw map with JavaFX
     */
    public void drawMap(){
        Controller.displayController.drawMap(Controller.map);
    }

    /**
     * Do a step in the simulation and check whether a group won
     */
    public void step(){
        boolean success = map.step();

        if (!success){
            System.out.println("Nobody can move anymore!");
            finish();

        }

        ArrayList<Master> winners = map.checkWin();
        if (winners.size() > 0) {
            finish();
            if (winners.contains(MasterBritish.getInstance())) System.out.println("British Won!");
            if (winners.contains(MasterUndead.getInstance())) System.out.println("Undead Won!");
            if (winners.contains(MasterPirate.getInstance())) System.out.println("Pirates Won!");
            if (winners.contains(MasterMerchant.getInstance())) System.out.println("Merchants Won!");
        }
        if(!success && winners.size() == 0){
            System.out.println("No one won :(");
        }

        // DRAW
        //printMap();
        drawMap();
        Platform.runLater(()->{
            Controller.displayController.fillDetails(map.getMap());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            if (winners.size() > 0) {
                finish();
                if (winners.contains(MasterBritish.getInstance())) {
                    a.setTitle("British Won!");
                    a.setContentText("British Won!");
                }
                if (winners.contains(MasterUndead.getInstance())){
                    a.setTitle("Undead Won!");
                    a.setContentText("Undead Won!");
                }
                if (winners.contains(MasterPirate.getInstance())){
                    a.setTitle("Pirates Won!");
                    a.setContentText("Pirates Won!");
                }
                if (winners.contains(MasterMerchant.getInstance())){
                    a.setTitle("Merchants Won!");
                    a.setContentText("Merchants Won!");
                }
                a.showAndWait();
            }
            if(!success && winners.size() == 0) {
                ArrayList<String> winnersForfait = map.checkWinForfait();
                a.setTitle("No winner :'(");
                a.setContentText("Gagnant par default \n" + String.join(", ", winnersForfait));
                a.showAndWait();
            }
        });
    }
}
