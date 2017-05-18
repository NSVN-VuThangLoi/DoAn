package healthcare.domain.doctor;

import java.util.Date;

import lombok.Data;
@Data
public class DoctorDto {
	
	private long version;
	
	private String doctorId;
	
	private String name;
	
	private Date birthDay;
	
	private String password;
	
	private String phoneNumber;
	
	private String email;
	
	private String position;
	
	private String addressWord;
	
	private Boolean sex;
	
	public DoctorDto(){
		
	}
public DoctorDto(long version,String doctorId,String name,Date birthDay,String phoneNumber,String email,String passWord,String position,String addressWord,Boolean sex){
		this.version = version;
		this.doctorId = doctorId;
		this.name = name;
		this.birthDay = birthDay;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = passWord;
		this.position = position;
		this.addressWord =addressWord;
		this.sex = sex;
	}
}

