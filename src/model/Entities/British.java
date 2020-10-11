package model.Entities;

import java.util.Collections;

public class British extends Good {
    public static final String[] britishGoods = {"Gold", "Silver", "Copper"};

    public British() {
        super();
        Collections.addAll(this.goods, British.britishGoods);
    }

    @Override
    public char getPrintable() {
        return 'b';
    }

    @Override
    public String getImage() {
        return "boat_british";
    }
}
