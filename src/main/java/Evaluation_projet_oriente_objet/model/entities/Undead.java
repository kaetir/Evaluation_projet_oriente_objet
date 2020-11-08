package main.java.Evaluation_projet_oriente_objet.model.entities;

import java.util.Collections;

/**
 * Undead Tokens Class
 */
public class Undead extends Bad {
    public static final String[] undeadGoods = {"Ashen Skulls", "Tridents", "Souls"};

    public Undead() {
        super();
        Collections.addAll(this.goods, Undead.undeadGoods);
    }

    /**
     * Share its goods to its master
     */
    public void shareMaster() {
        MasterUndead.getInstance().share(this.goods);
    }

    /**
     * Print a 'u' on the map
     * @return custom character that represents a undead
     */
    @Override
    public char getPrintable() {
        return 'u';
    }

    /**
     * Get the name of the undead boat's image
     * @return boat's img name
     */
    @Override
    public String getImage() {
        return "boat_undead";
    }
}
