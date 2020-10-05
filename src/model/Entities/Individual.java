package model.Entities;

import model.Token;

import java.util.ArrayList;

public abstract class Individual extends Token {

    protected ArrayList<String> goods = new ArrayList<String>(); /// Goods carried by every individual
    protected int energyPoints = 0; /// Energy points before an individual will collapse
    protected int[] lastDirection = {0,0}; /// Last direction taken by an individual {x,y}, ex: {-1,1} means it went to South-West (so it comes from North-East)

    // public abstract void move();
}