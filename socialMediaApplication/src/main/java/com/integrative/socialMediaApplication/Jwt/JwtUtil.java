package com.integrative.socialMediaApplication.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "8d6fad0346ad06afaf655488848c48c629dfec8d55cfe703a6128e4a8d4163ef83aaa8591f87af596fecef6c45ee77bf74109f2e24d0420dc1e979dee37ac625882eb7c3493c478805ba4a382930ec7f491944b14270ae3447606240daa4e6cbee9050eba0c8a83111780ac6ba0ee2e3046acdeff8683ac0bb2f5a689ca99b1caf048a01817d97db1c8f284da7abdbbf8e4bdd6b9f4c790746c9069229cfd00b2be2986a65ccfebb305e7eaa34a10b5db2351ccd0d7ce6742b1b36256aea7be169a7f19730d704b5ec98bbc688e0166b7b852b9314cfbc1069499f1701ccb1bb8390a973fba98abab063af7cdcde05a84a1a448293acc5eddf926bb0f331e6b7"; // Replace with a strong secret key
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
