package org.example;

public class Keys {

    private byte[] key;
    private byte[][] subKeys;

    public void KeyGenerator(byte[] key) {
        this.key = key;
        generateSubKeys();
    }

    private void generateSubKeys() {
        subKeys = new byte[16][6];

        // TODO: Dodaj generowanie DES
    }

    public byte[][] getSubKeys() {
        return subKeys;
    }
}
