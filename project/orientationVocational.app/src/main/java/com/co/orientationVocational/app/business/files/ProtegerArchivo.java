package com.co.orientationVocational.app.business.files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class ProtegerArchivo {
    private final static String clave = "kuaefbhqo3eñkfew4efwbeiqw6qw5da";

    public static void cifrarArchivo(String archivoOriginal, String archivoCifrado) throws Exception {
        Key key = new SecretKeySpec(clave.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (InputStream inputStream = new FileInputStream(archivoOriginal);
             OutputStream outputStream = new CipherOutputStream(new FileOutputStream(archivoCifrado), cipher)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("cifrado");
        }
    }

    public static void descifrarArchivo(String archivoCifrado, String archivoDescifrado) throws Exception {
        SecretKeySpec key = new SecretKeySpec(clave.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (InputStream inputStream = new FileInputStream(archivoCifrado);
             OutputStream outputStream = new FileOutputStream(archivoDescifrado)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] decryptedData = cipher.update(buffer, 0, bytesRead);
                outputStream.write(decryptedData);
            }
            byte[] decryptedData = cipher.doFinal();
            outputStream.write(decryptedData);
            System.out.println("Archivo descifrado exitosamente.");
        }
    }
}