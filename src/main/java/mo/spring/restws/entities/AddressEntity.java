package mo.spring.restws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mo.spring.restws.shared.dto.UserDto;

@Entity(name = "addresses")
public class AddressEntity implements Serializable{
	private static final long serialVersionUID = -7385214678307729725L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(length = 30, nullable = false, unique = true)
	private String addressId;
	
	@Column(length = 30, nullable = false)
	private String city;

	@Column(length = 30, nullable = false)
	private String country;

	@Column(length = 70, nullable = false)
	private String street;

	@Column(length = 11, nullable = false)
	private String postal;

	@Column(length = 20, nullable = false)
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "user_id")  
	private UserDto user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
}
