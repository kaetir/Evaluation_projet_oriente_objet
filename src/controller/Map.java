package controller;

import java.util.ArrayList;

public class Map {
    static private int defaultSafeZone = 2;

    private ArrayList<ArrayList<Case>> map;

    public Map(int width, int height, int safeZoneWidth, int safeZoneHeight) {
        this.map = new ArrayList<ArrayList<Case>>();

        if (safeZoneWidth < Map.defaultSafeZone) {
            safeZoneWidth = Map.defaultSafeZone;
        }
        if (safeZoneHeight < Map.defaultSafeZone) {
            safeZoneHeight = Map.defaultSafeZone;
        }
        if (width < 2 * safeZoneWidth) {
            width = 2 * safeZoneWidth;
        }
        if (height < 2 * safeZoneHeight) {
            height = 2 * safeZoneHeight;
        }

        // Generating Map
        for (int i = 0; i < width; i++) {
            ArrayList<Case> column = new ArrayList<Case>();
            for (int j = 0; j < height; j++) {
                if (i < safeZoneWidth && j < safeZoneHeight) {
                    column.add(new SafeCaseBritish());
                } else if (i >= width - safeZoneWidth && j < safeZoneHeight) {
                    column.add(new SafeCaseUndead());
                } else if (i < safeZoneWidth && j >= height - safeZoneHeight) {
                    column.add(new SafeCasePirate());
                } else if (i >= width - safeZoneWidth && j >= height - safeZoneHeight) {
                    column.add(new SafeCaseMerchant());
                } else {
                    column.add(new Case());
                }
            }
            this.map.add(column);
        }
    }

    public void printMap() {
        /*
            Print Map with ASCII Characters
         */

        for (int j = 0; j < this.map.get(0).size(); j++) {
            for (int i = 0; i < this.map.size(); i++) {

            }
        }
    }
}
