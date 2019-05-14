package ua.ita.smartcarservice.service.impl.mail;

import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.service.mail.MailService;
import ua.ita.smartcarservice.service.mail.MailTemplates;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
/**
* Class MailServiceImpl is used for sending different types of e-mails.
 * Is used mostly on controllers. Only method sendEmail is used on service layer
*/
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailTemplates templates;

    public void sendEmail(String from, String to, String subject, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
//         TODO this line is necessary for sending email
//        javaMailSender.send(helper.getMimeMessage());
    }

    @Override
    public void sendEMailGreatMileage(String username, String email) {
        String text=null;
        try {
            MimeMessageParser parser = new MimeMessageParser(templates.templateSendNotification());
            parser.parse();
            text = String.format(parser.getPlainContent(), username,
                    "you car drowe too much km since the last technical inspection ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendEmail("SmartCarService",email, "technical inspection",  text) ;

    }

    @Override
    public void sendEMailOldTechnicalInspection(String username, String email) {
        String text=null;
        try {
            MimeMessageParser parser = new MimeMessageParser(
                    templates.templateSendNotification());
            parser.parse();
            text = String.format(parser.getPlainContent(), username,
                    "your last technical inspection was nearly a year ago");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendEmail("SmartCarService",email, "technical inspection",  text) ;

    }

    @Override
    public void sendEmailRegistration(String username, String password, String email, String fullname) {
        String text = null;
        try {
            MimeMessageParser parser = new MimeMessageParser(
                    templates.templateRegisterMessageWorker(username, password, fullname));
            parser.parse();
            text= parser.getPlainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendEmail("SmartCarService",email,"registration",text);
    }

    public void sendEmail(UserEntity userEntity, String subject, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
//            helper.setFrom(from);
            helper.setTo(userEntity.getEmail());
            helper.setSubject(subject);
            helper.setText(text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
