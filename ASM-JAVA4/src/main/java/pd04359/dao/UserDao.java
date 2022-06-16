package pd04359.dao;

import java.util.List;
import java.util.Map;

import pd04359.entity.User;

public interface UserDao {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUsername(String usename);
	User findByUsernameAndPassword(String username, String password);
	List<User> findAll();
	List<User> findAll(int pageNumber, int pageSize);
	User create(User entity);
	User update(User entity);
	User delete(User entity);
	List<User> findUserLikedVideoByVideoHref(Map<String, Object> params);
}
