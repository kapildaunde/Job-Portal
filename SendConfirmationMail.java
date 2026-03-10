package com.deepak.backend;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendConfirmationMail {

    // TODO: Replace with your actual Gmail credentials
    private static final String FROM_EMAIL    = "your_email@gmail.com";
    private static final String FROM_PASSWORD = "your_app_password";

    public static void sendConfirmationMail(String toEmail, String subject, String message) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });

        try {
            MimeMessage mime = new MimeMessage(session);
            mime.setFrom(new InternetAddress(FROM_EMAIL));
            mime.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mime.setSubject(subject);
            mime.setText(message);
            Transport.send(mime);
        } catch (Exception e) {
            // Email sending is optional - log but don't crash
            System.out.println("Email send failed (optional): " + e.getMessage());
        }
    }
}
