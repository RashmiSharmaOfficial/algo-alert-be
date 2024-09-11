package com.personal.algoAlert.system.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmailScheduler {
    private final RestTemplate restTemplate;

    public EmailScheduler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 * * * * *")
    public void sendScheduledEmail(){
        try{
            String url = "http://localhost:8080/sendEmailWithTemplate";
            restTemplate.getForObject(url, String.class);
            System.out.println("Email sent successfully!");
        }catch(Exception e){
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
