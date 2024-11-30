package com.example.service.hub.integration;

import org.example.common.AdmissionEvent;
import org.example.common.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IntegrationHubService{

	@Autowired
	private RestTemplate restTemplate;


	public static void main(String[] args) {
		SpringApplication.run(IntegrationHubService.class, args);
	}


    // Kafka Listener to handle Admission Events
	@KafkaListener(topics = "admission-topic", groupId = "integration-hub-group")
	public void handleAdmissionEvent(AdmissionEvent event) {
		System.out.println("Received Admission Event: " + event);

		// Send notification to student
		String notificationResponse = sendNotification(
				event.getPhone(),
				"Your admission has been finalized successfully!",
				"SMS"
		);
		System.out.println("Notification Response: " + notificationResponse);

		// Log event
		String logResponse = logEvent(
				"Admission Finalized",
				"Application ID: " + event.getApplicationID() + ", Student ID: " + event.getStudentID()
		);
		System.out.println("Log Event Response: " + logResponse);
	}

	// Forward notification to the Notification Service
	public String sendNotification(String recipient, String message, String notificationType) {
		String notificationServiceUrl = "http://localhost:9094/notify/send";

		NotificationRequest notificationRequest = new NotificationRequest(recipient, message, notificationType);
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(notificationServiceUrl, notificationRequest, String.class);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to send notification: " + e.getMessage();
		}
	}

	// Simulate logging events for monitoring
	public String logEvent(String eventType, String eventDetails) {
		// In a real-world scenario, this would be stored in a database or monitoring system
		System.out.println("Logging Event: " + eventType + " - " + eventDetails);
		return "Event logged successfully";
	}
}

