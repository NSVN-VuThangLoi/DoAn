package healthcare.app.common;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Data;


@SessionScoped
@Named("UserLogin")
@Data
public class UserLogin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	
	private String doctorId;
	
	private String nurseId;
	
	private Boolean isPatient = false;
	
	private Boolean isDoctor = false;
	
	private Boolean isNurse = false;
}
