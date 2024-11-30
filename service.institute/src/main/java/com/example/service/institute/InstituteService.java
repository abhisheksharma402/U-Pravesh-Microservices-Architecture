package com.example.service.institute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RequestMapping("/institute")
public class InstituteService {

	@Autowired
	private RestTemplate restTemplate;

    public static void main(String[] args) {
		SpringApplication.run(InstituteService.class, args);
	}


	@PostMapping("/register")
	public String registerInstitute(@RequestBody String instituteData) {
		System.out.println("Institute registered: " + instituteData.toString());

		// Communicate with Seat Management Service
		String seatResponse = restTemplate.postForObject("http://localhost:8082/seat/allocate", instituteData.toString(), String.class);

		// Communicate with Quota Management Service
		String quotaResponse = restTemplate.postForObject("http://localhost:8084/quota/setup", instituteData.toString(), String.class);

		return "Institute registered. Seat Response: " + seatResponse + ". Quota Response: " + quotaResponse;
	}

	@PutMapping("/update/{instituteID}")
	public String updateInstitute(@PathVariable String instituteID, @RequestBody String updatedData) {
		return "Institute " + instituteID + " updated with data: " + updatedData;
	}

}
