package mo.spring.restws.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotNull(message = "Must not be null")
	@NotBlank(message = "Firstname must not be empty")
	@Size(min = 3, message = "Must contain 3 chars at least")
	private String firstname;
	
	@NotNull(message = "Must not be null")
	@NotBlank(message = "Lastname must not be empty")
	@Size(min = 3, message = "Must contain 3 chars at least")
	private String lastname;
	
	@NotNull(message = "Must not be null")
	@NotBlank(message = "Email must not be empty")
	@Email(message = "Must be an email")
	private String email;
	
	@NotNull(message = "Must not be null")
	@NotEmpty(message = "Firstname must not be empty")
	@Size(min = 8, message = "Must contain 8 chars at least")
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
