package com.example.service.admission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/admission")
public class AdmissionService {

	public static void main(String[] args) {
		SpringApplication.run(AdmissionService.class, args);
	}

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/fetch/{instituteID}")
	public String fetchApplications(@PathVariable String instituteID) {
		return "Fetching applications for Institute ID: " + instituteID;
	}

	@PostMapping("/approve")
	public String approveApplication(@RequestParam String applicationID) {
		return "Application " + applicationID + " approved.";
	}

	@PostMapping("/finalize")
	public String finalizeAdmission(@RequestBody Map<String, Object> applicationData) {
		System.out.println("Finalizing admission for: " + applicationData);

		// Notify student via Notification Service
		Map<String, String> notificationRequest = Map.of(
				"recipient", (String) applicationData.get("phone"),
				"message", "Your admission has been finalized successfully!",
				"notificationType", "SMS"
		);

		String notificationResponse = restTemplate.postForObject(
				"http://localhost:8085/notify/send",
				notificationRequest,
				String.class
		);

		System.out.println("Notification Response: " + notificationResponse);
		return "Admission finalized. Notification sent.";
	}

}