package healthcare.domain.nurse;

import java.util.Date;

import lombok.Data;
@Data
public class NurseDto {
	
	private long version;
	
	private String nurseId;
	
	private String name;
	
	private Date birthDay;
	
	private String password;
	
	private String phoneNumber;
	
	private String email;
	
	private String position;
	
	private String addressWord;
	
	private Boolean sex;
	
	public NurseDto(){
		
	}
public NurseDto(long version,String nurseId,String name,Date birthDay,String phoneNumber,String email,String passWord,String position,String addressWord,Boolean sex){
		this.version = version;
		this.nurseId = nurseId;
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

