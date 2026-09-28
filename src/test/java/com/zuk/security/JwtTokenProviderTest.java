package com.zuk.security;

import com.zuk.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class JwtTokenProviderTest {

    private static final String SECRET = "unit-test-secret-that-is-long-enough-for-hs256";

    private final JwtTokenProvider provider = new JwtTokenProvider(SECRET, 60_000, mock(UserDetailsService.class));

    @Test
    void createdTokenIsValidAndCarriesUsername() {
        String token = provider.createToken("anna", List.of(role("ROLE_USER")));

        assertThat(provider.validateToken(token)).isTrue();
        assertThat(provider.getUsername(token)).isEqualTo("anna");
    }

    @Test
    void tokenSignedWithAnotherSecretIsRejected() {
        JwtTokenProvider attacker = new JwtTokenProvider("another-secret-that-is-also-long-enough-123", 60_000, mock(UserDetailsService.class));
        String forged = attacker.createToken("admin", List.of(role("ROLE_ADMIN")));

        assertThat(provider.validateToken(forged)).isFalse();
    }

    @Test
    void expiredTokenIsRejected() {
        JwtTokenProvider shortLived = new JwtTokenProvider(SECRET, -1_000, mock(UserDetailsService.class));
        String token = shortLived.createToken("anna", List.of(role("ROLE_USER")));

        assertThat(provider.validateToken(token)).isFalse();
    }

    @Test
    void garbageAndNullAreRejected() {
        assertThat(provider.validateToken("not-a-jwt")).isFalse();
        assertThat(provider.validateToken(null)).isFalse();
    }

    @Test
    void shortSecretFailsFast() {
        assertThatThrownBy(() -> new JwtTokenProvider("zuk", 60_000, mock(UserDetailsService.class)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void bothHeaderPrefixesAreSupported() {
        assertThat(provider.stripPrefix("Bearer_abc")).isEqualTo("abc");
        assertThat(provider.stripPrefix("Bearer abc")).isEqualTo("abc");
        assertThat(provider.stripPrefix("Basic abc")).isNull();
        assertThat(provider.stripPrefix(null)).isNull();
    }

    private static Role role(String name) {
        Role role = new Role();
        role.setName(name);
        return role;
    }
}
