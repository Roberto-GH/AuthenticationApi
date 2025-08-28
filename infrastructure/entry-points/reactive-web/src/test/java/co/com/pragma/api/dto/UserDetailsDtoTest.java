package co.com.pragma.api.dto;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsDtoTest {

    @Test
    void testUserDetailsDtoCreation() {
        int id = 1;
        String username = "testuser";
        String password = "testpassword";
        String email = "test@example.com";
        String roles = "ROLE_USER, ROLE_ADMIN";

        UserDetailsDto dto = UserDetailsDto.builder()
            .id(id)
            .username(username)
            .password(password)
            .email(email)
            .roles(roles)
            .build();

        assertAll("UserDetailsDto properties",
            () -> assertEquals(username, dto.getUsername()),
            () -> assertEquals(password, dto.getPassword())
        );

        Collection<? extends SimpleGrantedAuthority> authorities = (Collection<? extends SimpleGrantedAuthority>) dto.getAuthorities();
        assertAll("Authorities",
            () -> assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))),
            () -> assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );

        assertAll("Account status",
            () -> assertTrue(dto.isAccountNonExpired()),
            () -> assertTrue(dto.isAccountNonLocked()),
            () -> assertTrue(dto.isCredentialsNonExpired()),
            () -> assertTrue(dto.isEnabled())
        );
    }
}
