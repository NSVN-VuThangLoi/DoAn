package healthcare.infra.entity.login;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "hust_info_login")
@Data
@AllArgsConstructor
public class LoginPatient {
	@Version
	private long version;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "birthday")
	private Date birthDay;
	
	@Column(name = "phone_number")
	private Integer phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "sex")
	private Boolean sex;

}
