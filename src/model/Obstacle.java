package model;

public class Obstacle extends Token {
    @Override
/**
 * représentation des obstacle avec un O
 */
    public char getPrintable() {
        return 'O';
    }

    @Override
    /**
     * Représentation des obstacle par des images
     */
    public String getImage() {
        return "island";
    }
}
