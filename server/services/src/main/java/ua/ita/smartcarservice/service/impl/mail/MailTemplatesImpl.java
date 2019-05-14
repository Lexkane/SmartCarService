package ua.ita.smartcarservice.service.impl.mail;

import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.service.mail.MailMainTemplate;
import ua.ita.smartcarservice.service.mail.MailService;
import ua.ita.smartcarservice.service.mail.MailTemplates;

import javax.mail.internet.MimeMessage;
/**
* Class MailTemplatesImpl provides templates for using  in MailService
* @see MailService
 * All of the methods are using MailMainTemplate
 * @see MailMainTemplate
*/
@Service
public class MailTemplatesImpl implements MailTemplates {

    @Autowired
    private MailMainTemplate mainTemplate;

    public MimeMessage templateRegisterMessageWorker(String username, String password, String fullname)throws Exception{
            MimeMessage message = mainTemplate.templateMimeMessage();
            MimeMessageParser parser = new MimeMessageParser(message);
            parser.parse();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setText(String.format(
                    parser.getPlainContent(),
                    "<h3>Dear "+fullname+",<br>" +
                    "<h4> Thanks for your registration in our service. <br>" +
                    "Your credentials are" + "<br>" +
                    "username: " + username+"<br>" +
                    "password: " + password+"<br>"),true);
       return message;
    }

    public MimeMessage templateSendNotification()throws Exception{
            MimeMessage message = mainTemplate.templateMimeMessage();
            MimeMessageParser parser = new MimeMessageParser(message);
            parser.parse();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setText(String.format(
                    parser.getPlainContent(),
//                    "<h3> Hi dear user " +
                    "<h3>Dear "+"%s"+",<br>" +
                            "<h4> You have to go to the service.  <br>" +
                            "%s",
                    true)
            );
        return message;

    }


}
