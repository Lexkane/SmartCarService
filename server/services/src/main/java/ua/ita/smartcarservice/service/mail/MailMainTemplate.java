package ua.ita.smartcarservice.service.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Interface MailMainTemplate provides main templates for using in any other templates.
 * For sending emails please use MailService and its implementations.
 * @see MailService
 */
public interface MailMainTemplate {
    public MimeMessage templateMimeMessage()throws MessagingException;
}
