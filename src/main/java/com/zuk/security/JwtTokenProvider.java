package com.zuk.security;

import com.zuk.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    /** The front-end sends "Bearer_<token>"; the standard "Bearer <token>" is accepted too. */
    private static final List<String> TOKEN_PREFIXES = List.of("Bearer_", "Bearer ");
    private static final int MIN_SECRET_BYTES = 32;

    private final SecretKey key;
    private final JwtParser parser;
    private final long validityInMilliseconds;
    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(@Value("${jwt.token.secret}") String secret,
                            @Value("${jwt.token.expired}") long validityInMilliseconds,
                            UserDetailsService userDetailsService) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (secretBytes.length < MIN_SECRET_BYTES) {
            throw new IllegalStateException("jwt.token.secret (JWT_SECRET) must be at least "
                    + MIN_SECRET_BYTES + " bytes long for HS256");
        }
        this.key = Keys.hmacShaKeyFor(secretBytes);
        this.parser = Jwts.parserBuilder().setSigningKey(key).build();
        this.validityInMilliseconds = validityInMilliseconds;
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", getRoleNames(roles));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return parser.parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        return stripPrefix(req.getHeader("Authorization"));
    }

    /** Returns the raw token from an Authorization header value, or null if it has no known prefix. */
    public String stripPrefix(String header) {
        if (header == null) {
            return null;
        }
        for (String prefix : TOKEN_PREFIXES) {
            if (header.startsWith(prefix)) {
                return header.substring(prefix.length());
            }
        }
        return null;
    }

    /** Signature and expiry are both checked by the parser. */
    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            parser.parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public List<String> getRoleNames(List<Role> userRoles) {
        return userRoles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
