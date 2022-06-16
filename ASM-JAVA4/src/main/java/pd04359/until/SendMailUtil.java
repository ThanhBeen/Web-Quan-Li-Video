package pd04359.until;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailUtil {
	public static void sendEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {
 
        // sets SMTP server properties
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.ssl.protocols", "TLSv1.2");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(p, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPass() {
				String username = "thanhle.221001@gmail.com";
				String pass = "vhd221001";
				return new PasswordAuthentication(username, pass);
			}
		});
 
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        // creates a new e-mail message


        //InternetAddress[] toAddresses = { new InternetAddress(toAddress) };

        try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("thanhle.221001@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			msg.setSubject(subject, "UTF-8");
			msg.setText(message, "UTF-8", "html");
			msg.setReplyTo(msg.getFrom());
			msg.setSentDate(new Date());
			Transport.send(msg, "thanhle.221001@gmail.com", "vhd221001");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
 
    }
}
