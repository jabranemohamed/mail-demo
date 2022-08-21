package org.mhj.mail;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jabrane mohamed
 */
@Service
public class EmailService {
    final Configuration configuration;
    final JavaMailSender javaMailSender;

    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(UserInfo user) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String subject = "Welcome " + user.getUsername() +" ! ";
        helper.setSubject(subject);
        helper.setTo(user.getEmail());
        String emailContent = getEmailContent(user);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    String getEmailContent(UserInfo user) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
       // configuration.setDirectoryForTemplateLoading(new File("/Users/jabranemohamed/Desktop/udemy-Workspace/mail/mail-demo/src/main/resources/"));
        configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}

