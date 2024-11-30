package com.example.service.notification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Collections;


@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Add Basic Auth Interceptor
        String username = "SKe318578a252dd4044818417408f8a892"; // Replace with your Twilio Account SID
        String password = "AXp96rivFNkrqrf8nj9hlRyRs1ip4FU5"; // Replace with your Twilio Auth Token
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            request.getHeaders().add("Authorization", "Basic " + base64Credentials);
            return execution.execute(request, body);
        };

        restTemplate.setInterceptors(Collections.singletonList(interceptor));
        return restTemplate;
    }
}