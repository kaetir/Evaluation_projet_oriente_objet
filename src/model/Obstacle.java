package model;

public class Obstacle extends Token {
    @Override
    public char getPrintable() {
        return 'O';
    }

    @Override
    public String getImage() {
        return "island";
    }
}
