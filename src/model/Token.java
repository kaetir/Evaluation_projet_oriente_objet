package model;

public abstract class Token {
    public char getPrintable() {
        /*
        Return a printable character depending on the token, must be override
        otherwise a '?' will appear
         */
        return '?';
    }

    public String getImage() {
        /*
        Return a the reference in the Sprite map depending on the token, must be override
        otherwise a 'Poop' will appear
         */

        return "default";
    }
}
