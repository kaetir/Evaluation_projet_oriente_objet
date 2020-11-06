package main.java.Evaluation_projet_oriente_objet.model.map;


import java.util.*;
import java.util.stream.Collectors;


import main.java.Evaluation_projet_oriente_objet.controller.PseudoRandom;
import main.java.Evaluation_projet_oriente_objet.model.FixObstacle;
import main.java.Evaluation_projet_oriente_objet.model.MovingObstacle;
import main.java.Evaluation_projet_oriente_objet.model.entities.*;
import main.java.Evaluation_projet_oriente_objet.model.Token;
import main.java.Evaluation_projet_oriente_objet.utils.Direction;


public class Map {

    static private final int minimumSafeZone = 2;

    static private final Direction britishSafeDirection = new Direction(-1, -1);
    static private final Direction undeadSafeDirection = new Direction(1, -1);
    static private final Direction pirateSafeDirection = new Direction(-1, 1);
    static private final Direction merchantSafeDirection = new Direction(1, 1);

    private ArrayList<ArrayList<Case>> map;

    private ArrayList<Master> masters = new ArrayList<>();

    /**
     * Safe British Direction Getter
     * @return a Copy of the Direction
     */
    public static Direction getBritishSafeDirection() {
        return new Direction(britishSafeDirection.getX(), britishSafeDirection.getY());
    }
    /**
     * Safe Undead Direction Getter
     * @return a Copy of the Direction
     */
    public static Direction getUndeadSafeDirection() {
        return new Direction(undeadSafeDirection.getX(), undeadSafeDirection.getY());
    }
    /**
     * Safe Pirate Direction Getter
     * @return a Copy of the Direction
     */
    public static Direction getPirateSafeDirection() {
        return new Direction(pirateSafeDirection.getX(), pirateSafeDirection.getY());
    }
    /**
     * Safe Merchant Direction Getter
     * @return a Copy of the Direction
     */
    public static Direction getMerchantSafeDirection() {
        return new Direction(merchantSafeDirection.getX(), merchantSafeDirection.getY());
    }

    /**
     * This function generateMap is meant to generate a map via different parameters
     * @param width set the width of the map by each case
     * @param height set the height of the map by each case
     * @param safeZoneWidth set the width of the safe Zone
     * @param safeZoneHeight   set the height of the safe Zone
     */
    private void generateMap(int width, int height, int safeZoneWidth, int safeZoneHeight) {
        this.map = new ArrayList<>();

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

        masters.add(MasterBritish.getInstance());
        masters.add(MasterUndead.getInstance());
        masters.add(MasterPirate.getInstance());
        masters.add(MasterMerchant.getInstance());


        // Reset Masters
        for (Master m: masters ) {
            m.reset();
        }




        // Generating Map
        for (int i = 0; i < width; i++) {
            ArrayList<Case> column = new ArrayList<>();
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
                    if ( PseudoRandom.getRandomNumberInRange(0, 100)  < 0.05 * 100){
                        // 20% to create tornado
                        if(PseudoRandom.getRandomNumberInRange(0,100) < 0.2 * 100){
                            tempCase.setToken(new MovingObstacle());
                        } else {
                            tempCase.setToken(new FixObstacle());
                        }
                    }
                        //tempCase.setToken(new Obstacle());
                }
                column.add(tempCase);
            }
            this.map.add(column);
        }
    }

    /**
     * Map function is meant to call the function to generate map via different parameters and we set the  chance of having Obstacle in the map
     * @param width set the width of the map by each case
     * @param height set the height of the map by each case
     * @param safeZoneWidth set the width of the safe Zone
     * @param safeZoneHeight   set the height of the safe Zone
     *
     */
    public Map(int width, int height, int safeZoneWidth, int safeZoneHeight) {
        this.generateMap(width, height, safeZoneWidth, safeZoneHeight);
    }
    public Map() {
        this.generateMap(12, 8, 3, 2);
    }

    /**
     * ArrayList allows you to table the map according to x and y coordinates, it is called in the generated map function
     * @param x position in x on the map
     * @param y position in y on the map
     * @return a 3x3 array of case surrounding a case with the case in position 1x1
     */
    public ArrayList< ArrayList<Case> > getAdjacentCases(int x, int y) {
        ArrayList< ArrayList<Case> > arrayCases = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            arrayCases.add(new ArrayList<>());
            for (int j = -1; j < 2; j++) {
                arrayCases.get(i+1).add(this.getCase(x+i, y+j));
            }
        }
        return arrayCases;
    }

    /**
     * It returns a certain case in the map via the x and y axis parameters
     * @param x position in x on the map
     * @param y position in y on the map
     * @return null if the case is out of the map
     */
    public Case getCase(int x, int y) {
        if (x < 0 || y < 0 || x >= this.map.size() || y >= this.map.get(0).size()) return null;
        return this.map.get(x).get(y);
    }

    /**
     * Class which can contain a pair of two elements: An Individual and a 2d Position
     */
    private static class PackIndividualPosition {
        public Individual individual;
        public int x;
        public int y;

        public PackIndividualPosition (Individual individual, int x, int y) {
            this.individual = individual;
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Class to pack a Moving Obstacle and is position
     */
    private static class PackMovingObstacle{
        public MovingObstacle obstacle;
        public int x;
        public int y;

        public PackMovingObstacle (MovingObstacle obstacle, int x, int y){
            this.obstacle = obstacle;
            this.x = x;
            this.y = y;
        }
    }

    /**
     *  List all the individual able to move this turn
     * @return Array of every Individual with energyPoints > 0 on the map and their position
     */
    public ArrayList<PackIndividualPosition> getEveryIndividualsMoving() {

        ArrayList<PackIndividualPosition> individuals = new ArrayList<>();

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

    /**
     *
     * @return all the Moving Obstacle found on the map
     */
    public ArrayList<PackMovingObstacle> getEveryMovingObstacle(){

        ArrayList<PackMovingObstacle> obstacles = new ArrayList<>();

        for (int i=0; i< this.map.size(); i++){
            for(int j=0; j< this.map.get(0).size(); j++){
                Token token = this.map.get(i).get(j).getToken();
                // != null not needed handled by instanceof
                if(token instanceof MovingObstacle){
                    obstacles.add(new PackMovingObstacle((MovingObstacle) token, i, j));
                }
            }
        }

        return obstacles;
    }

    /**
     * Check whether masters won the simulation (Array because we could have a Tie)
     * by counting the number of goods of each team
     * @return ArrayList of the winners
     */
    public ArrayList<String> checkWinForfait() {
        HashMap<String, Set<String>> goodPerTeams = new HashMap<>();
        ArrayList<String> winners = new ArrayList<>();

        // TODO redo the function to count the master with the most good
        //return masters.stream().map(master -> master.getGoods().size()).max();

        return winners;
    }

    /**
     * Check whether masters won the simulation (Array because we could have a Tie)
     * @return ArrayList of the winners
     */
    public ArrayList<Master> checkWin() {
        return (ArrayList<Master>) masters.stream().filter(Master::checkHasEveryGoods).collect(Collectors.toList());
    }


    /**
     * Do a cycle
     *     Move the moving obstacle
     *     Move the Individuals
     * @return return true if the step succeeded (actually always true)
     */
    public boolean step() {
        // individuals
        ArrayList<PackIndividualPosition> individuals = this.getEveryIndividualsMoving();
        // moving Obstacles
        ArrayList<PackMovingObstacle> obstacles = this.getEveryMovingObstacle();

        if (individuals.size() == 0) return false;

        Collections.shuffle(individuals, PseudoRandom.getGenerator());

        //for for moving obstacles
        for (PackMovingObstacle ob : obstacles){
            Direction dir = ob.obstacle.move(this.getAdjacentCases(ob.x, ob.y));

            int length = dir.getLength();
            int x = dir.getX();
            int y = dir.getY();

            int newX = ob.x;
            int newY = ob.y;

            while (length > 0) {
                // Get the case the tornado will move on
                Case tmpCase = this.getCase(newX + x, newY + y);
                if (tmpCase == null) break; /// out of map
                // get the token in the case if there is one
                Token tmpToken = tmpCase.getToken();

                // Tornado don't go in safe zone
                if (tmpCase instanceof SafeCase)  break;

                //if the tornado touch an entity
                if(tmpToken != null){
                    // if the tornado touch a boat it lose a item
                    if(tmpToken instanceof Individual){
                        int rng = PseudoRandom.getRandomNumberInRange(0, 2);
                        ((Individual) tmpToken).looseItem(rng);
                    }
                    // we move one more turn any way to not stay on a entity
                    newX += x;
                    newY += y;
                } else {
                    newX += x;
                    newY += y;
                    length--;
                }

            }

            if (length == 0){ // check if the position to move is correct or stay in place
                // TODO may induce bug were it moved and did things but stay at the same place
                // Changing position
                this.getCase(ob.x, ob.y).setToken(null);
                this.getCase(newX, newY).setToken(ob.obstacle);
            }
        }


        int movedIndividual = 0;
        // for for individual
        for (PackIndividualPosition pack: individuals) {
            Direction individualDirection = pack.individual.move(this.getAdjacentCases(pack.x, pack.y));
            int length = individualDirection.getLength();
            int dirX = individualDirection.getX();
            int dirY = individualDirection.getY();

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
                    movedIndividual++;
                }
            }

            // Changing position
            this.getCase(pack.x, pack.y).setToken(null);
            this.getCase(newX, newY).setToken(pack.individual);

            if (this.getCase(newX, newY) instanceof SafeCase) {
                pack.individual.restoreEnergy();

                pack.individual.shareMaster();
            }
        }

        return movedIndividual != 0; // Step Worked

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

            for (ArrayList<Case> cases : this.map) {
                Case tempCase = cases.get(j);
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
