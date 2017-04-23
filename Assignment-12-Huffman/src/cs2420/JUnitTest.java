package cs2420;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/16/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class JUnitTest {

    @Test
    public void testBitOperations(){
        BitSet bitSet = BitSet.valueOf(new byte[]{(byte)0b111_0000});
        byte[] bytes = Bit_Operations.get_bytes(bitSet);
        System.out.println(Arrays.toString(bytes));
        for(int i = 0; i<5; i++) {
            assertFalse(Bit_Operations.get_bit((byte) 7, i));
        }
        for(int i = 5; i<8; i++) {
            assertTrue(Bit_Operations.get_bit((byte) 7, i));
        }
    }
}
