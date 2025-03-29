import org.example.Operations;
import org.example.Des;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;


public class DesTest {

    String text = "Hell";
    String hexText = "48656C6C";
    byte[] hell = {(byte) 0b01001000,(byte) 0b01100101,
                   (byte) 0b01101100,(byte) 0b01101100};
    byte[] bob = {(byte) 0b10000000,(byte) 0b00000000};
    byte[] bobAfter = {(byte) 0b00000000,(byte) 0b00000010};
    byte[] bobXor = {(byte) 0b10000000,(byte) 0b00000010};
    int forShift = 2;
    byte[] bobBeforePerm = {(byte) 0b00000000,(byte) 0b00000000,
                            (byte) 0b00000000,(byte) 0b00000000,
                            (byte) 0b00000000,(byte) 0b00000000,
                            (byte) 0b00000000,(byte) 0b01000000,};

    byte[] bobAfterPerm = { (byte) 0b10000000,(byte) 0b00000000,
                            (byte) 0b00000000,(byte) 0b00000000,
                            (byte) 0b00000000,(byte) 0b00000000,
                            (byte) 0b00000000,(byte) 0b00000000,};
    @Test
    @DisplayName("Test for Shift")
    public void shiftTest() {
        byte[] result = Operations.shiftLeft(bob, forShift);
        Assertions.assertArrayEquals(bobAfter, result);
    }

    @Test
    @DisplayName("Test for permutation")
    public void permutationTest() {
        byte[] result = Operations.permute(bobBeforePerm, Des.getIP(), 64 );
        Assertions.assertArrayEquals(bobAfterPerm, result);
    }

    @Test
    @DisplayName("Test for XOR")
    public void xorTest() {
        byte[] result =  Operations.doXor(bob,bobAfter);
        Assertions.assertArrayEquals(bobXor, result);
    }

    @Test
    @DisplayName("Test for text change to bytes")
    public void textChangeTest() {
        byte[] result = Operations.textToByte(text);
        Assertions.assertArrayEquals(hell, result);
    }
    @Test
    @DisplayName("Test for bytes change to text")
    public void bytesToTextTest() {
        String result = Operations.bytesToText(hell);
        Assertions.assertEquals(text, result);
    }
}