package ua.ita.smartcarservice.service.mail;
/**
 * Interface MailService is used for sending different types of e-mails.
 * Is used mostly on controllers. Only method sendEmail is used on service layer
 */
public interface MailService {

    void sendEmail(String from, String to, String subject, String text);
    void sendEMailGreatMileage(String username, String email);
    public void sendEMailOldTechnicalInspection(String username, String email);
    public void sendEmailRegistration(String username, String password, String email, String fullname);
//    public void sendEmail(String email);
}