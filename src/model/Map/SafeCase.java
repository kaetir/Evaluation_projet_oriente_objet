package model.Map;

import javafx.scene.paint.Color;

public class SafeCase extends Case {

    public SafeCase() {
    }

    public char getPrintable() {
        if(this.getToken() == null)
            return ':';
        return this.getToken().getPrintable();
    }


    public void setColor(Color c) {
        this.color = c;
    }
}
