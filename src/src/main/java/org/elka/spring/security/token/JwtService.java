package org.elka.spring.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtService {

    private final AbstractTokenProperties properties;

    public String extractUsername(String token) throws TokenValidationException {
        try {
            return this.extractClaim(token, Claims::getSubject);
        } catch (JwtException e) {
            throw new TokenValidationException(e.getMessage(), e);
        }
    }

    public <T> T extractClaim(String token, @NotNull Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(@NotNull UserDetails userDetails) {
        return this.generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, @NotNull UserDetails userDetails) {
        return this.buildToken(extraClaims, userDetails, this.properties.getJwtExpiration());
    }

    public String generateRefreshToken(@NotNull UserDetails userDetails) {
        return this.buildToken(new HashMap<>(), userDetails, this.properties.getRefreshExpiration());
    }

    private @NotNull String buildToken(
            @NotNull Map<String, Object> extraClaims,
            @NotNull UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(this.getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValid(String token, @NotNull UserDetails userDetails) throws TokenValidationException {
        try {
            final String username = this.extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !this.isExpired(token);
        } catch (JwtException e) {
            throw new TokenValidationException(e.getMessage(), e);
        }
    }

    private boolean isExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    @Contract(pure = true)
    private @Nullable Date extractExpiration(String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) throws TokenValidationException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(this.getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new TokenValidationException(e.getMessage(), e);
        }
    }

    private @NotNull Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.properties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public List<String> extractRoles(String token) {
        Claims claims = this.extractAllClaims(token);
        return claims.get("roles", List.class);
    }

    public String addRoles(String token, List<String> roles) {
        Claims claims = this.extractAllClaims(token);
        claims.put("roles", roles);
        return Jwts.builder().setClaims(claims)
                .signWith(this.getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateSignature(String token) throws TokenValidationException {
        try {
            Jwts.parserBuilder().setSigningKey(this.getSignInKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            throw new TokenValidationException(e.getMessage(), e);
        }
    }

    public void logTokenDetails(String token) {
        Claims claims = this.extractAllClaims(token);
        log.info("Token details: subject={}, issuedAt={}, expiration={}",
                claims.getSubject(), claims.getIssuedAt(), claims.getExpiration());
    }
}
