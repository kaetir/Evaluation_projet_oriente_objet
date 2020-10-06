package model.Entities;

import java.util.Collections;

public class MasterMerchant extends Merchant {

    private static final MasterMerchant instance = new MasterMerchant();
    private MasterMerchant() {}

    public static MasterMerchant getInstance() {return MasterMerchant.instance;}

    public void reset() {
        this.goods.clear();
        Collections.addAll(this.goods, Merchant.merchantGoods);
    }

    @Override
    public char getPrintable() {
        return 'M';
    }

    @Override
    public String getImage() {
        return super.getImage();
    }
}
