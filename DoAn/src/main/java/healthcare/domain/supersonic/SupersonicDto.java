package healthcare.domain.supersonic;

import java.util.Date;

import lombok.Data;

@Data
public class SupersonicDto {
	
 private long version;
 
 private String supersonicId;
 
 private String userId;
 
 private String name;
 
 private String doctorId;
 
 private String age;
 
 private Date dayCare;
 
 private String diagnose;
 
 private String result;
 
 private String addressPatient;
 
 private String urlImage;
 
 private Boolean isImage;
 
 private Boolean isResult;
}
