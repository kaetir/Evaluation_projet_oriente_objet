package model.entities;

import java.util.Collections;

public class Merchant extends Good {
    public static final String[] merchantGoods = {"Wood", "Clothes", "Tea"};

    public Merchant() {
        super();
        Collections.addAll(this.goods, Merchant.merchantGoods);
    }

    /**
     * Share its goods to its master
     */
    public void shareMaster() {
        MasterMerchant.getInstance().share(this.goods);
    }

    /**
     * Print a 'm' on the map
     * @return custom character that represents a merchant
     */
    @Override
    public char getPrintable() {
        return 'm';
    }

    /**
     * Get the name of the merchant boat's image
     * @return boat's img name
     */
    @Override
    public String getImage() {
        return "boat_merchant";
    }
}
