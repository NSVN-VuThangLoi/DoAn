package healthcare.domain.xquang;

import java.util.Date;

import lombok.Data;

@Data
public class XquangDto {
	
 private long version;
 
 private String userId;
 
 private String doctorId;
 
 private String age;
 
 private Date dayCare;
 
 private String diagnose;
 
 private String result;
 
 private String addressPatient;
 
 private String urlImage;
 
 private Boolean isImage;
 
}
