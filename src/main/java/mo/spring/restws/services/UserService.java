package mo.spring.restws.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import mo.spring.restws.shared.dto.UserDto;

@Service
public interface UserService extends UserDetailsService{
	public UserDto createUser(UserDto userDto);
	public UserDto getUser(String email);
	public UserDto getUserByUserId(String userId);
	public UserDto updateUser(String userId, UserDto userDto);
	public void deleteUser(String userId);
	public List<UserDto> getUsers(int page, int limit);
	public List<UserDto> getUsers(int page, int limit, String search);
}
