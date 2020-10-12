package model.map;


import java.util.ArrayList;
import java.util.Collections;

import controller.PseudoRandom;
import model.entities.*;
import model.Obstacle;
import model.Token;
import utils.Direction;


public class Map {

    static private final int minimumSafeZone = 2;
    static public final Direction britishSafeDirection = new Direction(-1, -1);
    static public final Direction undeadSafeDirection = new Direction(1, -1);
    static public final Direction pirateSafeDirection = new Direction(-1, 1);
    static public final Direction merchantSafeDirection = new Direction(1, 1);

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
                    if (i == 0 && j == 0) {
                        tempCase.setToken(MasterBritish.getInstance());
                    } else {
                        tempCase.setToken(new British());
                    }

                } else if (i >= width - safeZoneWidth && j < safeZoneHeight) {
                    tempCase = new SafeCaseUndead();
                    if (i == width - 1 && j == 0) {
                        tempCase.setToken(MasterUndead.getInstance());
                    } else {
                        tempCase.setToken(new Undead());
                    }

                } else if (i < safeZoneWidth && j >= height - safeZoneHeight) {
                    tempCase = new SafeCasePirate();
                    if (i == 0 && j == height - 1) {
                        tempCase.setToken(MasterPirate.getInstance());
                    } else {
                        tempCase.setToken(new Pirate());
                    }

                } else if (i >= width - safeZoneWidth && j >= height - safeZoneHeight) {
                    tempCase = new SafeCaseMerchant();
                    if (i == width - 1 && j == height - 1) {
                        tempCase.setToken(MasterMerchant.getInstance());
                    } else {
                        tempCase.setToken(new Merchant());
                    }

                } else {
                    tempCase = new Case();
                    if ( PseudoRandom.getRandomNumberInRange(0, 100)  < randomObstacleChance * 100)
                        tempCase.setToken(new Obstacle());
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

    public ArrayList< ArrayList<Case> > getAdjacentCases(int x, int y) {
        ArrayList< ArrayList<Case> > arrayCases = new ArrayList< ArrayList<Case> >();
        for (int i = -1; i < 2; i++) {
            arrayCases.add(new ArrayList<Case>());
            for (int j = -1; j < 2; j++) {
                arrayCases.get(i+1).add(this.getCase(x+i, y+j));
            }
        }
        return arrayCases;
    }

    public Case getCase(int x, int y) {
        if (x < 0 || y < 0 || x >= this.map.size() || y >= this.map.get(0).size()) return null;
        return this.map.get(x).get(y);
    }

    private static class PackIndividualPosition {
        public Individual individual;
        public int x;
        public int y;

        public PackIndividualPosition (Individual indi, int x, int y) {
            this.individual = indi;
            this.x = x;
            this.y = y;
        }
    }
    
    public ArrayList<PackIndividualPosition> getEveryIndividualsMoving() {
        /*
        Return every Individual with energyPoints > 0 on the map and their position
         */
        ArrayList<PackIndividualPosition> individuals = new ArrayList<PackIndividualPosition>();

        for (int i = 0; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(0).size(); j++) {
                Token tokenTemp = this.map.get(i).get(j).getToken();
                if (tokenTemp == null) continue;
                if (tokenTemp instanceof Master) continue; // Masters Do not move
                if (tokenTemp instanceof Individual) {
                    if (((Individual) tokenTemp).getEnergyPoints() <= 0) continue;
                    individuals.add(new PackIndividualPosition((Individual) tokenTemp, i, j));
                }
            }
        }

        return individuals;
    }

    public ArrayList<Master> checkWin() {
        /*
        Check whether masters won the simulation (Array because we could have a Tie)
         */
        ArrayList<Master> winners = new ArrayList<Master>();

        if (MasterBritish.getInstance().checkHasEveryGoods()) winners.add(MasterBritish.getInstance());
        if (MasterUndead.getInstance().checkHasEveryGoods()) winners.add(MasterUndead.getInstance());
        if (MasterPirate.getInstance().checkHasEveryGoods()) winners.add(MasterPirate.getInstance());
        if (MasterMerchant.getInstance().checkHasEveryGoods()) winners.add(MasterMerchant.getInstance());

        return winners;
    }

    public boolean step() {
        ArrayList<PackIndividualPosition> individuals = this.getEveryIndividualsMoving();

        if (individuals.size() == 0) return false;

        Collections.shuffle(individuals, PseudoRandom.getGenerator());

        for (PackIndividualPosition pack: individuals) {
            Direction indiDirection = pack.individual.move(this.getAdjacentCases(pack.x, pack.y));
            int length = indiDirection.getLength();
            int dirX = indiDirection.getX();
            int dirY = indiDirection.getY();

            int newX = pack.x;
            int newY = pack.y;

            while (length > 0) {
                Case tempCase = this.getCase(newX + dirX, newY + dirY);
                if (tempCase == null) break; // Out of Bounds
                Token tempToken = tempCase.getToken();

                // Cannot encounter and go in safe
                if (tempCase instanceof SafeCase &&
                 ( pack.individual instanceof British && !(tempCase instanceof SafeCaseBritish)
                || pack.individual instanceof Undead && !(tempCase instanceof SafeCaseUndead)
                || pack.individual instanceof Pirate && !(tempCase instanceof SafeCasePirate)
                || pack.individual instanceof Merchant && !(tempCase instanceof SafeCaseMerchant))) {

                    break; // STOP

                } else if (tempToken != null) {
                    // OBSTACLE!
                    if (tempToken instanceof Individual) { // ENCOUNTER!
                        pack.individual.encounter((Individual) tempToken);
                    }
                    break;

                } else {
                    newX += dirX;
                    newY += dirY;
                    length--;
                }
            }

            // Changing position
            this.getCase(pack.x, pack.y).setToken(null);
            this.getCase(newX, newY).setToken(pack.individual);

            if (this.getCase(newY, newX) instanceof SafeCase) {
                pack.individual.restoreEnergy();

                pack.individual.shareMaster();
            }
        }
        return true; // Step Worked
    }


    
    public void printMapASCII() {
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
