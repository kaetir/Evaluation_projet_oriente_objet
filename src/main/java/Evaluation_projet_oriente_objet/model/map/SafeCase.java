package main.java.Evaluation_projet_oriente_objet.model.map;

import javafx.scene.paint.Color;

public class SafeCase extends Case {

    public SafeCase() {
    }

    /**
     * Redefine the function of the Case according to every team
     * @return the printable char of the Token
     */
    public char getPrintable() {
        if(this.getToken() == null)
            return ':';
        return this.getToken().getPrintable();
    }

    /**
     * initialise the color of the cases for safe zones
     * @param c the color is overwritten for every team for the safe zone.
     */
    public void setColor(Color c) {
        this.color = c;
    }
}
