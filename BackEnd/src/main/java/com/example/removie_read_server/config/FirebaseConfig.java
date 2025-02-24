package com.example.removie_read_server.config;


import com.example.removie_read_server.exception.FireBaseInitException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;


@Configuration
public class FirebaseConfig {
    private final static Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @Value("${firebase.config.path}")
    private String firebaseConfigPath;


    @PostConstruct
    public void initializeFirebase() throws IOException {
        try (FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath)) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            logger.error("FireBase 인증 실패 {}", e.getMessage());

            throw new FireBaseInitException(e.getMessage());
        }
    }
}