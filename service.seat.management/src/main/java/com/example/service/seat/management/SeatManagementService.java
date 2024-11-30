package com.example.service.seat.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/seat")
public class SeatManagementService {

	public static void main(String[] args) {
		SpringApplication.run(SeatManagementService.class, args);
	}

	@PostMapping("/allocate")
	public String allocateSeats(@RequestBody String instituteData) {
		System.out.println("Allocating seats for institute: " + instituteData);
		return "Seats allocated successfully";
	}

	@PutMapping("/update/{programId}")
	public String updateSeats(@PathVariable String programId, @RequestParam int seatsAllocated) {
		System.out.println("Updating seats for program " + programId + ": " + seatsAllocated + " seats allocated.");
		return "Seats updated successfully";
	}
}
