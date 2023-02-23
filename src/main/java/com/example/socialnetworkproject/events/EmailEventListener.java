package com.example.socialnetworkproject.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailEventListener {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Async
    @EventListener
    public void sendEmail(SendEmailEvent event){
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject("Click to this link to reset password");
            mailMessage.setFrom(sender);
            mailMessage.setTo(event.getEmail());

            mailMessage.setText("This link will forward you to the page that will help you reset the password: " +"http://localhost.com/api/auth/forgot-password/reset/" + event.getEncodedString());
            // Sending the mail
            javaMailSender.send(mailMessage);

        }
        catch (Exception e) {
            log.error("Error while Sending Mail" +e.getMessage());
        }
    }
}
