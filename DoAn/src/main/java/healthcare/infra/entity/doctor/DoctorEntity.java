package healthcare.infra.entity.doctor;

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
@Table(name = "Doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorEntity implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Version
	private long version;

	@Id
	@Column(name = "doctor_id")
	private String doctorId;

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
	
	@Column(name = "address_word")
	private String address;
	
	@Column(name = "sex")
	private Boolean sex;
	
	@Column(name ="position")
	private String position;

}
