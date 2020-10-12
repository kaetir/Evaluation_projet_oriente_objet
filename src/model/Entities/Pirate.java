package model.Entities;

import java.util.Collections;

public class Pirate extends Bad {
    public static final String[] pirateGoods = {"Rhum", "Absinth", "Grog"};

    public Pirate() {
        super();
        Collections.addAll(this.goods, Pirate.pirateGoods);
    }

    /**
     * Share its goods to its master
     */
    public void shareMaster() {
        MasterPirate.getInstance().share(this.goods);
    }

    /**
     * Print a 'p' on the map
     * @return custom character that represents a pirate
     */
    @Override
    public char getPrintable() {
        return 'p';
    }

    /**
     * Get the name of the pirate boat's image
     * @return boat's img name
     */
    @Override
    public String getImage() {
        return "boat_pirate";
    }
}
