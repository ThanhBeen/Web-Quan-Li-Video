package pd04359.service.impl;

import javax.servlet.ServletContext;

import pd04359.entity.User;
import pd04359.service.EmailService;
import pd04359.until.SendMailUtil;

public class EmailServiceImpl implements  EmailService {
	
	private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online Entertaiment";
	private static final String EMAI_FORGOT_PASSWORD = "Welcome to Online Entertaiment - New Passowrd";
	
	@Override
	public void sendMail(ServletContext context, User recipient, String type) {
		String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String user = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
		try {
			String content = null;
			String subject = null;
			switch(type) {
				case "welcome":
					subject = EMAIL_WELCOME_SUBJECT;
					content = "Dear " + recipient.getUsername() + " welcome to my website";
					break;
				case "forgot":
					subject = EMAI_FORGOT_PASSWORD;
					content = "Dear " + recipient.getUsername() + 
							" , your new password here: " + recipient.getPassword();
					break;
				default:
					subject = "Online Entertaiment";
					content = "Maybe this email is wrong, don't care about it";
					
			}
			SendMailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
