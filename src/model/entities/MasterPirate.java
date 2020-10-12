package model.entities;

import java.util.Collections;

public class MasterPirate extends Pirate implements Master {

    private static final MasterPirate instance = new MasterPirate();
    private MasterPirate() {}

    /**
     * @return instance of MasterPirate
     */
    public static MasterPirate getInstance() {return MasterPirate.instance;}

    /**
     * Remove all the goods of instance and only add ones that belong to Pirate
     */
    public void reset() {
        this.goods.clear();
        Collections.addAll(this.goods, Pirate.pirateGoods);
    }

    /**
     * Print a 'P' on the map
     * @return custom character that represents a master pirate
     */
    @Override
    public char getPrintable() {
        return 'P';
    }

    /**
     * Get the name of the master pirate boat's image
     * @return master boat's img name
     */
    @Override
    public String getImage() {
        return "master_pirate";
    }

}
