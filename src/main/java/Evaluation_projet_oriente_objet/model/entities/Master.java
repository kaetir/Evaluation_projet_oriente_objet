package main.java.Evaluation_projet_oriente_objet.model.entities;

import java.util.ArrayList;

public interface Master {

    void reset();

    char getPrintable();

    String getImage();

    boolean checkHasEveryGoods();

    ArrayList<String> getGoods();

}
