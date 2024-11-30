package com.example.service.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/notify")
@SpringBootApplication
public class NotificationService {


	private final RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static void main(String[] args) {
		SpringApplication.run(NotificationService.class, args);
	}

	@PostMapping("/send")
	public String sendNotification(@RequestBody Map<String, String> notificationData) {
		String recipient = notificationData.get("recipient");
		String message = notificationData.get("message");
		String notificationType = notificationData.get("notificationType");

		System.out.println("Processing notification for: " + recipient);

		if ("SMS".equalsIgnoreCase(notificationType)) {
			return sendSmsNotification(recipient, message);
		}

		return "Unsupported notification type";
	}

	private String sendSmsNotification(String recipient, String message) {
		String twilioUrl = "https://api.twilio.com/2010-04-01/Accounts/ACc026e10025718966e2b81dc9ebcf32dc/Messages.json";
		// Twilio Request Payload
		MultiValueMap<String, String> requestPayload = new LinkedMultiValueMap<>();
		requestPayload.add("To", recipient); // The recipient's phone number
		requestPayload.add("From", "+1(775) 310-7738"); // Replace with your Twilio-verified phone number
		requestPayload.add("Body", message); // The SMS content

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestPayload, headers);


		try {
			// Send POST request to Twilio API
//			System.out.println(requestPayload);
			String response = restTemplate.postForObject(twilioUrl, requestEntity, String.class);
			return "SMS sent successfully. Response: " + response;
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to send SMS. Error: " + e.getMessage();
		}
	}

}
