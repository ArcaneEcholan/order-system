package cn.edu.bistu.auth;

import cn.edu.bistu.constants.Time;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 该类主要调用JWT的API，用于创建token，验证token时效，解析token内容等。
 */
public class JwtHelper {

    /**
     * token的过期时间，Time接口中定义了一些关于日期的常量
     */
    private static long tokenExpiration = Time.DAY;

    /**
     * 生成token所需的密钥
     */
    private static String tokenSignKey = "111111";

    /**
     * 根据map中的数据创建token
     *
     * @return 创建好的token
     */
    public static String createToken(Map<String, Object> claimMap) {

        String token = Jwts.builder()
                .setSubject("ORDER-SYSTEM-USER")
                .setClaims(claimMap)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();

        return token;
    }

    /**
     * verify signature with specific tokenSignKey, test if expired at the same time
     *
     * @param token token to be verified
     * @return the Jwt<Claims> from the specific token if signature checking passed
     * @throws io.jsonwebtoken.SignatureException JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.
     */
    public static Jws<Claims> verifySignature(String token) {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
    }

    /**
     * 生成token的过期时间，使用时间戳与
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + tokenExpiration);
    }

    /**
     * 从token中获取指定的claim，需要提供claim的Class
     *
     * @param token      指定的token
     * @param claimName  待获取的claim的名字
     * @param claimClazz 待获取的claim的Class
     * @param <T>        待获取的claim的类型
     * @return 待获取的claim
     */
    public static <T> T getClaim(String token, String claimName, Class<T> claimClazz) {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = verifySignature(token);
        Claims claims = claimsJws.getBody();
        T claim = (T) claims.get(claimName);
        return claim;
    }

    /**
     * 从token中获取claims对象
     *
     * @param token 指定的token
     * @return 待获取的claims
     */
    public static Claims getClaims(String token) {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = verifySignature(token);
        Claims claims = claimsJws.getBody();
        return claims;
    }

    /**
     * 从token中获取指定的claim
     *
     * @param token     指定的token
     * @param claimName 待获取的claim的名字
     * @return 待获取的claim
     */
    public static Object getClaim(String token, String claimName) {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = verifySignature(token);
        Claims claims = claimsJws.getBody();
        Object claim = claims.get(claimName);
        return claim;
    }




    public static void main(String[] args) throws InterruptedException {
        //Map<String, Object> map = new HashMap<>();
        //map.put("userId", 1L);
        //map.put("userName", "55");
        //
        //String token = JwtHelper.createToken(map);
        //System.out.println(token);
        //
        //
        //JwtHelper.verifySignature("eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi1OLfJLzE1VslIyNVXSUUqtKFCyMjQzsjAxNjY1M9ABK_BMAYrVAgCq20hsLQAAAA.b4WyFz4v4tSbLgK7R58ljsus23FuaTkKpVZeZA-wiuExcQxY0Q92t00oufWEW9dW7Pf7b_UpaOLFljHYcpiMJ");
        //
    }
}
