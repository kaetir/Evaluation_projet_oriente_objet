package model.Map;


import model.*;
import javafx.scene.paint.Color;


public class Case {
    protected Token token = null;

    protected Color color = Color.AQUA;

    public Case() {

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
}
