package model.Entities;

import java.util.Collections;

public class Undead extends Bad {
    public static final String[] undeadGoods = {"Ashen Skulls", "Tridents", "Souls"};

    public Undead() {
        super();
        Collections.addAll(this.goods, Undead.undeadGoods);
    }

    public void shareMaster() {
        MasterUndead.getInstance().share(this.goods);
    }

    @Override
    public char getPrintable() {
        return 'u';
    }

    @Override
    public String getImage() {
        return "boat_undead";
    }
}
