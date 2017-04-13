package lab11;
/**
 * This is a silly little class to do the animations for the decoding bit.
 * Completely superfluous, but give it a more polished look
 *
 * Never underestimate a good polish.
 * @author ryans
 */
public class Spinner {
	public static final char slashes[] = {'/', '-', '\\', '|'};
	
	public static void slash(String text) {
		for (int iter = 0; iter < 12; iter++) {
			System.out.print("\r" + text + " " + slashes[iter % 4]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public static void dots(String text) {
		StringBuilder sb = new StringBuilder();
		for (int iter = 0; iter < 12; iter++) {
			sb.setLength(0); // clear
			sb.append("\r" + text + " ");
			char[] dots = {' ' ,' ' , ' ' , ' ' }; // really spaces...soon to be dotts.
			for(int dot = 0; dot <= iter % 4; dot++) {
				dots[dot] = '.';
			}
			sb.append(dots);
			System.out.print(sb.toString());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
			}
		}
		System.out.print("\r" + text + " .... done!");
	}
}
