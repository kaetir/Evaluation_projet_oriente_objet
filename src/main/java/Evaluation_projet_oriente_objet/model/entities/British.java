package main.java.Evaluation_projet_oriente_objet.model.entities;

import java.util.Collections;

/**
 * British Tokens Class
 */
public class British extends Good {
    public static final String[] britishGoods = {"Gold", "Silver", "Copper"};

    public British() {
        super();
        Collections.addAll(this.goods, British.britishGoods);
    }

    /**
     * Share its goods to its master
     */
    public void shareMaster() {
        MasterBritish.getInstance().share(this.goods);
    }

    /**
     * Print a 'b' on the map
     * @return custom character that represents a british
     */
    @Override
    public char getPrintable() {
        return 'b';
    }

    /**
     * Get the name of the british boat's image
     * @return boat's img name
     */
    @Override
    public String getImage() {
        return "boat_british";
    }
}
