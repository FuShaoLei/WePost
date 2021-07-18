package github.fushaolei.wpserver.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // 密钥,可自行定义
    private static final String SECRET = "wepost-server";

    // 过期时间
    private static long EXPIRE_TIME = 30L * 24 * 60 * 60 * 1000;// 一个月


    public static String createToken(int id) {
        //过期时间
        long beginTime = System.currentTimeMillis();
        Date expireDate = new Date(beginTime + EXPIRE_TIME);
        Date beginDate = new Date(beginTime);
        // header
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(map)
                .withClaim("id", id)
                .withExpiresAt(expireDate)
                .withIssuedAt(beginDate)
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //解码异常则抛出异常
            return null;
        }
        return jwt.getClaims();
    }

    /**
     * 验证token的有效性
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

}