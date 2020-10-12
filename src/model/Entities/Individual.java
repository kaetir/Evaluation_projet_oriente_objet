package model.Entities;

import controller.PseudoRandom;
import model.Map.Case;
import model.Map.Map;
import model.Token;
import utils.Direction;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Individual extends Token {

    private static final int defaultEnergyPoints = 100;
    private static int getAllianceSharingNumber() {
        /*
        How many goods should individual in an alliance share ?
         */
        return PseudoRandom.getRandomNumberInRange(1,3);
    }

    protected ArrayList<String> goods = new ArrayList<String>(); /// Goods carried by every individual
    protected int energyPoints = 0; /// Energy points before an individual will collapse
    protected Direction lastDirection = new Direction(0, 0); /// Last direction taken by an individual {x,y}, ex: {-1,1} means it went to South-West (so it comes from North-East)

    protected Individual () {
        this.energyPoints = Individual.defaultEnergyPoints;
    }

    public int getEnergyPoints() {
        return this.energyPoints;
    }

    public boolean checkHasEveryGoods() {
        /*
        Check if individual own every goods of the world
         */
        for (String good: British.britishGoods) {
            if (!this.goods.contains(good)) return false;
        }
        for (String good: Undead.undeadGoods) {
            if (!this.goods.contains(good)) return false;
        }
        for (String good: Pirate.pirateGoods) {
            if (!this.goods.contains(good)) return false;
        }
        for (String good: Merchant.merchantGoods) {
            if (!this.goods.contains(good)) return false;
        }
        return true;
    }

    public void restoreEnergy() {
        this.energyPoints = Individual.defaultEnergyPoints;
    }

    public void steal(Individual other) {
        // TODO refaire cette fonction avec le pseudo aléatoire
        Collections.shuffle(other.goods, PseudoRandom.getGenerator());
        for (String good: other.goods) {
            if (!this.goods.contains(good)) {
                this.goods.add(good); // We steal Only one good we do not own
                return;
            }
        }
    }

    public int battleRandom() {
        return PseudoRandom.getRandom() * 10;
    }

    public void encounter(Individual other) {
        if ((other instanceof British && this instanceof British)
            || (other instanceof Undead && this instanceof Undead)
            || (other instanceof Pirate && this instanceof Pirate)
            || (other instanceof Merchant && this instanceof Merchant)) {
            // Same Population, Sharing !
            other.share(goods);
            this.share(other.goods); // Same class, We can access it

        } else if ((other instanceof Good && this instanceof Good)
            || (other instanceof Bad && this instanceof Bad)) {
            // Same Alliance, Little Sharing !
            ArrayList<String> littleGoods = new ArrayList<String>();
            ArrayList<String> littleGoodsOther = new ArrayList<String>();
            Collections.shuffle(this.goods, PseudoRandom.getGenerator());
            for (int i = 0; (i < Individual.getAllianceSharingNumber() && i < this.goods.size()); i++) {
                littleGoods.add(this.goods.get(i));
            }
            Collections.shuffle(other.goods, PseudoRandom.getGenerator());
            for (int i = 0; (i < Individual.getAllianceSharingNumber() && i < other.goods.size()); i++) {
                littleGoodsOther.add(other.goods.get(i));
            }

            other.share(littleGoods);
            this.share(littleGoodsOther); // Same class, We can access it

        } else {
            // Enemies, Battle !
            int myValue = this.battleRandom();
            int otherValue = this.battleRandom();

            if (myValue > otherValue) {
                // I won
                this.steal(other);
            } else if (myValue < otherValue) {
                // I lose
                other.steal(this);
            } else {
                // Tie, nothing Happens
            }

        }
    }

    public void share(ArrayList<String> goods) {
        /*
        Individual sharing every goods he own with another one
         */
        for (String good: goods) {
            if (!this.goods.contains(good)) this.goods.add(good);
        }
    }

    public abstract void shareMaster(); // Share ressources to his master

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
            int x = PseudoRandom.getRandomNumberInRange(-1, 1); // -1, 0 or 1
            int y = PseudoRandom.getRandomNumberInRange(-1, 1); // -1, 0 or 1
            direction = new Direction(x, y);
        }

        // Prevent going outside the Map, now still blocked by Obstacles
        if (adjacentCases.get(1).get(1+direction.getY()) == null) direction.setY(0);
        if (adjacentCases.get(1+direction.getX()).get(1) == null) direction.setX(0);

        int length = PseudoRandom.getRandomNumberInRange(0, 9);

        direction.setLength(length);

        this.energyPoints -= length;

        this.lastDirection = direction;
        return this.lastDirection;
    }


    @Override
    public String toString() {
        String[] className = this.getClass().toString().split("\\.");
        if (className.length != 0)
            return className[className.length-1] + " EP=" + String.format("%03d", energyPoints) + " goods=" + goods ;
        return "EP=" + String.format("%03d", energyPoints) + " goods=" + goods ;
    }
}
