package healthcare.domain.doctor;

import java.util.Date;

import lombok.Data;
@Data
public class DoctorDto {
	private String doctorId;
	
	private String name;
	
	private Date birthDay;
	
	private String password;
	
	private int phoneNumber;
	
	private String email;
	
	private String position;
	
	private String addressWord;
	
	private Boolean sex;
	
	public DoctorDto(){
		
	}
public DoctorDto(String doctorId,String name,Date birthDay,int phoneNumber,String email,String position,String addressWord,Boolean sex){
		this.doctorId = doctorId;
		this.name = name;
		this.birthDay = birthDay;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.position = position;
		this.addressWord =addressWord;
		this.sex = sex;
	}
}

