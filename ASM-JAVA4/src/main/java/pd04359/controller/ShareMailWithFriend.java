package pd04359.controller;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShareMailWithFriend
 */
@WebServlet("/ShareMailWithFriend")
public class ShareMailWithFriend extends HttpServlet {

    public ShareMailWithFriend() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/userShareMailWithFriend").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. lay thong tin tu form
				String to = "thanhle.221001@gmail.com";
				String subject = "Share Video";
				String body = "Chào mừng bạn đến với Website của chúng tôi";
				System.out.println(body);
				
				//2. cau hinh server
				Properties p = new Properties();
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.host", "smtp.gmail.com");
				p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				p.put("mail.smtp.port", "587");
				p.put("mail.smtp.ssl.protocols", "TLSv1.2");
				// p.put("mail.smtp.ssl.trust", "*");
				//3. lay thong tin username, pass
				Session session = Session.getInstance(p, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPass() {
						String username = "thanhle.221001@gmail.com";
						String pass = "vhd221001";
						return new PasswordAuthentication(username, pass.toCharArray());
					}
				});
				
				session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
				//4. Tao message va gui mail
				
				try {
					MimeMessage msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress("thanhle.221001@gmail.com"));
					msg.setRecipients(Message.RecipientType.TO, to);
					msg.setSubject(subject, "UTF-8");
					msg.setText(body, "UTF-8", "html");
					msg.setReplyTo(msg.getFrom());
					Transport.send(msg, "thanhle.221001@gmail.com", "vhd221001");
					request.setAttribute("messageId", "Gui mail cho ban be thanh cong");
				} catch (MessagingException e) {
					request.setAttribute("messageId", "Gui mail that bai");
					e.printStackTrace();
				}
				request.getRequestDispatcher("/views/userShareMailWithFriend").forward(request, response);
			}

}
