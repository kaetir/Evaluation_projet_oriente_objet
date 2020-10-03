package model.Map;

import javafx.scene.paint.Color;

public class SafeCase extends Case {

    public SafeCase() {
    }

    @Override
    public char getPrintable() {
        return ':';
    }

    public void setColor(Color c) {
        this.color = c;
    }
}
