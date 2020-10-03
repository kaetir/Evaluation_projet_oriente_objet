package model.Entities;

public class Undead extends Mechant {

    public Undead() {
        this.goods.add("Treasure Chest");
        this.goods.add("Ashen Skull");
        this.goods.add("Trident");
    }

    @Override
    public char getPrintable() {
        return 'u';
    }
}
