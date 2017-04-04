package cards;


/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class My_Best_Random_Generator implements Random_Generator {

    private long multiplier, increment, seed;

    private static final long mask = (1L << 48) - 1;

    @Override
    public int next_int(int max) {
        seed = (seed * multiplier + increment) & mask;
        return (int) (seed % max);
    }

    @Override
    public void set_seed(long seed) {
        this.seed = seed;
    }

    @Override
    public void set_constants(long multiplier, long increment) {
        this.multiplier = multiplier;
        this.increment = increment;
    }
}
