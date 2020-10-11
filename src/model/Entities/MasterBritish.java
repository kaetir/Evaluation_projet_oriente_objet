package model.Entities;

import java.util.Collections;

public class MasterBritish extends British implements Master {

    private static final MasterBritish instance = new MasterBritish();
    private MasterBritish() {}

    public static MasterBritish getInstance() {return MasterBritish.instance;}

    public void reset() {
        this.goods.clear();
        Collections.addAll(this.goods, British.britishGoods);
    }

    @Override
    public char getPrintable() {
        return 'B';
    }

    @Override
    public String getImage() {
        return "master_british";
    }

}
