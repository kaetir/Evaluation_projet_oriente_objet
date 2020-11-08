package main.java.Evaluation_projet_oriente_objet.model;

/**
 * Main class from which inherit evrey token which could be put on the map
 */
public abstract class Token {

    /**
     * Get a console printable character representing the token
     * @return a character
     */
    public char getPrintable() {
        /*
        Return a printable character depending on the token, must be override
        otherwise a '?' will appear
         */
        return '?';
    }

    /**
     * Get an image name representing the token for JaaFX
     * @return an image name
     */
    public String getImage() {
        /*
        Return a the reference in the Sprite map depending on the token, must be override
        otherwise a 'Poop' will appear
         */

        return "default";
    }
}
