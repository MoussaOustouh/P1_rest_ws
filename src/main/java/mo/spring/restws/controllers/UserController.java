package mo.spring.restws.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mo.spring.restws.exceptions.UserException;
import mo.spring.restws.requests.UserRequest;
import mo.spring.restws.responses.ErrorMessages;
import mo.spring.restws.responses.UserResponse;
import mo.spring.restws.services.UserService;
import mo.spring.restws.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE) /* bach nred response b XML format */
	//@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})  /* bach nred response b XML format ou json */
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userDto = userService.getUserByUserId(id);
		
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping
	public List<UserResponse> getAllUsers(@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="limit", defaultValue="15") int limit) {
		List<UserResponse> usersResponse = new ArrayList<>();
		
		List<UserDto> users = userService.getUsers(page, limit);
		
		users.forEach(user -> {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);
			usersResponse.add(userResponse);
		});
		 
		return usersResponse;
	}
	
	//@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE) /*nakhd request as XML o nred response as XML*/
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws UserException {
		if(userRequest.getFirstname().isEmpty()) {
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userRequest, userDto);

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createdUser, userResponse);
		
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto updateUser = userService.updateUser(id, userDto);
		
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);
		
		return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/{id}")
	public Object deleteUser(@PathVariable("id") String id) {
		userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
