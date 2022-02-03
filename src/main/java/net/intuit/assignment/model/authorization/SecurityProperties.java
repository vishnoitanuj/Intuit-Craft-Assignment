package net.intuit.assignment.model.authorization;

import lombok.Data;
import net.intuit.assignment.model.authorization.CookieProperties;
import net.intuit.assignment.model.authorization.FirebaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("security")
@Data
public class SecurityProperties {

	CookieProperties cookieProps;
	FirebaseProperties firebaseProps;
	boolean allowCredentials;
	List<String> allowedOrigins;
	List<String> allowedHeaders;
	List<String> exposedHeaders;
	List<String> allowedMethods;
	List<String> allowedPublicApis;

}
