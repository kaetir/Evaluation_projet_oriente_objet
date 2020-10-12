package model.entities;

import java.util.Collections;

public class MasterUndead extends Undead implements Master {

    private static final MasterUndead instance = new MasterUndead();
    private MasterUndead() {}

    /**
     * @return instance of MasterUndead
     */
    public static MasterUndead getInstance() {return MasterUndead.instance;}

    /**
     * Remove all the goods of instance and only add ones that belong to Undead
     */
    public void reset() {
        this.goods.clear();
        Collections.addAll(this.goods, Undead.undeadGoods);
    }

    /**
     * Print a 'U' on the map
     * @return custom character that represents a master undead
     */
    @Override
    public char getPrintable() {
        return 'U';
    }

    /**
     * Get the name of the master undead boat's image
     * @return master boat's img name
     */
    @Override
    public String getImage() {
        return "master_undead";
    }

}
