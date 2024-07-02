package com.suffixit.kieb.async;

import com.suffixit.kieb.utils.Utils;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncMailSender {

    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(String email, String username, String password, String memberName) throws Exception {
        log.info("Thread name:"+ Thread.currentThread().getName());
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            /*String body = "Dear " + memberName + "<br>"
                    + "Your account has been created. Below are your login credentials:<br>"
                    + "Username: " + username + "<br>"
                    + "Password: " + password + "<br>"
                    + "Please <a href='google.com'>log in</a> using these credentials and change your password after the first login.<br>"
                    + "Thank you,<br>YourCompany Support";*/
            String body = Utils.getEmailTemplate(memberName, username, password, "http://kwfbd.com/login");
            String subject = "Account Created: Your Login Credentials";

           // Set additional properties for authentication and TLS
            Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "false");

            Session session = Session.getDefaultInstance(props);


            MimeMessage message = new MimeMessage(session);
           // SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("kuet1990@gmail.com");
            message.reply(false);
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(email));
            message.setText(body,"UTF-8", "html");
            message.setSubject(subject);
            mailSender.send(message);

           // log.info("Email sent to mail: {}", email);
        }  catch (AddressException e) {

            log.info("Invalid email address: " + e.getMessage());
            throw new RuntimeException("Invalid email address");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Sending email failed to: {}", email);
            throw new RuntimeException("Sending email failed");
        }
    }

}
