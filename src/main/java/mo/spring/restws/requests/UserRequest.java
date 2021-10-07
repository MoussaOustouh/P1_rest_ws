package mo.spring.restws.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotNull(message = "'firstname' is required")
	@NotBlank(message = "'firstname' must not be empty")
	@Size(min = 3, message = "'firsname' must contain 3 chars at least")
	private String firstname;
	
	@NotNull(message = "'lastname' is required")
	@NotBlank(message = "'lastname' must not be empty")
	@Size(min = 3, message = "'lastname' must contain 3 chars at least")
	private String lastname;
	
	@NotNull(message = "'email' is required")
	@NotBlank(message = "'email' must not be empty")
	@Email(message = "'email' must be an email")
	private String email;
	
	@NotNull(message = "'password' is required")
	@NotEmpty(message = "'password' must not be empty")
	@Size(min = 8, message = "'password' must contain 8 chars at least")
	private String password;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
