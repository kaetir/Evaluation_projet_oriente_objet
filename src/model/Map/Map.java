package model.Map;


import java.util.ArrayList;

import model.Entities.*;
import model.Obstacle;

public class Map {

    static private final int minimumSafeZone = 2;

    private ArrayList<ArrayList<Case>> map;

    private void generateMap(int width, int height, int safeZoneWidth, int safeZoneHeight, double randomObstacleChance) {
        this.map = new ArrayList<ArrayList<Case>>();

        // Minimum Values
        if (safeZoneWidth < Map.minimumSafeZone) {
            safeZoneWidth = Map.minimumSafeZone;
        }
        if (safeZoneHeight < Map.minimumSafeZone) {
            safeZoneHeight = Map.minimumSafeZone;
        }
        if (width < 4 * safeZoneWidth) {
            width = 4 * safeZoneWidth;
        }
        if (height < 4 * safeZoneHeight) {
            height = 4 * safeZoneHeight;
        }

        // Reset Masters
        MasterBritish.getInstance().reset();
        MasterUndead.getInstance().reset();
        MasterPirate.getInstance().reset();
        MasterMerchant.getInstance().reset();

        // Generating Map
        for (int i = 0; i < width; i++) {
            ArrayList<Case> column = new ArrayList<Case>();
            for (int j = 0; j < height; j++) {
                Case tempCase;
                if (i < safeZoneWidth && j < safeZoneHeight) {
                    tempCase = new SafeCaseBritish();
                    if (i == 0 && j == 0) tempCase.setToken(MasterBritish.getInstance());

                } else if (i >= width - safeZoneWidth && j < safeZoneHeight) {
                    tempCase = new SafeCaseUndead();
                    if (i == width - 1 && j == 0) tempCase.setToken(MasterUndead.getInstance());

                } else if (i < safeZoneWidth && j >= height - safeZoneHeight) {
                    tempCase = new SafeCasePirate();
                    if (i == 0 && j == height - 1) tempCase.setToken(MasterPirate.getInstance());

                } else if (i >= width - safeZoneWidth && j >= height - safeZoneHeight) {
                    tempCase = new SafeCaseMerchant();
                    if (i == width - 1 && j == height - 1) tempCase.setToken(MasterMerchant.getInstance());

                } else {
                    tempCase = new Case();
                    if (Math.random() < randomObstacleChance) tempCase.setToken(new Obstacle());
                }
                column.add(tempCase);
            }
            this.map.add(column);
        }
    }

    public Map(int width, int height, int safeZoneWidth, int safeZoneHeight) {
        this.generateMap(width, height, safeZoneWidth, safeZoneHeight, 0.05);
    }
    public Map() {
        this.generateMap(12, 8, 3, 2, 0.05);
    }

    public ArrayList<Case> getAdjacentCases(int x, int y) {
        ArrayList<Case> arrayCases = new ArrayList<Case>();
        return arrayCases;
    }

    public Case getCase(int x, int y) {
        if (x < 0 || y < 0 || x >= this.map.size() || y >= this.map.get(0).size()) return null;
        return this.map.get(x).get(y);
    }
/*
    public int[] getSafeZoneDirection(Individual token) {
        if (token instanceof British) return {-1,-1};
    }
*/
    public void printMap() {
        /*
            Print Map with ASCII Characters
         */
        for (int i = 0; i < this.map.size() + 1; i++) {
            System.out.print("-");
        }
        System.out.println("-");

        for (int j = 0; j < this.map.get(0).size(); j++) {
            System.out.print("|");

            for (int i = 0; i < this.map.size(); i++) {
                Case tempCase = this.map.get(i).get(j);
                if (tempCase.getToken() == null) {
                    System.out.print(tempCase.getPrintable());
                } else {
                    System.out.print(tempCase.getToken().getPrintable());
                }
            }

            System.out.println("|");
        }

        for (int i = 0; i < this.map.size() + 1; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    public ArrayList<ArrayList<Case>> getMap() {
        return map;
    }
}
