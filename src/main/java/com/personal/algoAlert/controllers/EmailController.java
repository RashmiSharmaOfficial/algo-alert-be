package com.personal.algoAlert.controllers;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestController
public class EmailController {

    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping("/sendEmail")
    public String sendEmail(){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("algoalert.service@gmail.com");
            message.setTo("rashmisharma19019@gmail.com");
            message.setSubject("Simple mail testing");
            message.setText("This is a sample email body for my first email!");

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/sendEmailFromMimeMessage")
    public String sendEmailFromMimeMessage() {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            //set the email details
            helper.setFrom("algoalert.service@gmail.com", "AlgoAlert");
            helper.setTo("rashmisharma19019@gmail.com");
            helper.setSubject("Changed Mail");
            helper.setText("This is a sample email body for my first email!");

            mailSender.send(mimeMessage);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/sendEmailWithAttachment")
    public String sendEmailWithAttachment(){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("algoalert.service@gmail.com", "AlgoAlert");
            helper.setTo("rashmisharma19019@gmail.com");
            helper.setSubject("Changed Mail");
            helper.setText("This is a sample email body for my first email!");

            helper.addAttachment("freshmen.jpeg",
                    new File("/Users/rashmisharma/Projects/sendingMail/freshmen.jpeg"));

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/sendEmailWithTemplate")
    public String sendEmailWithTemplate(){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("algoalert.service@gmail.com", "AlgoAlert");
            helper.setTo("omsharma050322@gmail.com");
            helper.setSubject("Daily Practice Reminder");

            try{
                var inputStream = Objects.requireNonNull(EmailController.class.getResourceAsStream("/templates/email-content.html"));
                helper.setText(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8), true);
            }catch(Exception e){
                return e.getMessage();
            }
            helper.addInline("frog_welcome.png",new File("/Users/rashmisharma/Projects/Static (HTML, CSS)/EmailTemplates/assets/frog_welcome.png"));

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
