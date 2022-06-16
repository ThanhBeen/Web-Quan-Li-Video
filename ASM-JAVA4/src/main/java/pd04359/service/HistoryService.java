package pd04359.service;

import java.util.List;

import pd04359.entity.History;
import pd04359.entity.User;
import pd04359.entity.Video;

public interface HistoryService {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLiked (String username);
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	History create(User user, Video video);
	boolean updateLikeOrUnlike(User user, String videoHref);
	History delete(History entity);
}
