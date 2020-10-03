package model.Entities;

public class Merchant extends Good {

    public Merchant() {
        this.goods.add("Wood");
        this.goods.add("Clothes");
        this.goods.add("Tea");
    }

    @Override
    public char getPrintable() {
        return 'm';
    }
}
