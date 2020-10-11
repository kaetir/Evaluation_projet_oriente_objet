package model.Entities;

import java.util.Collections;

public class Pirate extends Bad {
    public static final String[] pirateGoods = {"Rhum", "Absinth", "Grog"};

    public Pirate() {
        super();
        Collections.addAll(this.goods, Pirate.pirateGoods);
    }

    public void shareMaster() {
        MasterPirate.getInstance().share(this.goods);
    }

    @Override
    public char getPrintable() {
        return 'p';
    }
}
