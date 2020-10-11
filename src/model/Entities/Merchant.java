package model.Entities;

import java.util.Collections;

public class Merchant extends Good {
    public static final String[] merchantGoods = {"Wood", "Clothes", "Tea"};

    public Merchant() {
        super();
        Collections.addAll(this.goods, Merchant.merchantGoods);
    }

    public void shareMaster() {
        MasterMerchant.getInstance().share(this.goods);
    }

    @Override
    public char getPrintable() {
        return 'm';
    }

    @Override
    public String getImage() {
        return "boat_merchant";
    }
}
