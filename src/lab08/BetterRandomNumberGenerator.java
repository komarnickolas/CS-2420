package lab08;

public class BetterRandomNumberGenerator implements RandomNumberGenerator {

	private long multiplier, increment, seed;

	private static final long mask = (1L << 48) - 1;
	
	@Override
	public int nextInt(int max){
		seed = (seed * multiplier + increment) & mask;
		return (int)(seed % max);
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;
	}

	@Override
	public void setConstants(long multiplier, long increment) {
		this.multiplier = multiplier;
		this.increment = increment;
	}
}
