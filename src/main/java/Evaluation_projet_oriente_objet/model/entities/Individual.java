package main.java.Evaluation_projet_oriente_objet.model.entities;

import main.java.Evaluation_projet_oriente_objet.controller.PseudoRandom;
import main.java.Evaluation_projet_oriente_objet.model.map.Case;
import main.java.Evaluation_projet_oriente_objet.model.map.Map;
import main.java.Evaluation_projet_oriente_objet.model.Token;
import main.java.Evaluation_projet_oriente_objet.utils.Direction;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Individual extends Token {

    private static final int defaultEnergyPoints = 100;

    /**
     * Get the number of goods that is share inside an alliance
     * @return a number between 1 and 3
     */
    private static int getAllianceSharingNumber() {
        return PseudoRandom.getRandomNumberInRange(1,3);
    }

    protected ArrayList<String> goods = new ArrayList<>(); /// Goods carried by every individual
    protected int energyPoints; /// Energy points before an individual will collapse
    protected Direction lastDirection = new Direction(0, 0); /// Last direction taken by an individual {x,y}, ex: {-1,1} means it went to South-West (so it comes from North-East)

    protected Individual () {
        this.energyPoints = Individual.defaultEnergyPoints;
    }

    /**
     * Get the energy points of the individual
     * @return the value of energyPoints
     */
    public int getEnergyPoints() {
        return this.energyPoints;
    }

    /**
     * Check if individual own every goods of the world by looping through all the type of individual
     * @return true if it has all the goods, false otherwise
     */
    public boolean checkHasEveryGoods() {
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

    /**
     * Set the energy points to the default value
     */
    public void restoreEnergy() {
        this.energyPoints = Individual.defaultEnergyPoints;
    }

    /**
     * Steal a good from another individual
     * @param other individual
     */
    public void steal(Individual other) {
        Collections.shuffle(other.goods, PseudoRandom.getGenerator());
        for (String good: other.goods) {
            if (!this.goods.contains(good)) {
                this.goods.add(good); // We steal Only one good we do not own
                return;
            }
        }
    }

    public void looseItem(int x){
        int y = x % this.goods.size();
        for(int i = 0; i<y; i++){
            this.goods.remove(0);
        }
    }

    /**
     * Get a random value
     * @return a random value which is multiply by 10
     */
    public int battleRandom() {
        return PseudoRandom.getRandom() * 10;
    }

    /**
     * If this individual and the other one are the same class, they exchange goods
     * Else if this individual and the other one are allies, they exchange random goods
     * Else if they are enemies, they fight and the winner steals the looser
     * @param other individual
     */
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
            ArrayList<String> littleGoods = new ArrayList<>();
            ArrayList<String> littleGoodsOther = new ArrayList<>();
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

    /**
     * Add undiscovered goods to the goods list of the individual
     * @param goods that are shared
     */
    public void share(ArrayList<String> goods) {
        for (String good: goods) {
            if (!this.goods.contains(good)) this.goods.add(good);
        }
    }

    /**
     * Share ressources to his master
     */
    public abstract void shareMaster();

    /**
     * Move the individual to a direction
     * @param adjacentCases around the individual on the map
     * @return a Direction to follow
     */
    public Direction move(ArrayList< ArrayList<Case> > adjacentCases) {
        /*
        *
        * adjacentCases: Matrix containing adjacent Cases
        * */

        Direction direction = null;

        // Getting back to the safe Zone, Dumb
        // if energy low or all the goods are mine
        if (energyPoints <= Individual.defaultEnergyPoints * 0.2 || this.checkHasEveryGoods()) {
            if (this instanceof British) direction = Map.getBritishSafeDirection();
            else if (this instanceof Undead) direction = Map.getUndeadSafeDirection();
            else if (this instanceof Pirate) direction = Map.getPirateSafeDirection();
            else if (this instanceof Merchant) direction = Map.getMerchantSafeDirection();
        }

        if (direction == null) {
            int x = PseudoRandom.getRandomNumberInRange(-1, 1); // -1, 0 or 1
            int y = PseudoRandom.getRandomNumberInRange(-1, 1); // -1, 0 or 1
            direction = new Direction(x, y);
        }

        // Prevent going outside the Map, now still blocked by Obstacles
        if (adjacentCases.get(1).get(1+direction.getY()) == null) direction.setY(0);
        if (adjacentCases.get(1+direction.getX()).get(1) == null) direction.setX(0);

        int length = PseudoRandom.getRandomNumberInRange(1, 3);

        if (direction.getX() == 0 && direction.getY() == 0) {
            length = 0;
        }

        direction.setLength(length);

        this.energyPoints -= length;

        this.lastDirection = direction;
        return this.lastDirection;
    }


    /**
     * @return information of the individual in a string format
     */
    @Override
    public String toString() {
        String[] className = this.getClass().toString().split("\\.");
        if (className.length != 0)
            return className[className.length-1] + " EP=" + String.format("%03d", energyPoints) + " goods=" + goods ;
        return "EP=" + String.format("%03d", energyPoints) + " goods=" + goods ;
    }

    public ArrayList<String> getGoods() {
        return goods;
    }
}
