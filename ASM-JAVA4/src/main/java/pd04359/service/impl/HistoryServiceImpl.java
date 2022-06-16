package pd04359.service.impl;

import java.sql.Timestamp;
import java.util.List;

import pd04359.dao.HistoryDao;
import pd04359.dao.impl.HistoryDaoImpl;
import pd04359.entity.History;
import pd04359.entity.User;
import pd04359.entity.Video;
import pd04359.service.HistoryService;
import pd04359.service.VideoService;

public class HistoryServiceImpl implements HistoryService{
	
	private HistoryDao dao;
	private VideoService videoService;
	
	public HistoryServiceImpl() {
		 dao = new HistoryDaoImpl();
		 videoService = new VideoServiceImpl();
	}
	@Override
	public List<History> findByUser(String username) {
		return dao.findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		return dao.findByUserAndIsLiked(username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
		
		History existhistory = findByUserIdAndVideoId(user.getId(), video.getId());
		if(existhistory == null) {
			existhistory = new History();	
			existhistory.setUser(user);
			existhistory.setVideo(video);
			existhistory.setViewsDate(new Timestamp(System.currentTimeMillis()));
			existhistory.setIsLiked(Boolean.FALSE);
			return dao.create(existhistory);
		}
		return existhistory;
		
		
	}

	@Override
	public boolean updateLikeOrUnlike(User user, String videoHref) {
		Video video = videoService.findByHref(videoHref);
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		
		if(existHistory.getIsLiked() == Boolean.FALSE) {
			existHistory.setIsLiked(Boolean.TRUE);
			existHistory.setLikeDate(new Timestamp(System.currentTimeMillis()));
		}else {
			existHistory.setIsLiked(Boolean.FALSE);
			existHistory.setLikeDate(null);
		}
		History updateHistory = dao.update(existHistory);
		//kiểm tra nếu updateHistory != null thì trả về true, ngược lại 
		return updateHistory != null ? true : false;
	}

	@Override
	public History delete(History entity) {
		return dao.delete(entity);
	}
	
}
