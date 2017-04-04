package hash_tables;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/3/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class String_Hash_Functor implements HashFunctor<String> {
    public int hash(String item) {
        int intLength = item.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char c[] = item.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char c[] = item.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int) Math.abs(sum);
    }
}
