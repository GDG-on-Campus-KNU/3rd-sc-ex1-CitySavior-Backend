package GDSCKNU.CitySavior.global.jwt.entity;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class NoPasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public NoPasswordAuthenticationToken(Object principal, Object credentials,
                                         Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
