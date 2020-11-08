package main.java.Evaluation_projet_oriente_objet.model.entities;

import java.util.Collections;

/**
 * British's Master Singleton
 */
public class MasterBritish extends British implements Master {

    private static final MasterBritish instance = new MasterBritish();
    private MasterBritish() {}

    /**
     * @return instance of MasterBritish
     */
    public static MasterBritish getInstance() {return MasterBritish.instance;}

    /**
     * Remove all the goods of instance and only add ones that belong to British
     */
    public void reset() {
        this.goods.clear();
        Collections.addAll(this.goods, British.britishGoods);
    }

    /**
     * Print a 'B' on the map
     * @return custom character that represents a master british
     */
    @Override
    public char getPrintable() {
        return 'B';
    }

    /**
     * Get the name of the master british boat's image
     * @return master boat's img name
     */
    @Override
    public String getImage() {
        return "master_british";
    }

}
