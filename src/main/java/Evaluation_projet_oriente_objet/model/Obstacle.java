package main.java.Evaluation_projet_oriente_objet.model;

public abstract class Obstacle extends Token {
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
    public abstract String getImage() ;
}
