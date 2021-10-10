package mo.spring.restws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

public class ContactEntity implements Serializable {
	private static final long serialVersionUID = 395262324644695637L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Column(length = 30, nullable = false, unique = true)
	private String contactId;

	@NotBlank
	private String mobile;
	private String skype;

	@OneToOne
	@JoinColumn(name = "user_id")  
	private UserEntity user;

	public Long getId() {
		return id;
	}	

	public void setId(Long id) {
		this.id = id;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
