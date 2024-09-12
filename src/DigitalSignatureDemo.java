import java.nio.charset.StandardCharsets;
import java.security.*;

public class DigitalSignatureDemo {
    public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    public static boolean verify(byte[] data, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(signatureBytes);
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        byte[] data = "Hello".getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = sign(data, keyPair.getPrivate());
        boolean isValid = verify(data, signatureBytes, keyPair.getPublic());
        System.out.println(isValid);
    }
}