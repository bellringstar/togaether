package com.ssafy.dog.domain.fcm.config;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

	@PostConstruct
	public void initialize() {
		try {
			FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase-admin-sdk.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

			// if (FirebaseApp.getApps().isEmpty()) {
			// 	FirebaseApp.initializeApp(options);
			// }

			FirebaseApp.initializeApp(options);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
