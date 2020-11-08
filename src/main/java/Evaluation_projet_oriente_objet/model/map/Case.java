package main.java.Evaluation_projet_oriente_objet.model.map;


import javafx.scene.paint.Color;
import main.java.Evaluation_projet_oriente_objet.model.Token;

/**
 * Main class from which wil inherit every Cases
 * The map is made of Case objects and this is where token will be put
 */
public class Case {
    protected Token token = null;

    protected Color color = Color.AQUA;

    public Case() {}

    /**
     * get Printable will display the content of the case  if the token exist
     * @return a char of what will is the case
     */
    public char getPrintable() {
        if(this.getToken() == null)
            return '.';
        return this.getToken().getPrintable();
    }

    /**
     * Return the color for the case
     * @return a color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the token for the case depending from its use
     * @param token
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * Getter for the case's token
     * @return the token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Check if the case has a token
     * @return success
     */
    public boolean hasToken() {
        return token != null;
    }
    
}
