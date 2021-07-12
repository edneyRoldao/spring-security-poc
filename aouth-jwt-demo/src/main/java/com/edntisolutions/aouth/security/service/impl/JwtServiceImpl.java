package com.edntisolutions.aouth.security.service.impl;

import com.edntisolutions.aouth.security.dto.JwtUserDetails;
import com.edntisolutions.aouth.security.exception.AppUnauthorizedException;
import com.edntisolutions.aouth.security.exception.TokenFormatException;
import com.edntisolutions.aouth.util.DateUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import com.edntisolutions.aouth.security.service.JwtService;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${app.security.auth-header}")
    private String authHeader;

    @Value("${app.security.token.secret}")
    private String tokenSecret;

    @Value("${app.security.token.expiration-time}")
    private Long tokenExpirationMinutes;

    @Override
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            JwtUserDetails user = (JwtUserDetails) userDetails;
            String usernameFromToken = extractUsername(token);
            return usernameFromToken.equals(user.getUsername()) && !isTokenExpired(token);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(buildClaims(userDetails))
                .setExpiration(generateExpirationTime())
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(tokenSecret))
                .compact();
    }

    @Override
    public String refreshToken(UserDetails userDetails, String token) {
        if (isTokenValid(token, userDetails)) {
            return generateToken(userDetails);
        }

        throw new AppUnauthorizedException("invalid token");
    }

    @Override
    public String getAuthHeaderName() {
        return authHeader;
    }


    private Map<String, Object> buildClaims(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        return claims;
    }

    private Date generateExpirationTime() {
        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(tokenExpirationMinutes);
        return DateUtil.localDateTimeToDate(expiredAt);
    }

    private boolean isTokenExpired(String token) {
        Date date = getClaims(token).getExpiration();
        LocalDateTime localDateTime = DateUtil.dateToLocalDateTime(date);
        return localDateTime.isBefore(LocalDateTime.now());
    }

    private Claims getClaims(String token) {
        try {
            String tokenWithoutBearer = removeBearer(token);

            return Jwts
                    .parser()
                    .setSigningKey(TextCodec.BASE64.decode(tokenSecret))
                    .parseClaimsJws(tokenWithoutBearer)
                    .getBody();

        } catch (Exception e) {
            throw new TokenFormatException(e.getMessage());
        }
    }

    private String removeBearer(String token) {
        try {
            return token.split(" ")[0];
        } catch (Exception e) {
            throw new TokenFormatException("Bearer is missing from token");
        }
    }

}
