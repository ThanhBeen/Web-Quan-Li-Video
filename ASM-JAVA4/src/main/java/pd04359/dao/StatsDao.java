package pd04359.dao;

import java.util.List;

import pd04359.dto.VideoLikeInfo;

public interface StatsDao {
	List<VideoLikeInfo> findVideoLikeInfo();
	
}
