package com.example.service.quota.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/quota")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostMapping("/setup")
	public String setupQuota(@RequestBody String instituteData) {
		System.out.println("Setting up quotas for institute: " + instituteData);
		return "Quotas set up successfully";
	}

	@GetMapping("/validate/{programId}")
	public String validateQuota(@PathVariable String programId) {
		System.out.println("Validating quotas for program " + programId);
		return "Quota validation successful";
	}
}
