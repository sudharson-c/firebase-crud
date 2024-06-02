package com.example.firebasecrud;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class FirebaseCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirebaseCrudApplication.class, args);
    }

    @PostConstruct
    public void initFirebase() throws IOException {
        FileInputStream serviceAccount =
            new FileInputStream("src/main/resources/firebase-service-account.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

        FirebaseApp.initializeApp(options);
    }
}
