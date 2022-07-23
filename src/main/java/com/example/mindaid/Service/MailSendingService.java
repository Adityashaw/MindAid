package com.example.mindaid.Service;

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
}
