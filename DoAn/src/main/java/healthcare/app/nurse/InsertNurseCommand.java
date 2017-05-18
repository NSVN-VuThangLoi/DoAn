package healthcare.app.nurse;

import java.util.Date;

import lombok.Data;

@Data
public class InsertNurseCommand {
	private String nurseId;
	
	private String name;
	
	private Date birthDay;
	
	private String password;
	
	private String phoneNumber;
	
	private String email;
	
	private String position;
	
	private String addressWord;
	
	private Boolean sex;
	
}
