package model.map;


import javafx.scene.paint.Color;
import model.Token;


public class Case {
    protected Token token = null;

    protected Color color = Color.AQUA;

    public Case() {

    }

    public char getPrintable() {
        if(this.getToken() == null)
            return '.';
        return this.getToken().getPrintable();
    }

    public Color getColor() {
        return color;
    }

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