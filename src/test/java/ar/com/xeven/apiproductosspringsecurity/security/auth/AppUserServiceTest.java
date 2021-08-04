package ar.com.xeven.apiproductosspringsecurity.security.auth;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppUserService.class})
@ExtendWith(SpringExtension.class)
public class AppUserServiceTest {
    @MockBean
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @Test
    public void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: Nothing to assert: the constructed class does not have observers (e.g. getters or public fields).
        //   Add observers (e.g. getters or public fields) to the class.
        //   See https://diff.blue/R002

        // Arrange
        AppUserRepository appUserRepository = mock(AppUserRepository.class);

        // Act
        new AppUserService(appUserRepository);
    }

    @Test
    public void testConstructor2() {
        // TODO: This test is incomplete.
        //   Reason: Nothing to assert: the constructed class does not have observers (e.g. getters or public fields).
        //   Add observers (e.g. getters or public fields) to the class.
        //   See https://diff.blue/R002

        // Arrange
        AppUserRepository appUserRepository = mock(AppUserRepository.class);

        // Act
        new AppUserService(appUserRepository);
    }

    @Test
    public void testLoadUserByUsername() throws UsernameNotFoundException {
        // Arrange
        AppUser appUser = new AppUser();
        appUser.setPassword("iloveyou");
        appUser.setAccountNonLocked(true);
        appUser.setAccountNonExpired(true);
        appUser.setUsername("janedoe");
        appUser.setUser_id(1);
        appUser.setCredentialsNonExpired(true);
        appUser.setAuthorities(new ArrayList<MiSimpleGrantedAuthority>());
        appUser.setEnabled(true);
        when(this.appUserRepository.findAppUserByUsername(anyString())).thenReturn(appUser);
        String s = "foo";

        // Act
        UserDetails actualLoadUserByUsernameResult = this.appUserService.loadUserByUsername(s);

        // Assert
        assertSame(appUser, actualLoadUserByUsernameResult);
        verify(this.appUserRepository).findAppUserByUsername(anyString());
    }

    @Test
    public void testLoadUserByUsername2() throws UsernameNotFoundException {
        // Arrange
        AppUser appUser = new AppUser();
        appUser.setPassword("iloveyou");
        appUser.setAccountNonLocked(true);
        appUser.setAccountNonExpired(true);
        appUser.setUsername("janedoe");
        appUser.setUser_id(1);
        appUser.setCredentialsNonExpired(true);
        appUser.setAuthorities(new ArrayList<MiSimpleGrantedAuthority>());
        appUser.setEnabled(true);
        when(this.appUserRepository.findAppUserByUsername(anyString())).thenReturn(appUser);
        String s = "foo";

        // Act
        UserDetails actualLoadUserByUsernameResult = this.appUserService.loadUserByUsername(s);

        // Assert
        assertSame(appUser, actualLoadUserByUsernameResult);
        verify(this.appUserRepository).findAppUserByUsername(anyString());
    }
}

