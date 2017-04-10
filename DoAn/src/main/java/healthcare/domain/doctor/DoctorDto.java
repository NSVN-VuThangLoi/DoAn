package healthcare.domain.doctor;

import java.util.Date;

import lombok.Data;
@Data
public class DoctorDto {
	private String doctorId;
	
	private String name;
	
	private Date birthDay;
	
	private String password;
	
	private int phoneNumber;
	
	private String email;
	
	private String position;
	
	private String addressWord;
	
	private Boolean sex;
}
