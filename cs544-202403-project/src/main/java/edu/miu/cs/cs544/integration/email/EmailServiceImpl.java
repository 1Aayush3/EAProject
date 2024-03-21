package edu.miu.cs.cs544.integration.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
//    @Autowired
//    JmsTemplate jmsTemplate;
//    public void sendEmail(String to, String subject, String body) {
//        EmailMessage emailMessage = new EmailMessage(to, subject, body);
//        jmsTemplate.convertAndSend("email.queue", emailMessage);
//    }
}
