package pd04359.service.impl;

import java.util.List;

import pd04359.dao.StatsDao;
import pd04359.dao.impl.StatDaoImpl;
import pd04359.dto.VideoLikeInfo;
import pd04359.service.StatsService;

public class StatsServiceImpl implements StatsService {
	
	private StatsDao statDao;
	
	public StatsServiceImpl() {
		statDao = new StatDaoImpl();
	}

	@Override
	public List<VideoLikeInfo> findVideoLikeInfo() {
		return statDao.findVideoLikeInfo();
	}
	

}
