package pd04359.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
 * Servlet implementation class HomeController
 */
@WebServlet({"/index", "/favorites", "/history"})
public class HomeController extends HttpServlet {
	//Inject, // IOC
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	private  static final int VIDEO_MAX_PAGE_SIZE = 4;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getServletPath();
		System.out.println(path);
		switch (path) {
		case "/index": 
			doGetIndex(request, response);
			break;
		case "/favorites": 
			doGetFavorites(session, request, response);
			break;
		case "/history": 
			doGetHistory(session, request, response);;
			break;
		}
		
	}
	
	//locallhost:8080/ASM-JAVA4/index?page=2
	private void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Video> countVideo = videoService.findAll();
		int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
		request.setAttribute("maxPage", maxPage);
		
		List<Video> videos;
		String pageNumber = request.getParameter("page");
		
		if(pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
			videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currentPage", 1);
		}else {
			videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}

		if(videos.isEmpty()) {
			System.out.println("Rong");
		}
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
		// loi r a
	}
	
	private void doGetFavorites(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);		
		List<History> histories = historyService.findByUserAndIsLiked(user.getUsername());
		List<Video> videos = new ArrayList<>();
		//histories.forEach(item -> videos.add(item.getVideo()));
		for (History item : histories) {
			videos.add(item.getVideo());
		}
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/favorites.jsp").forward(request, response);

	}
	// em tai hien lai loi da gap di da
	
	private void doGetHistory(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);		
		List<History> histories = historyService.findByUser(user.getUsername());
		List<Video> videos = new ArrayList<>();
		//histories.forEach(item -> videos.add(item.getVideo()));
		for (History item : histories) {
			videos.add(item.getVideo());
		}
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/history.jsp").forward(request, response);

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
