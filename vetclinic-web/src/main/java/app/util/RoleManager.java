package app.util;

import enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public final class RoleManager {

    public static boolean hasRole(Authentication authentication, Role role) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(role)){
                return true;
            }
        }

        return false;
    }

}
