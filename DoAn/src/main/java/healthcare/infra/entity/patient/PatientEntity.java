package healthcare.infra.entity.patient;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "sex")
	private Boolean sex;
}
