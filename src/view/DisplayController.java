package view;

import controller.Controller;
import controller.PseudoRandom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.map.*;

import java.util.Collections;
import model.entities.Individual;

import java.util.ArrayList;


public class DisplayController {

    private boolean started = false;
    private Controller controller;



    @FXML
    private ListView<String> Detail_list;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField seedText;

    public Integer getSeed() {
        try{
            return Integer.parseInt( seedText.getText());
        }catch (Exception e){
            return 0;
        }
    }

    public void genRandomSeed(){
        seedText.setText(String.valueOf(PseudoRandom.getRandomNumberInRange(1,123465789)));
    }

    public void setController(Controller controller) {
        this.controller = controller;
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
        controller.step();
    }

    public void reset(){
        System.out.println("Clic on reset");
        this.controller.reset(getSeed());
    }

    public void drawMap(Map map){
        GraphicsContext g = canvas.getGraphicsContext2D();


        double width  = g.getCanvas().getHeight();
        double height = g.getCanvas().getWidth();

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
                g.fillRect(x, y, h, w);
                y += w;

                // g.strokeText(String.valueOf((int)Math.ceil(y/w)-1)+ "," + String.valueOf((int)Math.ceil(x/h)), x, y);
                //g.strokeText(String.valueOf(c.getPrintable()), x, y);
                // g.drawImage(Sprite.load(Sprite.map.get("island")), 10, 10);
                if( c.hasToken() ){
                    g.drawImage(Sprite.load(Sprite.map.get(c.getToken().getImage()), h,w), x, y-w);
                }

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

    public void fillDetails(ArrayList<ArrayList<Case>> map){
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (ArrayList<Case> ligne: map ) {
            for (Case c: ligne ) {
                if (c.hasToken() && c.getToken() instanceof Individual)
                    items.add(c.getToken().toString());
            }
        }
        Collections.sort(items);
        Detail_list.setItems(items);
    }

}
