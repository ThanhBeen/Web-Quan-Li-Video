package pd04359.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pd04359.dao.AbstractDao;
import pd04359.dao.StatsDao;
import pd04359.dto.VideoLikeInfo;

public class StatDaoImpl extends AbstractDao<Object[]> implements StatsDao{

	@Override
	public List<VideoLikeInfo> findVideoLikeInfo() {
		String sql = " select v.id, v.href, v.title , sum(cast(h.isLiked as int))"
				+ " as sumLike from video v "
				+ "  inner join history h"
				+ "  on v.id = h.videoId"
				+ "  where v.isActive = 1"
				+ "  group by v.id, v.href, v.title "
				+ "  order by sum(cast(h.isLiked as int)) desc";
		List<Object[]> objects = super.findManyNativeQuery(sql);
		List<VideoLikeInfo> result = new ArrayList<>();
		objects.forEach(object -> {
			VideoLikeInfo videoLikedInfo = setDataVideoLikedInfo(object);
			result.add(videoLikedInfo);
		});
		return result;
	}
	
	private VideoLikeInfo setDataVideoLikedInfo(Object[] object) {
		VideoLikeInfo videoLikedInfo = new VideoLikeInfo();
		videoLikedInfo.setVideoId((Integer) object[0]);
		videoLikedInfo.setHref((String) object[1]);
		videoLikedInfo.setTitle((String) object[2]);
		videoLikedInfo.setTotalLike((Integer) object[3] == null ? 0 : (Integer) object[3]);
		return videoLikedInfo;
	}

}
