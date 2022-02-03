package net.intuit.assignment.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import net.intuit.assignment.model.authorization.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Objects;

@Configuration
public class FirebaseConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    SecurityProperties secProps;

    @Primary
    @Bean
    public void firebaseInit() {
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource("firebase_config.json").getInputStream();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(inputStream)))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            LOGGER.info("Firebase Initialize");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
