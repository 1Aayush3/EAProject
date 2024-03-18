package edu.miu.cs.cs544.integration.listener;

import edu.miu.cs.cs544.integration.email.EmailMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageListener {
    public JavaMailSender javaMailSender;

    public EmailMessageListener(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @JmsListener(destination = "email.queue")
    public void processEmailMessage(EmailMessage emailMessage) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(emailMessage.getTo());
            mailMessage.setSubject(emailMessage.getSubject());
            mailMessage.setText(emailMessage.getBody());
            javaMailSender.send(mailMessage);

            System.out.println("Email sent successfully");
        } catch (MessageConversionException e) {
            System.out.println("Message conversion failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for further debugging
        }
    }
}
