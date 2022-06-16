package pd04359.dao.impl;

import java.util.List;

import pd04359.dao.HistoryDao;
import pd04359.entity.History;
import pd04359.entity.User;
import pd04359.entity.Video;

public class TestDao {
	public HistoryDaoImpl his = new HistoryDaoImpl();
	
	public VideoDaoImpl video = new VideoDaoImpl();
	public UserDaoImpl use = new UserDaoImpl();
	public User u;
	public Video v;
	public History h;
	public TestDao() {
		
	}

	public static void main(String[] args) {
		TestDao cc = new TestDao();
		cc.findU();
		
	}
	
	 public void testHis() {
		try {
			History h = new History();
			h = his.findByUserIdAndVideoId(2, 3);
			System.out.println(h.getUser().getId());
			System.out.println(h.getVideo().getId());
		} catch (Exception e) {
			System.out.println("Loi ");
			e.printStackTrace();
		}
	}
	 
	 public void testHis1() {
			try {
				List<History> h;
				h = his.findByUser("thanh");
				for (History history : h) {
					System.out.println(history.getUser());
				}
			} catch (Exception e) {
				System.out.println("Loi ");
				e.printStackTrace();
			}
		}
	 
	 public void testHis2() {
			try {
				History h = new History();
				User u = new User();
				u.setId(2);
				Video v = new Video();
				v.setId(3);
				h.setUser(u);
				h.setVideo(v);
				h.setViewsDate(null);
				h.setIsLiked(true);
				h.setLikeDate(null);
				his.create(h);
				System.out.println("thanh cong");
			} catch (Exception e) {
				System.out.println("Loi ");
				e.printStackTrace();
			}
		}
	 public void ucc() {
		 try {
			 User newUser = new User();
				newUser.setUsername("cc");
				newUser.setPassword("123");//bcrypt md5
				newUser.setEmail("cc@gmail.com");
				newUser.setIsAdmin(Boolean.FALSE);
				newUser.setIsActive(Boolean.TRUE);
				use.create(newUser);
				System.out.println("thanh cong");
		} catch (Exception e) {
			System.out.println("loi");
			e.printStackTrace();
		}
			
	 }
	 public void videoc() {
		 try {
			 Video newUser = new Video();
				newUser.setTitle("cc");
				newUser.setHref("123");//bcrypt md5
				newUser.setPoster(null);
				newUser.setViews(0);
				newUser.setShares(0);
				newUser.setDescription("CCCCCCCC");
				newUser.setIsActive(true);
				video.create(newUser);
				System.out.println("thanh cong video");
		} catch (Exception e) {
			System.out.println("loi");
			e.printStackTrace();
		}
			
	 }
	 public void findU() {
		 u = use.findByEmail("thanhle.221001@gmail.com");
		 u.setPassword("123");
		 use.update(u);
		 System.out.println("Thanh cong");
		 System.out.println(h.getId());
	 }
}
