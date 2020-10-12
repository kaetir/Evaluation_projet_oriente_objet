package model.map;

import javafx.scene.paint.Color;

public class SafeCase extends Case {

    public SafeCase() {
    }

    /**
     * On redéfini la fonction de Case selon chaque équipe
     * @return
     */
    public char getPrintable() {
        if(this.getToken() == null)
            return ':';
        return this.getToken().getPrintable();
    }

    /**
     * On initialise ici la couleur que prend les cases des safe zones
     * @param c la couleur c est précisé selon l'équipe de la safe zone.
     */
    public void setColor(Color c) {
        this.color = c;
    }
}
