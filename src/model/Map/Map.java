package model.Map;


import java.util.ArrayList;

import model.Entities.*;

public class Map {

    static private final int defaultSafeZone = 2;

    private ArrayList<ArrayList<Case>> map;

    public Map(int width, int height, int safeZoneWidth, int safeZoneHeight) {
        this.map = new ArrayList<ArrayList<Case>>();

        // Minimum Values
        if (safeZoneWidth < Map.defaultSafeZone) {
            safeZoneWidth = Map.defaultSafeZone;
        }
        if (safeZoneHeight < Map.defaultSafeZone) {
            safeZoneHeight = Map.defaultSafeZone;
        }
        if (width < 4 * safeZoneWidth) {
            width = 4 * safeZoneWidth;
        }
        if (height < 4 * safeZoneHeight) {
            height = 4 * safeZoneHeight;
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
