package controller;

import java.util.Random;


public class PseudoRandom {

    private static long seed = 0 ;

    private static Random generator = null;

    public static PseudoRandom pseudoRandom = new PseudoRandom(0);

    private PseudoRandom(long seed) {
        PseudoRandom.seed = seed;
        PseudoRandom.generator = new Random(seed);
    }

    public static void reset(long seed){
        pseudoRandom = new PseudoRandom(seed);
    }

    public static int getRandom(){
        return generator.nextInt();
    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return PseudoRandom.generator.nextInt((max - min) + 1) + min;
    }

}
