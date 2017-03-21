package healthcare.app.login;

import lombok.Data;

@Data
public class LoginResult {
	private String userName;
	
	private String nameNotice;
	
	private String userId;
	
	private String linkTopPage;
	
	private boolean status;

}
