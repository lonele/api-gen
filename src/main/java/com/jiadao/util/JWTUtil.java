package com.jiadao.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2018/7/5 下午9:56
 * @history: 1.2018/7/5 created by jianfeng.zheng
 */
public class JWTUtil {
    
    public static final String SECRET="5pil6aOO5YaN576O5Lmf5q+U5LiN5LiK5bCP6ZuF55qE56yR";
    
    public static String createUserJWT(int uid ,
                                    String name,
                                    String[] depts,
                                    String[] roles, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Key signingKey = new SecretKeySpec(SECRET.getBytes(), signatureAlgorithm.getJcaName());

        Map<String,Object> header=new HashMap<String,Object>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        Map<String,Object> claims= new HashMap<>();
        claims.put("uid",uid);
        claims.put("name",name);
        claims.put("depts", Arrays.toString(depts));
        claims.put("roles", Arrays.toString(roles));
        JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setIssuer("jiadao")
                .setClaims(claims)
                // .setSubject(uid)
                .setHeader(header)
                .signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static void main(String[]cmd) throws Exception {

        String[] deptIds = {"1","2","3"};
        System.out.println(Arrays.toString(deptIds));
        String[] roleIds = {"4","5","6"};
        String s=createUserJWT(1001,"lel",deptIds,roleIds,36000000);
        System.out.println("Bearer "+s);
    }

}