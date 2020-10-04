package model.Entities;

import java.util.Collections;

public class Undead extends Mechant {
    public static final String[] undeadGoods = {"Ashen Skulls", "Tridents", "Souls"};

    public Undead() {
        Collections.addAll(this.goods, Undead.undeadGoods);
    }

    @Override
    public char getPrintable() {
        return 'u';
    }
}
