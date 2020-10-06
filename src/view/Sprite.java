package view;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Sprite {

    public static Map<String, String> map = new HashMap<String, String>();

    public static Sprite sprite = new Sprite();

    private Sprite() {
        Sprite.map.put("default", "https://icon-icons.com/downloadimage.php?id=100556&root=1465/PNG/512/&file=095pileofpoo_100556.png");
        Sprite.map.put("obstacle", "obstacle.png");
        Sprite.map.put("island", "res/island.png");
        Sprite.map.put("master_pirate", "res/master_pirate.png");
        Sprite.map.put("master_british", "res/master_british.png");
        Sprite.map.put("master_undead", "res/master_undead.png");
        Sprite.map.put("master_merchant", "res/master_merchant.png");


    }

    public static Image load(String location){
        return load(location, 100, 100);
    }

    public static Image load(String location, double sizeX, double sizeY){
        try{
            return new Image(location, sizeX, sizeY, false, false);
        }catch (Exception e){
            return new Image("https://vignette.wikia.nocookie.net/hypixel-skyblock/images/b/bc/Null_Block.png/revision/latest/scale-to-width-down/340?cb=20200110153845",sizeX, sizeY, false, false);
        }
    }




}
