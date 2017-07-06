package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

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
        String header = request.getHeader(AUTH_HEADER_NAME);
        return Optional
                .ofNullable(request.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extractId)
                .flatMap(userDetailsService::findById)
                .map(UserAuthentication::new);
    }
}
