package healthcare.app.patient;

import lombok.Data;

@Data
public class UpdatePatientCommand {
	
	private String passwordOld;
	private String password;
	private String passwordComfirm;
}
