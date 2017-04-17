package healthcare.app.patient;

import java.util.Date;

import lombok.Data;

@Data
public class InsertPatientCommand {
	private String userId;
	
	private String name;
	
	private Date birthDay;
	
	private String password;
	
	private int phoneNumber;
	
	private String email;
	
	private String address;
	
	private Boolean sex;
	
}
