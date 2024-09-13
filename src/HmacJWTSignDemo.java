import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HmacJWTSignDemo {
    public static void main(String[] args) throws Exception {
        String secret = "your-256-bit-secret";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        // JSON 헤더, 페이로드 데이터 입력
        String header_JSON = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String payload_JSON = "{\"sub\":\"1234567890\",\"name\":\"John Doe\",\"iat\":1516239022}";
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String header_Base64URLSafe = encoder.encodeToString(header_JSON.getBytes(StandardCharsets.UTF_8));
        String payload_Base64URLSafe = encoder.encodeToString(payload_JSON.getBytes(StandardCharsets.UTF_8));
        System.out.println(header_Base64URLSafe);
        System.out.println(payload_Base64URLSafe);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmac.init(secretKey);
        byte[] signatureBytes = hmac.doFinal((header_Base64URLSafe + "." + payload_Base64URLSafe).getBytes(StandardCharsets.UTF_8));
        String signature_Base64URLSafe = encoder.encodeToString(signatureBytes);
        System.out.println(signature_Base64URLSafe);
        String jwt = header_Base64URLSafe + "." + payload_Base64URLSafe + "." + signature_Base64URLSafe;
        System.out.println(jwt);
        System.out.println(jwt.equals(token));

    }
}
