package healthcare.domain.patient;

import java.util.Date;

import lombok.Data;
@Data
public class PatientDto {
	
	private long version;
	
	private String userId;
	
	private String name;
	
	private Date birthDay;
	
	private String password;
	
	private int phoneNumber;
	
	private String email;
	
	private String address;
	
	private Boolean sex;
	
	public PatientDto(){
		
	}
public PatientDto(long version,String userId,String name,Date birthDay,int phoneNumber,String email,String address,Boolean sex,String password){
		this.version = version;
		this.userId = userId;
		this.name = name;
		this.birthDay = birthDay;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.sex = sex;
		this.password = password;
	}
}

