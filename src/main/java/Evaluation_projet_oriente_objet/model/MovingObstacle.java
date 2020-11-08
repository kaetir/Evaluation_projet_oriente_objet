package main.java.Evaluation_projet_oriente_objet.model;

import main.java.Evaluation_projet_oriente_objet.controller.PseudoRandom;
import main.java.Evaluation_projet_oriente_objet.model.map.Case;
import main.java.Evaluation_projet_oriente_objet.utils.Direction;

import java.util.ArrayList;

/**
 * Class for Obstacle which can move
 */
public class MovingObstacle extends Obstacle{

    Direction lastDir;

    /**
     *
     */
    public MovingObstacle(){
        this.lastDir = new Direction(0,0);
    }

    /**
     *  Allow the obstacle to move in a direction
     * @param adjacentCases Array or Array of Case around th current position of the Obstacle
     * @return The direction of the movement
     */
    public Direction move(ArrayList<ArrayList<Case>> adjacentCases) {
        Direction dir;

        int x = PseudoRandom.getRandomNumberInRange(-1, 1); // -1, 0 or 1
        int y = PseudoRandom.getRandomNumberInRange(-1, 1); // -1, 0 or 1
        dir = new Direction(x, y);

        // Prevent going outside the Map, now still blocked by Obstacles
        if (adjacentCases.get(1).get(1+dir.getY()) == null) dir.setY(0);
        if (adjacentCases.get(1+dir.getX()).get(1) == null) dir.setX(0);

        if (dir.getX() == 0 && dir.getY() == 0) {
            dir.setLength(0);
        } else {
            dir.setLength(1);
        }

        this.lastDir = dir;
        return dir;
    }

    @Override
    public String getImage() {
        return "tornado";
    }
}
