package com.example.service.admission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RequestMapping("/admission")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
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
	public String finalizeAdmission(@RequestBody String applicationData) {
		System.out.println("Finalizing admission for application: " + applicationData);

		// Communicate with Notification Service
		String notificationResponse = restTemplate.postForObject("http://localhost:8085/notify/send", applicationData, String.class);

		return "Admission finalized. Notification Response: " + notificationResponse;
	}

}
