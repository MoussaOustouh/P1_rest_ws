package mo.spring.restws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class AddressEntity implements Serializable {
	private static final long serialVersionUID = -7385214678307729725L;

	@Id
	@GeneratedValue
	private Long id;

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
	private UserEntity user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
