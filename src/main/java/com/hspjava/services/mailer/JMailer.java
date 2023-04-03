package com.hspjava.services.mailer;

import com.hspjava.services.config.Config;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class JMailer {

    private JMailer() {
    }

    public static void sendmail(String mail, String code) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        String emailAccount = Config.get("emailAccount", String.class);
        String emailPassword = Config.get("emailPassword", String.class);
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount, emailPassword);
            }
        });
        Message message = prepareMessage(session, emailAccount, mail, code);
        assert message != null;
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String mail, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            InternetAddress[] internetAddresses = new InternetAddress[1];
            internetAddresses[0] = new InternetAddress(mail);
            message.setRecipients(Message.RecipientType.TO, internetAddresses);
            message.setSubject("Test");
            message.setText(text);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
