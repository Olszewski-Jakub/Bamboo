package live.olszewski.bamboo.auth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.path:firebase_config.json}")
    private String firebaseConfigPath;

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        FirebaseApp firebaseApp;
        if (FirebaseApp.getApps().isEmpty()) {
            InputStream serviceAccount = this.getClass().getResourceAsStream("/firebase_config.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
        } else {
            firebaseApp = FirebaseApp.getInstance();
        }

        return FirebaseAuth.getInstance(firebaseApp);
    }
}