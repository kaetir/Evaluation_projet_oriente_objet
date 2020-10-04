package model.Entities;

import java.util.Collections;

public class MasterPirate extends Pirate {

    private static final MasterPirate instance = new MasterPirate();
    private MasterPirate() {}

    public static MasterPirate getInstance() {return MasterPirate.instance;}

    public void reset() {
        this.goods.clear();
        Collections.addAll(this.goods, Pirate.pirateGoods);
    }

    @Override
    public char getPrintable() {
        return 'P';
    }
}
