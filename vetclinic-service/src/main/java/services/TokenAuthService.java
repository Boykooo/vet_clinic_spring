package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import security.TokenHandler;
import security.UserAuthentication;
import security.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class TokenAuthService {

    private static final String AUTH_HEADER_NAME = "X-Auth-Token";

    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public Optional<Authentication> getAuthentication(HttpServletRequest request) {


        return Optional
                .ofNullable(request.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extractId)
                .flatMap(userDetailsService::findById)
                .map(UserAuthentication::new);
    }
}
