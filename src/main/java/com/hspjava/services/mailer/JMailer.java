package com.hspjava.services.mailer;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class JMailer {

    public static void sendmail(String mail, String code) throws MessagingException {
        System.out.println("Preparation du mail");
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String myAccountEmail = "***REMOVED***";
        String myPassword = "***REMOVED***";

        Session session=Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myPassword);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, mail, code);
        System.out.println(mail);
        Transport.send(message);
        System.out.println("Mail envoyé");
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String mail, String text){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            InternetAddress[] internetAddresses = new InternetAddress[1];
            internetAddresses[0] = new InternetAddress(mail);
            message.setRecipients(Message.RecipientType.TO, internetAddresses);
            message.setSubject("Test");
            message.setText(text);
            return message;
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
