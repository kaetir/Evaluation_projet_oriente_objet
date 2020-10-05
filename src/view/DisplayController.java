package view;

import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Map.*;
import java.util.Random;

import java.util.ArrayList;


public class DisplayController {

    private boolean started = false;

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @FXML
    private Canvas canvas;

    @FXML
    private TextField seedText;

    public Integer getSeed() {
        System.out.println(seedText.getText());
        return Integer.parseInt( seedText.getText());
    }

    public void genRandomSeed(){
        seedText.setText(String.valueOf(getRandomNumberInRange(1,123465789)));
    }

    public void createGrid(){
        GraphicsContext g = canvas.getGraphicsContext2D();
    }

    public void startSimulation(){
        System.out.println("Clic on start");
        if (!started){
            try{
                getSeed();
            } catch (Exception e){
                genRandomSeed();
                getSeed();
            }
            started = true;
        }else{
            // TODO Lancer la simulation en auto
        }

    }


    public void pause(){
        System.out.println("Clic on pause");
        // TODO pause the simulation il in autorun.
    }

    public void step(){
        System.out.println("Clic on step");
        // TODO step over one generation
    }

    public void reset(){
        System.out.println("Clic on reset");
        // TODO Reset the simulation - prepare to restart
    }

    public void drawMap(Map map){
        GraphicsContext g = canvas.getGraphicsContext2D();


        Double width  = g.getCanvas().getWidth();
        Double height = g.getCanvas().getHeight();

        g.clearRect(0,0, width, height);

        // Draw the grid
        double x=0.;
        double y=0.;
        double w = width / map.getMap().get(0).size();
        double h = height / map.getMap().size();


        for (ArrayList<Case> line: map.getMap()) {
            // draw horizontal lines
            for (Case c : line) {
                // draw vertical lines
                g.setFill(c.getColor());
                g.fillRect(x, y, w, h*2);
                y += w;
                //g.strokeText(String.valueOf((int)Math.ceil(y/w)-1)+ "," + String.valueOf((int)Math.ceil(x/h)), x, y);
            }
            x += h;
            y = 0;
        }

        // draw horizontal lines
        double hl = height / map.getMap().size();
        for (int xl = 0; xl < map.getMap().size()+1; xl++) {
            double hld = hl * xl;
            g.strokeLine(hld, 0 , hld, width);
        }

        // draw vertical lines
        double wl = width / map.getMap().get(0).size();
        for (int xl = 0; xl < map.getMap().get(0).size()+1; xl++) {
            double wld = wl* xl;
            g.strokeLine(0, wld , height, wld);
        }


    }

}
