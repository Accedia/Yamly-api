package yamly;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class YamlyApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(YamlyApplication.class, args);

        FileInputStream serviceAccount =
                new FileInputStream("./ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://yamly-eee3c.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
