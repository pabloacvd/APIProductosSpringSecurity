package ar.com.xeven.apiproductosspringsecurity.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserRoleTest {
    @Test
    public void testGetGrantedAuthorities() {
        // Arrange
        UserRole userRole = UserRole.CLIENTE;

        // Act
        Set<SimpleGrantedAuthority> actualGrantedAuthorities = userRole.getGrantedAuthorities();

        // Assert
        assertEquals(3, actualGrantedAuthorities.size());
    }

    @Test
    public void testGetRole() {
        // Arrange
        UserRole userRole = UserRole.CLIENTE;

        // Act
        String actualRole = userRole.getRole();

        // Assert
        assertEquals("CLIENTE", actualRole);
    }
}

