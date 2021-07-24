package ar.com.xeven.apiproductosspringsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static ar.com.xeven.apiproductosspringsecurity.security.UserPermission.*;
import static ar.com.xeven.apiproductosspringsecurity.security.UserRole.ADMIN;
import static ar.com.xeven.apiproductosspringsecurity.security.UserRole.CLIENTE;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/","/index.html").permitAll()
            //.antMatchers(HttpMethod.PUT, "/api/productos/**").hasAuthority(PRODUCTO_WRITE.getPermission())
            //.antMatchers(HttpMethod.POST, "/api/**").hasAuthority(PRODUCTO_WRITE.getPermission())
            //.antMatchers(HttpMethod.DELETE, "/api/productos/**").hasAnyRole(ADMIN.getRole())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails usuario1 = User.builder()
                .username("pablo")
                .password(passwordEncoder().encode("password"))
                .authorities(ADMIN.getGrantedAuthorities())
                //.roles(UserRole.ADMIN.name())
                .build();

        UserDetails usuario2 = User.builder()
                .username("natalia")
                .password(passwordEncoder().encode("password"))
                //.roles(UserRole.CLIENTE.name())
                .authorities(UserRole.CLIENTE.getGrantedAuthorities())
                .build();

        UserDetails usuario3 = User.builder()
                .username("juli")
                .password(passwordEncoder().encode("password"))
                .roles(ADMIN.name())
                .build();
        UserDetails usuario4 = User.builder()
                .username("test")
                .password(passwordEncoder().encode("password"))
                .authorities(UserRole.SELLER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3, usuario4);

    }
}
