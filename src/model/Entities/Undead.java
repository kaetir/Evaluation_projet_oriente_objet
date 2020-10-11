package model.Entities;

import java.util.Collections;

public class Undead extends Mechant {
    public static final String[] undeadGoods = {"Ashen Skulls", "Tridents", "Souls"};

    public Undead() {
        super();
        Collections.addAll(this.goods, Undead.undeadGoods);
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
