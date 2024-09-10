import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashFunctionDemo {

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_8);
        byte[] hash = digest.digest(bytes);
        System.out.println(hash.length);
        String hexString = bytesToHex(hash);
        System.out.println(hexString);
        System.out.println(hexString.length());
    }
}
