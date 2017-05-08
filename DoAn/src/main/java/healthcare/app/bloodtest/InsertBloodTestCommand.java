package healthcare.app.bloodtest;

import java.util.Date;

import lombok.Data;

@Data
public class InsertBloodTestCommand {
	
	private String userId;
	
	private Date dayCare;
	
	private Boolean gender;
	
	private String name;
	
	private String address;
	
	private String diagnose;
	
}
