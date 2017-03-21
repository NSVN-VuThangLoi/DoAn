package healthcare.app.login;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginQuery {
	private String userId;
	
	private String name;
	
	private Date birthDay;
	
	private String Password;
	
	private int phoneNumber;
	
	private String email;
	
	private String address;
	
	private Boolean sex;

}
