package org.example;
import org.example.Operations;

import java.util.ArrayList;
import java.util.List;

import static org.example.Operations.permute;

public class Keys {

    private static int[] PC1 = { 57, 49, 41, 33, 25, 17,  9,  1,
                                 58, 50, 42, 34, 26, 18, 10,  2,
                                 59, 51, 43, 35, 27, 19, 11,  3,
                                 60, 52, 44, 36, 63, 55, 47, 39,
                                 31, 23, 15,  7, 62, 54, 46, 38,
                                 30, 22, 14,  6, 61, 53, 45, 37,
                                 29, 21, 13,  5, 28, 20, 12,  4 };

    private static int[] PC2 = { 14, 17, 11, 24,  1,  5,  3, 28,
                                 15,  6, 21, 10, 23, 19, 12,  4,
                                 26,  8, 16,  7, 27, 20, 13,  2,
                                 41, 52, 31, 37, 47, 55, 30, 40,
                                 51, 45, 33, 48, 44, 49, 39, 56,
                                 34, 53, 46, 42, 50, 36, 29, 32 };

    private static int[] keyShift = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };

    public static byte[] IPkey(byte[] key) {
        int requiredLength = 8;
        if (key.length > requiredLength) {
            throw new IllegalArgumentException("Klucz jest za długi! Maksymalna długość to 64 bity (8 bajtów).");
        }
        byte[] paddedKey = new byte[requiredLength];
        System.arraycopy(key, 0, paddedKey, 0, key.length);
        return permute(paddedKey, PC1, 56);
    }

    public static byte[] generateKey(byte[] left, byte[] right, int i) {
        left = Operations.shiftLeft(left, keyShift[i]);
        right = Operations.shiftLeft(right, keyShift[i]);
        byte[] temp = new byte[7];
        System.arraycopy(left, 0, temp, 0, 3);
        temp[3] = (byte) ((left[3] & 0b11110000) | ((right[0] & 0b11110000) >> 4));
        System.arraycopy(right, 0, temp, 3, 3);
        return Operations.permute(temp, PC2, 48);
    }

    public static List<byte[]> generate16Keys(byte[] key) {
        List<byte[]> result = new ArrayList<>();
        byte[] left = new byte[4];
        byte[] right = new byte[4];
        System.arraycopy(key, 0, left, 0, 4);
        System.arraycopy(key, 4, right, 0, 4);
        right[0] &= 0b00001111;
        for (int i = 0; i < 16; i++) {
            result.add(generateKey(left, right, i));
        }
        return result;
    }
}
