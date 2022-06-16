package pd04359.service;

import javax.servlet.ServletContext;

import pd04359.entity.User;

public interface EmailService {
	void sendMail(ServletContext context, User recipient, String type);
}
