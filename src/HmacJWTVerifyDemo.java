import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HmacJWTVerifyDemo {
    public static void main(String[] args) throws Exception {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        String secret = "your-256-bit-secret";

        String[] content = token.split("\\.");
        String header_Base64URLSafe = content[0];
        String payload_Base64URLSafe = content[1];
        String signature = content[2];

        Base64.Decoder urlDecoder = Base64.getUrlDecoder();
        String header = new String(urlDecoder.decode(header_Base64URLSafe), StandardCharsets.UTF_8);
        String payload = new String(urlDecoder.decode(payload_Base64URLSafe), StandardCharsets.UTF_8);
        System.out.println(header);
        System.out.println(payload);

        // 검증 시작
        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmac.init(secretKey);
        byte[] signatureBytes = hmac.doFinal((content[0] + "." + content[1]).getBytes(StandardCharsets.UTF_8));
        String calculatedSignature = Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);
        System.out.println(calculatedSignature);
        System.out.println(signature.equals(calculatedSignature));

    }
}
