package com.tishina.integration;

import com.tishina.model.Client;
import com.tishina.model.Order;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collections;
import java.util.Properties;

//https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
public class SendMailTLS {
    private static final String serverFrom = "bookonline_shop_service@gmail.com";
    public static final String username = "kriverson13@gmail.com"; // need to authenticate?
    private static final String password = "nKraynov"; // need to authenticate?
    private static final Session session;
    static {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public static void sendMail(Order order) {
        Client client = order.getClient();
        String usernameTo = client.getEmail();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(serverFrom));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(usernameTo));
            message.setSubject("Order #" + order.getId() +" created");
            message.setText("Dear " + client.getName()
                    + "\n\nYou ordered books in our shop with price = "+order.getPrice()
                    + "\nDetails you can see here: http://localhost:8080/Tishina/order.jsp?id="+order.getId());

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
