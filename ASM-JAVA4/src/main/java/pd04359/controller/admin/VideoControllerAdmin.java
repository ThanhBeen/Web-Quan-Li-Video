package pd04359.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import pd04359.constant.SessionAttr;
import pd04359.entity.User;
import pd04359.entity.Video;
import pd04359.service.VideoService;
import pd04359.service.impl.VideoServiceImpl;


/**
 * Servlet implementation class VideoControllerAdmin
 */
@WebServlet(urlPatterns = {"/admin/video" }, name = "VideoControllerOfAdmin")
public class VideoControllerAdmin extends HttpServlet {
	
	private  VideoService videoService = new VideoServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User CurrentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(CurrentUser != null && CurrentUser.getIsAdmin() == Boolean.TRUE) {
			String action = request.getParameter("action");
			switch (action) {
			case "view":
				doGetOverView(request, response);
				break;
			case "delete":
				doGetDelete(request, response);
				break;
			case "add":
				request.setAttribute("isEdit", false);
				doGetAdd(request, response);
				break;
			case "edit":
				request.setAttribute("isEdit", true);
				doGetEdit(request, response);
				break;
			}
		}else {
			response.sendRedirect("index");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User CurrentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(CurrentUser != null && CurrentUser.getIsAdmin() == Boolean.TRUE) {
			String action = request.getParameter("action");
			switch (action) {
			case "add":
				doPostAdd(request, response);
				break;
			case "edit":
				doPostEdit(request, response);
				break;
			}
			
			
		}else {
			response.sendRedirect("index");	
		}
	}
	
	protected void doGetOverView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Video> videos = videoService.findAll();
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/admin/overview.jsp").forward(request, response);
		
		
	}
	
	// /asm-java-4/admin/video?action=delete&href={href}
		protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("application/json");
			String 	href = req.getParameter("href");
			Video videoDeleted = videoService.delete(href);	//update set isActive = false
			if(videoDeleted != null) {
				resp.setStatus(204);
			}else {
				resp.setStatus(400);

			}
		}
		
		// /asm-java-4/admin/video?action=edit&href={href}

		protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/video-edit.jsp");
			requestDispatcher.forward(req, resp);
			
		}
		
		protected void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String href = req.getParameter("href");
			Video video = videoService.findByHref(href);
			req.setAttribute("video", video);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/video-edit.jsp");
			requestDispatcher.forward(req, resp);
			
		}
		
		
		
		protected void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("application/json");
			String 	title = req.getParameter("title");
			String 	description = req.getParameter("description");
			String 	href = req.getParameter("newHref");
			String 	poster = req.getParameter("poster");

			Video video = new Video();
			video.setTitle(title);
			video.setHref(href);
			video.setDescription(description);
			video.setPoster(poster);
			Video videoReturn = videoService.create(video);
			if(videoReturn != null) {
				resp.setStatus(204);
			}else {
				resp.setStatus(400);

			}
			
		}
		protected void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("application/json");
			String 	title = req.getParameter("title");
			String 	description = req.getParameter("description");
			String 	href = req.getParameter("newHref");
			String 	poster = req.getParameter("poster");
			String 	hrefOrigin = req.getParameter("hrefOrigin");

			Video video = videoService.findByHref(hrefOrigin);
			video.setTitle(title);
			video.setHref(href);
			video.setDescription(description);				 
			video.setPoster(poster);
			
			Video videoReturn = videoService.update(video);
			if(videoReturn != null) {
				resp.setStatus(204);
			}else {
				resp.setStatus(400);

			}
			
		}
		

}
