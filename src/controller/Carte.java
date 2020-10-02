package controller;

import java.util.ArrayList;

public class Carte {
    static private int defaultSafeZone = 2;

    private ArrayList<ArrayList<Case>> map;

    public Carte(int width, int height, int safeZoneWidth, int safeZoneHeight) {
        this.map = new ArrayList<ArrayList<Case>>();

        if (safeZoneWidth < Carte.defaultSafeZone) {
            safeZoneWidth = Carte.defaultSafeZone;
        }
        if (safeZoneHeight < Carte.defaultSafeZone) {
            safeZoneHeight = Carte.defaultSafeZone;
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
                    column.add(new SafeCaseBritannique());
                } else if (i >= width - safeZoneWidth && j < safeZoneHeight) {
                    column.add(new SafeCaseTrepasse());
                } else if (i < safeZoneWidth && j >= height - safeZoneHeight) {
                    column.add(new SafeCasePirate());
                } else if (i >= width - safeZoneWidth && j >= height - safeZoneHeight) {
                    column.add(new SafeCaseMarchand());
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
