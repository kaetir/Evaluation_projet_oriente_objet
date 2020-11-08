package main.java.Evaluation_projet_oriente_objet.controller;

import java.util.Random;

/**
 * Singleton giving the pseudo-random numbers
 */
public class PseudoRandom {

    private static long seed = 0 ;

    private static Random generator = null;

    public static PseudoRandom pseudoRandom = new PseudoRandom(0);

    /**
     * Generate a number (long) from a seed
     * @param seed number use to generate the random number
     */
    private PseudoRandom(long seed) {
        PseudoRandom.seed = seed;
        PseudoRandom.generator = new Random(seed);
    }


    public static void reset(long seed){
        pseudoRandom = new PseudoRandom(seed);
    }

    /**
     * Get a pseudo-random number from the generator
     * @return return a random number in int
     */
    public static int getRandom(){
        return generator.nextInt();
    }

    /**
     * Get the generator, useful for shuffle() functions
     * @return the random generator
     */
    public static Random getGenerator() {
        return generator;
    }

    /**
     * Get a pseudo-random number between min and max
     * @param min minimum number
     * @param max maximum number
     * @return the pseudo-random number
     */
    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return PseudoRandom.generator.nextInt((max - min) + 1) + min;
    }
}
