package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Mail {
        public static void sendPinEmail(String sourceEmail, String sourcePwd, String desEmail, String subject, String username) {

        String body = "Dear "+ username +
                "\n" +
                "Thank you for creating an account with our app! We are thrilled to have you on board and look forward to serving you. If you have any questions or need assistance, please feel free to reach out to our support team.\n" +
                "\n" +
                "Best regards, ICity Team" ;
        sendMail(sourceEmail, sourcePwd, desEmail, subject, body);
    }
    private static void sendMail(String sourceEmail, String sourcePwd, String desEmail, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Créer une session de messagerie
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sourceEmail, sourcePwd);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sourceEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(desEmail));
            message.setSubject(subject);
            message.setText(body);



            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {

            if (e instanceof AuthenticationFailedException) {
                System.out.println("L'authentification a échoué. Veuillez vérifier vos informations d'identification.");
            } else {
                e.printStackTrace();
            }
        }
    }
    private static String handlePin(String verificationCode) {
        return verificationCode;
    }

    public static void main(String[] args) {

        sendPinEmail("icity.app.3a18@gmail.com","rmms ykaw ohyg bfnh","onsbahri27@yahoo.com","Welcome to ICity", "Ons Bahri");
    }
}