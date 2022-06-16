package pd04359.service;

import java.util.List;

import pd04359.dto.UserDto;
import pd04359.entity.User;

public interface UserService {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUsername(String usename);
	User login(String username, String password);
	User resetPassword(String email);
	List<User> findAll();
	List<User> findAll(int pageNumber, int pageSize);
	User register(String username, String password, String email );
	User update(User entity);
	User delete(String username );
	List<UserDto> findUserLikedVideoByVideoHref(String href);
}
