package gmailApi;
import com.sun.istack.internal.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {

    public static void sendLetter(@NotNull String receiver, @NotNull String tittle, @NotNull String text) {

        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("properties")){
            props.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("aco17.vlad.ivan.task@gmail.com", "aco17password");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aco17.vlad.ivan.task@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiver));
            message.setSubject(tittle);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

