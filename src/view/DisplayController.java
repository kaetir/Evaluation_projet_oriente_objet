package view;

import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import model.*;

import java.util.ArrayList;


public class DisplayController {

    @FXML
    private Canvas canvas;

    public void createGrid(){
        GraphicsContext g = canvas.getGraphicsContext2D();
    }

    public void drawMap(Map map){
        GraphicsContext g = canvas.getGraphicsContext2D();

        Double width  = g.getCanvas().getWidth();
        Double height = g.getCanvas().getHeight();

        // Draw the grid
        double x=0.;
        double y=0.;
        double w = width / map.getMap().get(0).size();
        double h = height / map.getMap().size();


        for (ArrayList<Case> line: map.getMap()) {
            // draw horizontal lines
            for (Case c : line) {
                // draw vertical lines
                if (c instanceof SafeCase){
                    if(c instanceof SafeCaseBritish){
                        g.setFill(Color.BLUEVIOLET);
                    }else if(c instanceof SafeCaseMerchant){
                        g.setFill(Color.GREEN);
                    }else if(c instanceof SafeCasePirate) {
                        g.setFill(Color.BLACK);
                    }else if(c instanceof SafeCaseUndead){
                        g.setFill(Color.DARKGRAY);
                    }
                }else{
                    g.setFill(Color.AQUA);
                }

                g.fillRect(x,y, w, h);
                y += h;
                // g.strokeText(String.valueOf(y).substring(0,1), x, y);
            }
            x += w;
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
