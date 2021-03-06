package net.intuit.assignment.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.model.authorization.Credentials;
import net.intuit.assignment.model.authorization.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    SecurityService securityService;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    SecurityProperties securityProps;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        verifyToken(request);
        filterChain.doFilter(request, response);
    }

    private void verifyToken(HttpServletRequest request) {
        String session = null;
        FirebaseToken decodedToken = null;
        Credentials.CredentialType type = null;
        boolean strictServerSessionEnabled = securityProps.getFirebaseProps().isEnableStrictServerSession();
        Cookie sessionCookie = cookieUtils.getCookie("session");
        String token = securityService.getBearerToken(request);
//        logger.info(token);
        try {
            if (sessionCookie != null) {
                session = sessionCookie.getValue();
                decodedToken = FirebaseAuth.getInstance().verifySessionCookie(session,
                        securityProps.getFirebaseProps().isEnableCheckSessionRevoked());
                type = Credentials.CredentialType.SESSION;
            } else if (!strictServerSessionEnabled) {
                if (token != null && !token.equalsIgnoreCase("undefined")) {
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                    type = Credentials.CredentialType.ID_TOKEN;
                }
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            log.error("Firebase Exception:: {}", e.getLocalizedMessage());
        }
        PlayerEntity player = firebaseTokenToUserDto(decodedToken);
        if (player != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(player,
                    new Credentials(type, decodedToken, token, session), null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private PlayerEntity firebaseTokenToUserDto(FirebaseToken decodedToken) {
        PlayerEntity player = null;
        if (decodedToken != null) {
            player = new PlayerEntity();
            player.setUid(decodedToken.getUid());
            player.setName(decodedToken.getName());
            player.setEmail(decodedToken.getEmail());
            player.setPicture(decodedToken.getPicture());
            player.setIssuer(decodedToken.getIssuer());
            player.setEmailVerified(decodedToken.isEmailVerified());
        }
        return player;
    }

}
