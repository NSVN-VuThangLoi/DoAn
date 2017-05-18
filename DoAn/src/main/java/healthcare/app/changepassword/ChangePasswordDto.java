package healthcare.app.changepassword;

import lombok.Data;

@Data
public class ChangePasswordDto {
	
	private String passwordOld;
	
	private String passwordNew;
	
	private String passwordConfirm;
	
}
