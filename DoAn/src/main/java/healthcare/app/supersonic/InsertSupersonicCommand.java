package healthcare.app.supersonic;

import java.util.Date;

import lombok.Data;

@Data
public class InsertSupersonicCommand {
	private String userId;
	
	private String doctorId;
	
	private String age;
	
	private Date dayCare;
	
	private String diagnose;
	
	private String addressPatern;
	
	private String name;
	

}
