package ar.com.xeven.apiproductosspringsecurity.security.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

public class AppUserTest {
    @Test
    public void testConstructor() {
        // Arrange and Act
        AppUser actualAppUser = new AppUser();
        boolean accountNonExpired = true;
        actualAppUser.setAccountNonExpired(accountNonExpired);
        boolean accountNonLocked = true;
        actualAppUser.setAccountNonLocked(accountNonLocked);
        ArrayList<MiSimpleGrantedAuthority> miSimpleGrantedAuthorityList = new ArrayList<MiSimpleGrantedAuthority>();
        actualAppUser.setAuthorities(miSimpleGrantedAuthorityList);
        boolean credentialsNonExpired = true;
        actualAppUser.setCredentialsNonExpired(credentialsNonExpired);
        boolean enabled = true;
        actualAppUser.setEnabled(enabled);
        String password = "iloveyou";
        actualAppUser.setPassword(password);
        int user_id = 1;
        actualAppUser.setUser_id(user_id);
        String username = "janedoe";
        actualAppUser.setUsername(username);

        // Assert
        assertSame(miSimpleGrantedAuthorityList, actualAppUser.getAuthorities());
        assertEquals("iloveyou", actualAppUser.getPassword());
        assertEquals(1, actualAppUser.getUser_id().intValue());
        assertEquals("janedoe", actualAppUser.getUsername());
        assertTrue(actualAppUser.isAccountNonExpired());
        assertTrue(actualAppUser.isAccountNonLocked());
        assertTrue(actualAppUser.isCredentialsNonExpired());
        assertTrue(actualAppUser.isEnabled());
        assertEquals(
                "AppUser{user_id=1, username='janedoe', password='iloveyou', accountNonExpired=true, accountNonLocked=true,"
                        + " enabled=true, credentialsNonExpired=true}",
                actualAppUser.toString());
    }

    @Test
    public void testConstructor2() {
        // Arrange
        int user_id = 1;
        String username = "janedoe";
        String password = "iloveyou";
        ArrayList<MiSimpleGrantedAuthority> miSimpleGrantedAuthorityList = new ArrayList<MiSimpleGrantedAuthority>();
        boolean accountNonExpired = true;
        boolean accountNonLocked = true;
        boolean enabled = true;
        boolean credentialsNonExpired = true;

        // Act
        AppUser actualAppUser = new AppUser(user_id, username, password, miSimpleGrantedAuthorityList, accountNonExpired,
                accountNonLocked, enabled, credentialsNonExpired);
        boolean accountNonExpired1 = true;
        actualAppUser.setAccountNonExpired(accountNonExpired1);
        boolean accountNonLocked1 = true;
        actualAppUser.setAccountNonLocked(accountNonLocked1);
        ArrayList<MiSimpleGrantedAuthority> miSimpleGrantedAuthorityList1 = new ArrayList<MiSimpleGrantedAuthority>();
        actualAppUser.setAuthorities(miSimpleGrantedAuthorityList1);
        boolean credentialsNonExpired1 = true;
        actualAppUser.setCredentialsNonExpired(credentialsNonExpired1);
        boolean enabled1 = true;
        actualAppUser.setEnabled(enabled1);
        String password1 = "iloveyou";
        actualAppUser.setPassword(password1);
        int user_id1 = 1;
        actualAppUser.setUser_id(user_id1);
        String username1 = "janedoe";
        actualAppUser.setUsername(username1);

        // Assert
        Collection<? extends GrantedAuthority> authorities = actualAppUser.getAuthorities();
        assertSame(miSimpleGrantedAuthorityList1, authorities);
        assertEquals(miSimpleGrantedAuthorityList, authorities);
        assertEquals("iloveyou", actualAppUser.getPassword());
        assertEquals(1, actualAppUser.getUser_id().intValue());
        assertEquals("janedoe", actualAppUser.getUsername());
        assertTrue(actualAppUser.isAccountNonExpired());
        assertTrue(actualAppUser.isAccountNonLocked());
        assertTrue(actualAppUser.isCredentialsNonExpired());
        assertTrue(actualAppUser.isEnabled());
        assertEquals(
                "AppUser{user_id=1, username='janedoe', password='iloveyou', accountNonExpired=true, accountNonLocked=true,"
                        + " enabled=true, credentialsNonExpired=true}",
                actualAppUser.toString());
    }
}

