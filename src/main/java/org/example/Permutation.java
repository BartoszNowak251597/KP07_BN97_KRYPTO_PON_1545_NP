package org.example;

public class Permutation {

    private static final byte[] pBlock = {16, 7, 20, 21, 29, 12, 28, 17,
                                            1, 15, 23, 26, 5, 18, 31, 10,
                                            2, 8, 24, 14, 32, 27, 3, 9,
                                            19, 13, 30, 6, 22, 11, 4, 25};

    public static byte[] applyPBox(byte[] input) {
        byte[] output = new byte[input.length];
        for (int i = 0; i < pBlock.length; i++) {
            int bitIndex = pBlock[i] - 1;
            int byteIndex = bitIndex / 8;
            int bitOffset = bitIndex % 8;

            if ((input[byteIndex] & (1 << (7 - bitOffset))) != 0) {
                output[i / 8] |= (byte) (1 << (7 - (i % 8)));
            }
        }
        return output;
    }

    // TODO: Implementacja obsługi S-Boxów (NW czy się przyda)
}