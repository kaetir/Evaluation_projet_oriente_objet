package model.Entities;

import java.util.Collections;

public class MasterUndead extends Undead {

    private static final MasterUndead instance = new MasterUndead();
    private MasterUndead() {}

    public static MasterUndead getInstance() {return MasterUndead.instance;}

    public void reset() {
        this.goods.clear();
        Collections.addAll(this.goods, Undead.undeadGoods);
    }

    @Override
    public char getPrintable() {
        return 'U';
    }

    @Override
    public String getImage() {
        return "master_undead";
    }

}
