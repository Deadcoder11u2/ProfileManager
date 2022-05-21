package com.example.profilemanager;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class PasswordEncoder {

    private final String algorithm = "AES";
    private SecretKey key;
    boolean ready = false;

    private final static PasswordEncoder ENCODER = new PasswordEncoder();

    private PasswordEncoder() {
        // Initializing the key for AES
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(128);
            this.key = keyGenerator.generateKey();
            this.ready = true;
        } catch (NoSuchAlgorithmException e) {
            this.ready = false;
            e.printStackTrace();
        }
    }

    public static PasswordEncoder getInstance() {
        return ENCODER;
    }

    String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] ciphertext = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(ciphertext);
    }

    String decrypt(String input, String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }
}