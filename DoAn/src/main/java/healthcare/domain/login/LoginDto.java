package healthcare.domain.login;

import java.util.Date;

import lombok.Data;

@Data
public class LoginDto {
	private long version;
	
	private String userId;
	
	private String name;
	
	private Date birthDay;
	
	private String Password;
	
	private int phoneNumber;
	
	private String email;
	
	private String address;
	
	private Boolean sex;
	
}
