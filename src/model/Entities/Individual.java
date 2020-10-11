package model.Entities;

import model.Map.Case;
import model.Map.Map;
import model.Token;
import utils.Direction;

import java.util.ArrayList;

public abstract class Individual extends Token {

    private static final int defaultEnergyPoints = 100;

    protected ArrayList<String> goods = new ArrayList<String>(); /// Goods carried by every individual
    protected int energyPoints = 0; /// Energy points before an individual will collapse
    protected Direction lastDirection = new Direction(0, 0); /// Last direction taken by an individual {x,y}, ex: {-1,1} means it went to South-West (so it comes from North-East)

    protected Individual () {
        this.energyPoints = Individual.defaultEnergyPoints;
    }

    public void restoreEnergy() {
        this.energyPoints = Individual.defaultEnergyPoints;
    }

    public void encounter(Individual other) {

    }


    public Direction move(ArrayList< ArrayList<Case> > adjacentCases) {
        /*
        *
        * adjacentCases: Matrix containing adjacent Cases
        * */

        Direction direction = null;

        if (energyPoints <= Individual.defaultEnergyPoints * 0.2) { // Getting back to the safe Zone, Dumb
            if (this instanceof British) {direction = Map.britishSafeDirection;}
            else if (this instanceof Undead) {direction = Map.undeadSafeDirection;}
            else if (this instanceof Pirate) {direction = Map.pirateSafeDirection;}
            else if (this instanceof Merchant) {direction = Map.merchantSafeDirection;}
        }

        if (direction == null) {
            int x = (int) (Math.random() * 3) - 1; // -1, 0 or 1
            int y = (int) (Math.random() * 3) - 1; // -1, 0 or 1
            direction = new Direction(x, y);
        }

        // Prevent going outside the Map, now still blocked by Obstacles
        if (adjacentCases.get(1).get(1+direction.getY()) == null) direction.setY(0);
        if (adjacentCases.get(1+direction.getX()).get(1) == null) direction.setX(0);

        int length = (int) (Math.random() * 9 + 1);

        direction.setLength(length);

        this.energyPoints -= length;

        this.lastDirection = direction;
        return this.lastDirection;
    }
}
