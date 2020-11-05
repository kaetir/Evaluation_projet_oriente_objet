package main.java.Evaluation_projet_oriente_objet.model.map;


import javafx.scene.paint.Color;
import main.java.Evaluation_projet_oriente_objet.model.Token;


public class Case {
    protected Token token = null;

    protected Color color = Color.AQUA;

    public Case() {

    }

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

    public Token getToken() {
        return token;
    }

    public boolean hasToken() {
        return token != null;
    }
    
}
