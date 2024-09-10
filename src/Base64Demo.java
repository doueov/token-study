import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Demo {
    public static void main(String[] args) {
        byte[] original = "Hello".getBytes();
        Base64.Encoder base64Encoder = Base64.getEncoder();
        // Base64 인코딩
        String encoded = base64Encoder.encodeToString(original);
        // Base64 형식의 문자열 출력
        System.out.println(encoded);
        // Base64 디코딩
        Base64.Decoder base64Decoder = Base64.getDecoder();
        // 원본 바이너리 데이터 배열 얻어오기
        byte[] decoded = base64Decoder.decode(encoded);
        // 바이트 배열 변환하여 원본 문자열 출력
        System.out.println(new String(decoded, StandardCharsets.UTF_8));

        // URL 인코더, 디코더 사용
        Base64.Encoder urlEncoder = Base64.getUrlEncoder();
        String urlEncoded = urlEncoder.encodeToString(original);
        System.out.println(urlEncoded);
        Base64.Decoder urlDecoder = Base64.getUrlDecoder();
        byte[] urlDecoded = urlDecoder.decode(urlEncoded);
        System.out.println(new String(urlDecoded, StandardCharsets.UTF_8));
    }
}