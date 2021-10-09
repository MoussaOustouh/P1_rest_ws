package mo.spring.restws.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mo.spring.restws.entities.UserEntity;
import mo.spring.restws.repositories.UserRepository;
import mo.spring.restws.services.UserService;
import mo.spring.restws.shared.Utils;
import mo.spring.restws.shared.dto.AddressDto;
import mo.spring.restws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());
		if(checkUser != null) {
			throw new RuntimeException("User Already exists.");
		}
		
		for (int i = 0; i < userDto.getAddresses().size(); i++) {
			 AddressDto addressDto = userDto.getAddresses().get(i);
			 addressDto.setUser(userDto);
			 addressDto.setAddressId(utils.generateStringId(20));
			 userDto.getAddresses().set(i, addressDto);
		}

		
//		UserEntity userEntity = new UserEntity();
//		BeanUtils.copyProperties(userDto, userEntity);
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		
		userEntity.setUserId(utils.generateStringId(10));
		
		UserEntity userEntity1 = userRepository.save(userEntity);
		
//		UserDto userDto1 = new UserDto();
//		BeanUtils.copyProperties(userEntity1, userDto1);
		UserDto userDto1 = modelMapper.map(userEntity1, UserDto.class);
		
		return userDto1;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null){
			throw new UsernameNotFoundException(email);
		}
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if(userEntity == null) {
			throw new UsernameNotFoundException(id);
		}
		
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());
		UserEntity updateUser = userRepository.save(userEntity);
		
		UserDto user = new UserDto();
		BeanUtils.copyProperties(updateUser, user);
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		userRepository.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		if(page > 0) {
			page--;
		}
		
		List<UserDto> usersDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		
		List<UserEntity> usersEntityList = usersPage.getContent();
		usersEntityList.forEach(user -> {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			usersDto.add(userDto);
		});
		
		return usersDto;  
	}
	
	
}
