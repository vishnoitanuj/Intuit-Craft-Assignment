package net.intuit.assignment.security;

import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.model.authorization.Credentials;
import net.intuit.assignment.model.authorization.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private CookieUtils cookieUtils;

    @Autowired
    private SecurityProperties securityProperties;

    public PlayerEntity getUser() {
        PlayerEntity userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof PlayerEntity) {
            userPrincipal = ((PlayerEntity) principal);
        }
        return userPrincipal;
    }

    public Credentials getCredentials() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (Credentials) securityContext.getAuthentication().getCredentials();
    }

    public boolean isPublic() {
        return securityProperties.getAllowedPublicApis().contains(httpServletRequest.getRequestURI());
    }

    public String getBearerToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            bearerToken = authorization.substring(7);
        }
        return bearerToken;
    }
}
