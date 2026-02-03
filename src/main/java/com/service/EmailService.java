package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String familyName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Family Invitation");

            String htmlContent = String.format("""
                    <div>You are invited to join Expense Manager from <b>%s</b>.</div>

                    <a href="http://localhost:8080/familyGroup?role=join"
                       style="padding:10px 20px;
                              background:green;
                              color:white;
                              text-decoration:none;
                              border-radius:5px;">
                       YES
                    </a>

                    <a href="http://localhost:8080/response"
                       style="padding:10px 20px;
                              background:red;
                              color:white;
                              text-decoration:none;
                              border-radius:5px;
                              margin-left:10px;">
                       NO
                    </a>
                                                """, familyName);

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void sendEmail(String toEmail){
    // SimpleMailMessage message = new SimpleMailMessage();
    // message.setFrom("expensemanagerofficial@gmail.com");
    // message.setTo(toEmail);
    // message.setSubject("Family Invitation");
    // message.setText("You are invited to join Expense Manager.");

    // eMailSender.send(message);
    // System.out.println("Mail send successfully...");
    // }
}
