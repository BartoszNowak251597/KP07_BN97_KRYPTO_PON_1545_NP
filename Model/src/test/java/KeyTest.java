import org.example.Des;
import org.example.Keys;
import org.example.Operations;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class KeyTest {

    byte[] greg = { (byte) 0b00000000,(byte) 0b00000000,
                    (byte) 0b00000000,(byte) 0b00000000,
                    (byte) 0b00000000,(byte) 0b00000000,
                    (byte) 0b00000000,(byte) 0b10000000,};

    byte[] gregKey = {  (byte) 0b10000000,(byte) 0b00000000,
                        (byte) 0b00000000,(byte) 0b00000000,
                        (byte) 0b00000000,(byte) 0b00000000,
                        (byte) 0b00000000};
    byte[] firstKey = { (byte) 0b00000001,(byte) 0b00000000,
                        (byte) 0b00000000,(byte) 0b00000000,
                        (byte) 0b00000000,(byte) 0b00000000};

    @Test
    @DisplayName("Test for permutation")
    public void permutationTest() {
        byte[] result = Keys.IPkey(greg);
        Assertions.assertArrayEquals(gregKey, result);
    }

    @Test
    @DisplayName("Test for key generation")
    public void generateKeyTest() {
        byte[] temp = Keys.IPkey(greg);
        byte[] left = new byte[4];
        byte[] right = new byte[4];
        System.arraycopy(temp, 0, left, 0, 4);
        System.arraycopy(temp, 3, right, 0, 4);
        right[0] &= 0b00001111;
        byte[] temp2 = Keys.generateKey(left , right, 0);
        Assertions.assertArrayEquals(firstKey, temp2);

    }
}
