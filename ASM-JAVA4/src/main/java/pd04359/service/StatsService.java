package pd04359.service;

import java.util.List;

import pd04359.dto.VideoLikeInfo;

public interface StatsService {
	List<VideoLikeInfo> findVideoLikeInfo();
}
