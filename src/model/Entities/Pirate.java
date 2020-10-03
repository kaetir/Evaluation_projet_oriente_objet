package model.Entities;

public class Pirate extends Mechant {

    public Pirate() {
        this.goods.add("Rhum");
        this.goods.add("Beer");
        this.goods.add("Absinth");
    }

    @Override
    public char getPrintable() {
        return 'p';
    }
}
