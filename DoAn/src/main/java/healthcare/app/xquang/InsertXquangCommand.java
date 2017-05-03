package healthcare.app.xquang;

import java.util.Date;

import lombok.Data;

@Data
public class InsertXquangCommand {
	private String userId;
	
	private String doctorId;
	
	private String age;
	
	private Date dayCare;
	
	private String diagnose;
	
	private String addressPatern;
	

}
