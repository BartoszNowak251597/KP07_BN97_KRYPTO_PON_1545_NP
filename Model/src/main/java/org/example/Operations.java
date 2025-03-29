package org.example;

import java.nio.charset.StandardCharsets;

public class Operations {

    public static byte[] permute(byte[] msg, int[] arr, int numTim) {
        byte[] permutation = new byte[(numTim + 7) / 8];

        for (int i = 0; i < numTim; i++) {
            int bitIndex = arr[i] - 1;
            int byteIndex = bitIndex / 8;
            int bitOffset = bitIndex % 8;

            boolean bitValue = (msg[byteIndex] & (1 << (7 - bitOffset))) != 0;

            if (bitValue) {
                permutation[i / 8] |= (byte) (1 << (7 - (i % 8)));
            }
        }
        return permutation;
    }

    public static byte[] shiftLeft(byte[] k, int n) {
        int totalBits = k.length * 8;
        n = n % totalBits;

        for (int i = 0; i < n; i++) {
            boolean firstBit = (k[0] & 0b10000000) != 0;

            for (int j = 0; j < k.length; j++) {
                int nextBit = (j < k.length - 1) ? ((k[j + 1] & 0b10000000) >> 7) : 0;
                k[j] = (byte) ((k[j] << 1) | nextBit);
            }

            if (firstBit) {
                k[k.length - 1] |= 0b00000001;
            }
        }
        return k;
    }

    public static byte[] doXor(byte[] msg1, byte[] msg2) {
        if (msg1.length != msg2.length) {
            throw new IllegalArgumentException("msg1 and msg2 must be the same length");
        }
            byte[] result = new byte[msg1.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) (msg1[i] ^ msg2[i]);
            }
            return result;
    }

    public static byte[] textToByte(String text) {
        return text.getBytes();
    }

    public static String bytesToText(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
