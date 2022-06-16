package pd04359.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pd04359.constant.SessionAttr;
import pd04359.entity.User;
import pd04359.service.EmailService;
import pd04359.service.UserService;
import pd04359.service.impl.EmailServiceImpl;
import pd04359.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {"/login", "/logout", "/register" , "/forgotPass", "/changePass"})
public class UserController extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();
	private EmailService emailService = new EmailServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getServletPath();
		System.out.println(path);
		switch (path) {
		case "/login": 
			doGetLogin(request, response);
			break;
		case "/register": 
			doGetRegister(request, response);
			break;
		case "/logout": 
			doGetLogout(session, request, response);;
			break;
		case "/forgotPass": 
			doGetForgetPass(request, response);;
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getServletPath();
//		System.out.println(path);
		switch (path) {
		case "/login": 
			doPostLogin(session, request, response);
			break;
		case "/register": 
			doPostRegister(session, request, response);
			break;
		case "/forgotPass": 
			doPostForgetPass(request, response);
			break;
		case "/changePass": 
			doPostChangePass(session, request, response);
			break;
		}

	}

	private void doGetLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
	}
	
	private void doGetRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
	}
	
	private void doGetLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session.removeAttribute(SessionAttr.CURRENT_USER);
		response.sendRedirect("login");
	}
	
	private void doGetForgetPass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/forgot-pass.jsp").forward(request, response);
	}
	
	private void doPostLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = userService.login(username, password);
		
		if(user != null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			response.sendRedirect("index");
			session.removeAttribute(SessionAttr.MESSAGE);
		}else {
			session.setAttribute(SessionAttr.MESSAGE, "Invalid username or password");
//			request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
			response.sendRedirect("login");
		}
	}
	
	private void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cfmpass = request.getParameter("cfmpass");
		String email = request.getParameter("email");
		
		User user = userService.register(username, password, email);
		
		if(user != null) {
			emailService.sendMail(getServletContext(), user, "welcome");
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			System.out.println("Dang ky thanh cong");
			response.sendRedirect("login");
		}else {
			response.sendRedirect("register");
		}
	}
	
	private void doPostForgetPass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String email = request.getParameter("email");
		User userWithNewPass = userService.resetPassword(email);
		
		if(userWithNewPass != null) {
			emailService.sendMail(getServletContext(), userWithNewPass, "forgot");
			response.setStatus(204);
		}else {
			response.setStatus(400);
		}
	}
	
	private void doPostChangePass(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String currentPass = request.getParameter("currentPass");
		String newPass = request.getParameter("newPass");
		
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		if(currentUser.getPassword().equals(currentPass)) {
			currentUser.setPassword(newPass);
			User updateUser = userService.update(currentUser);
			
			
			if(updateUser != null) {
				session.setAttribute(SessionAttr.CURRENT_USER, updateUser);
				//emailService.sendMail(getServletContext(), updateUser, "forgot");
				response.setStatus(204);
			}else {
				response.setStatus(400);
			}
		}else {
			response.setStatus(400);
		}
		
	}

}
