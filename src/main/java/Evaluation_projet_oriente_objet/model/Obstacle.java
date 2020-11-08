package main.java.Evaluation_projet_oriente_objet.model;

/**
 * Class from obstacle tokens
 */
public abstract class Obstacle extends Token {
    @Override
    public char getPrintable() {
        return 'O';
    }

    @Override
    public abstract String getImage() ;
}
