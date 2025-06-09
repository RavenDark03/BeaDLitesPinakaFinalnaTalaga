/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg3rdfinalproject;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Lynch
 */
public class JavaMailSender {
    private static final String USERNAME = "beadlites859@gmail.com";
    private static final String PASSWORD = "dxcz levj dqqs uelu"; 

    public static void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {

       
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");       
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");          
        props.put("mail.smtp.host", "smtp.gmail.com");            
        props.put("mail.smtp.port", "587");                    

        // create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        // create the email message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(htmlBody, "text/html; charset=utf-8");

        Transport.send(message);
    
}
public static void main(String[] args) {
    try {
        sendHtmlEmail(
            "matthewmarcsantua@gmail.com", // <-- use your real email!
            "Test Email from JavaMailSender",
            "<h1>It works!</h1>This is a test email."
        );
        System.out.println("Email sent!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
