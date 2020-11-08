package main.java.Evaluation_projet_oriente_objet.model;

/**
 * Class for Obstacle which cannot move
 */
public class FixObstacle extends Obstacle {

    @Override
    public String getImage() {
        return "island";
    }
}
