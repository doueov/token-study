import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SymmetricKeyDemo {

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static byte[] encrypt(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecureRandom secureRandom = new SecureRandom();

        int keySize = 32;
        byte[] randomBytes = new byte[keySize];
        secureRandom.nextBytes(randomBytes);
        SecretKeySpec secretKey = new SecretKeySpec(randomBytes, "AES");

        byte[] original = "Hello".getBytes(StandardCharsets.UTF_8);
        byte[] encrypted = encrypt(original, secretKey);
        byte[] decrypted = decrypt(encrypted, secretKey);
        System.out.println(original);
        System.out.println(encrypted);
        System.out.println(decrypted);

    }
}
