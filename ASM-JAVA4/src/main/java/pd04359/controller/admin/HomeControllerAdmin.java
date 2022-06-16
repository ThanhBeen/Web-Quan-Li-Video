package pd04359.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import pd04359.constant.SessionAttr;
import pd04359.dao.StatsDao;
import pd04359.dto.UserDto;
import pd04359.dto.VideoLikeInfo;
import pd04359.entity.User;
import pd04359.service.StatsService;
import pd04359.service.UserService;
import pd04359.service.impl.StatsServiceImpl;
import pd04359.service.impl.UserServiceImpl;

/**
 * Servlet implementation class HomeControllerAdmin
 */
@WebServlet(urlPatterns = { "/admin", "/admin/favorites" }, name = "HomeControllAdmin")
public class HomeControllerAdmin extends HttpServlet {
	
	private StatsService statsService = new StatsServiceImpl();
	private UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User CurrentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(CurrentUser != null && CurrentUser.getIsAdmin() == Boolean.TRUE) {
			
			String path = req.getServletPath();
			switch (path) {
			case "/admin":
				doGetHome(req, resp);
				break;
			case "/admin/favorites":
				doGetFavorites(req, resp);
				break;
			
			}
		}else {
			resp.sendRedirect("index");	
		}
		
		
		
	}
	protected void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			List<VideoLikeInfo> videos = statsService.findVideoLikeInfo();
			req.setAttribute("videos", videos);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/admin/home.jsp");
			requestDispatcher.forward(req, resp);
//		
		
	}
	
	// localhost:8080/asm-java-4/admin/favorites?href=${videoHref}
		protected void doGetFavorites(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			PrintWriter out = resp.getWriter();
			resp.setCharacterEncoding("UTF-8");	
			resp.setContentType("application/json");
			String videoHref = req.getParameter("href");
			List<UserDto> users = userService.findUserLikedVideoByVideoHref(videoHref);
			if(users.isEmpty()) {
				resp.setStatus(400);
			}else {
				ObjectMapper mapper = new ObjectMapper();
				String dataResponse = mapper.writeValueAsString(users);
				resp.setStatus(200);
				out.print(dataResponse);
				out.flush();
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
