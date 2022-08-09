package com.example.mindaid.Service;

import com.example.mindaid.Model.Contact;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailSendingService {
    @Resource
    JavaMailSender javaMailSender;

    public void sendEmailToNewApplicant(String recipientEmail, String name) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("tamzidkhan015@gmail.com", "MindAid Support");
        helper.setTo(recipientEmail);

        String subject = "MindAid Therapist Recruitement";

        String content = "<p>Hello,</p>"+name
                + "<p>We have received your application.We look forward to evaluate you.</p>"
                + "<p>Please send your cv.</p>"
//                + "<p>We look forward to evaluate you</p>"
//                + "<p><a href=\"" + link + "\">Change my password</a></p>"
//                + "<br>"
                + "<p>Thanks,</p>"
                + "<p>Team MindAid</p>"
                + "<p>mindaid.help@gmail.com</p>"
                + "<p>ABC,8-b,xyz,Dhaka.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        javaMailSender.send(message);
    }

    public void contactMail(Contact contact) throws MessagingException, UnsupportedEncodingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("tamzidkhan015@gmail.com", "MindAid Support");
        helper.setTo("mindaid.help@gmail.com");

        String subject = contact.getSubject();

        String content = "<p>Hello,</p>"
                + "<p>PLease reply to the following query: </p>"+
                + "<p>"+ contact.getText()+"</p>"
                +"<p>From: </p>"+ "<p>"+ contact.getName()+"</p>"
                +"<p>Email: </p>"+ "<p>"+ contact.getEmail()+"</p>"

                + "<p>Thanks,</p>"
                + "<p>Team MindAid</p>"
                + "<p>mindaid.help@gmail.com</p>"
                + "<p>ABC,8-b,xyz,Dhaka.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        javaMailSender.send(message);
    }
}
