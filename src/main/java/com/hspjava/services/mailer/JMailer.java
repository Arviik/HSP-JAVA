package com.hspjava.services.mailer;

import com.hspjava.services.config.Config;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class JMailer {

    private JMailer() {
    }

    public static void sendmail(String mail, String subject, String mailContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String emailAccount = Config.get("emailAccount", String.class);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount, Config.get("emailPassword", String.class));
            }
        });
        Message message = prepareMessage(session, emailAccount, mail, subject, mailContent);
        assert message != null;
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String emailAccount, String mail, String subject, String mailContent) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAccount));
            InternetAddress[] internetAddresses = new InternetAddress[1];
            internetAddresses[0] = new InternetAddress(mail);
            message.setRecipients(Message.RecipientType.TO, internetAddresses);
            message.setSubject(subject);
            message.setText(mailContent);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
