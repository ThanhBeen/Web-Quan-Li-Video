package pd04359.dao.impl;

import java.util.List;

import pd04359.dao.AbstractDao;
import pd04359.dao.HistoryDao;
import pd04359.entity.History;

public class HistoryDaoImpl  extends AbstractDao<History> implements HistoryDao{

	@Override
	public List<History> findByUser(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0"
				+ " AND o.video.isActive = 1 ORDER BY o.viewsDate DESC ";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.isLiked = 1"
				+ " AND o.video.isActive = 1 ORDER BY o.viewsDate DESC ";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		String sql = "SELECT o FROM History o WHERE o.user.id = ?0 "
				+ "AND o.video.id = ?1 AND o.video.isActive = 1";
		return super.findOne(History.class, sql, userId, videoId);
	}

	@Override
	public History create(History entity) {
		return super.create(entity);
		
	}

	@Override
	public History update(History entity) {
		return super.update(entity);
	}

	@Override
	public History delete(History entity) {
		return super.delete(entity);
	}

}
