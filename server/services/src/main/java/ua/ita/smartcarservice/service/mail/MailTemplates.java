package ua.ita.smartcarservice.service.mail;

import javax.mail.internet.MimeMessage;

public interface MailTemplates {

    public MimeMessage templateRegisterMessageWorker(String username, String password, String fullname)throws Exception;
    public MimeMessage templateSendNotification()throws Exception;
}
