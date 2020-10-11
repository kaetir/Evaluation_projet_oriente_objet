package model.Entities;

import java.util.Collections;

public class Pirate extends Mechant {
    public static final String[] pirateGoods = {"Rhum", "Absinth", "Grog"};

    public Pirate() {
        super();
        Collections.addAll(this.goods, Pirate.pirateGoods);
    }

    @Override
    public char getPrintable() {
        return 'p';
    }

    @Override
    public String getImage() {
        return "boat_pirate";
    }
}
