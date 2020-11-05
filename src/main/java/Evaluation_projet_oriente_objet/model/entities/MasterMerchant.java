package main.java.Evaluation_projet_oriente_objet.model.entities;

import java.util.Collections;

public class MasterMerchant extends Merchant implements Master {

    private static final MasterMerchant instance = new MasterMerchant();
    private MasterMerchant() {}

    /**
     * @return instance of MasterMerchant
     */
    public static MasterMerchant getInstance() {return MasterMerchant.instance;}

    /**
     * Remove all the goods of instance and only add ones that belong to Merchant
     */
    public void reset() {
        this.goods.clear();
        Collections.addAll(this.goods, Merchant.merchantGoods);
    }

    /**
     * Print a 'M' on the map
     * @return custom character that represents a master merchant
     */
    @Override
    public char getPrintable() {
        return 'M';
    }

    /**
     * Get the name of the master merchant boat's image
     * @return master boat's img name
     */
    @Override
    public String getImage() {
        return "master_merchant";
    }

}
