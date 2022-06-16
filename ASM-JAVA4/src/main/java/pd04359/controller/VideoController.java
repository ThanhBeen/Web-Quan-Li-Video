package pd04359.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pd04359.constant.SessionAttr;
import pd04359.entity.History;
import pd04359.entity.User;
import pd04359.entity.Video;
import pd04359.service.HistoryService;
import pd04359.service.VideoService;
import pd04359.service.impl.HistoryServiceImpl;
import pd04359.service.impl.VideoServiceImpl;

/**
 * Servlet implementation class VideoController
 */
@WebServlet("/video")
public class VideoController extends HttpServlet {
	
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionParam = request.getParameter("action");
		String href = request.getParameter("id");
		HttpSession session = request.getSession();
		Video video = videoService.findByHref(href);
		User currenUser =  (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		switch(actionParam) {
			case "watch":
				doGetWatch(session, href, request, response);
				break;
			case "like":
				doGetLike(session, href, request, response);
				break;
		}
	}
	
	//locallhost:8080/ASM-JAVA4/video?action=watch&id={href}
	private void doGetWatch(HttpSession session, String href, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Video video = videoService.findByHref(href);
			request.setAttribute("video", video);
			User currenUser =  (User) session.getAttribute(SessionAttr.CURRENT_USER);
			if(currenUser != null) {
				History history = historyService.create(currenUser, video);
				request.setAttribute("flagLikedBtn", history.getIsLiked());
			}else {
				System.out.println("Rong roiiiii");
			}
			
			request.getRequestDispatcher("/views/user/video-detail.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Loi roi ban oi");
		}
	}
	
	//locallhost:8080/ASM-JAVA4/video?action=like&id={href}
	private void doGetLike(HttpSession session, String href, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		User currenUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		Boolean result = historyService.updateLikeOrUnlike(currenUser, href);
		if(result == true) {
			response.setStatus(204); //thành công nhưng không trả về response data
		}else {
			response.setStatus(400);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
