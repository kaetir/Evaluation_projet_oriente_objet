package model.Entities;

import java.util.Collections;

public class Merchant extends Good {
    public static final String[] merchantGoods = {"Wood", "Clothes", "Tea"};

    public Merchant() {
        Collections.addAll(this.goods, Merchant.merchantGoods);
    }

    @Override
    public char getPrintable() {
        return 'm';
    }
}
