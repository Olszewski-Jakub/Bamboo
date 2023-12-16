package live.olszewski.bamboo.auth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * This class represents the Firebase Configuration.
 * It is annotated with @Configuration to indicate that it is a source of bean definitions.
 */
@Configuration
public class FirebaseConfig {
    /**
     * This method is used to configure and return a FirebaseAuth instance.
     * It is annotated with @Bean to indicate that it is a factory for a shared instance of FirebaseAuth.
     *
     * @return A FirebaseAuth instance.
     * @throws IOException If an error occurs when reading the Firebase configuration file.
     */
    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        FirebaseApp firebaseApp;
        if (FirebaseApp.getApps().isEmpty()) {
            // Read the Firebase configuration file from the classpath
            InputStream serviceAccount = this.getClass().getResourceAsStream("/firebase_config.json");

            // Configure Firebase options with the service account credentials
            @SuppressWarnings("deprecation") FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(serviceAccount)))
                    .build();

            // Initialize a new Firebase app with the configured options
            firebaseApp = FirebaseApp.initializeApp(options);
        } else {
            // Get the default Firebase app instance
            firebaseApp = FirebaseApp.getInstance();
        }

        // Return a FirebaseAuth instance for the Firebase app
        return FirebaseAuth.getInstance(firebaseApp);
    }
}