package ua.ita.smartcarservice.service.impl.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.service.mail.MailMainTemplate;
import ua.ita.smartcarservice.service.mail.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Class MailMainTemplateImpl provides main templates for using in any other templates.
 * For sending emails please use MailService and its implementations.
 * @see MailService
*/
@Service
public class MailMainTemplateImpl implements MailMainTemplate {

    @Autowired
    private JavaMailSender javaMailSender;

    public MimeMessage templateMimeMessage() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setText("<h2>Hi from SmartCarService!\n" +
                "%s\n" +
                "<br><a href = 'http://localhost:9501'> SmartCarService</a>"
                ,true);
        return message;
    }
}
