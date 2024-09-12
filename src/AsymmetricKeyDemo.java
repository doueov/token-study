import java.awt.desktop.SystemSleepEvent;
import java.security.*;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;

public class AsymmetricKeyDemo {
    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        byte[] original = "Hello".getBytes(StandardCharsets.UTF_8);
        byte[] encrypted = encrypt(original, privateKey);
        byte[] decrypted = decrypt(encrypted, publicKey);
        System.out.println(original);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

}
