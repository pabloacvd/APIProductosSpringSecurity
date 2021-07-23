package ar.com.xeven.apiproductosspringsecurity.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ar.com.xeven.apiproductosspringsecurity.security.UserPermission.*;

public enum UserRole {
    CLIENTE(Set.of(USER_READ, PRODUCTO_READ)),
    ADMIN(Set.of(USER_READ, USER_WRITE, PRODUCTO_READ, PRODUCTO_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permisos = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permisos.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permisos;
    }
}
