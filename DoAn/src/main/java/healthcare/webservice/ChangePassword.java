package healthcare.webservice;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import healthcare.app.changepassword.ChangePasswordDto;
import healthcare.app.common.UserLogin;
import healthcare.app.doctor.FinderDoctor;
import healthcare.app.nurse.FinderNurse;
import healthcare.app.patient.FinderPatient;
import healthcare.domain.bloodtest.ResultBloodTest;
import healthcare.domain.doctor.DoctorDto;
import healthcare.domain.doctor.DoctorRepository;
import healthcare.domain.nurse.NurseDto;
import healthcare.domain.nurse.NurseRepository;
import healthcare.domain.patient.PatientDto;
import healthcare.domain.patient.PatientRepository;

@Path("/changepassword")
public class ChangePassword{
	@Inject
		private UserLogin userLogin;
	@Inject
		private FinderDoctor finderDoctor;
	@Inject
		private FinderPatient finderPatient;
	@Inject
		private FinderNurse finderNurse;
	@Inject
		private DoctorRepository doctorRep;
	@Inject 
		private PatientRepository patientRep; 
	@Inject
		private NurseRepository nurseRep; 
	
	@POST
	@Path("/change")
	public ResultBloodTest changePassword(ChangePasswordDto changePasswordDto){
		ResultBloodTest result = new ResultBloodTest();
		if(userLogin.getIsDoctor() || userLogin.getIsNurse() || userLogin.getIsPatient()){
			// check password Old with password new
			if(changePasswordDto.getPasswordOld().equals(changePasswordDto.getPasswordNew())){
				  result.setResult("PW0002");
				  return result;
			}
			//check password confirm with password new
			if(!changePasswordDto.getPasswordConfirm().equals(changePasswordDto.getPasswordNew())){
				result.setResult("PW0003");
				return result;
			}
			// change password with doctor
			if(userLogin.getIsDoctor()){
				DoctorDto dto = finderDoctor.getDoctor(userLogin.getDoctorId());
				if(!changePasswordDto.getPasswordOld().equals(dto.getPassword())){
					result.setResult("PW0001"); // PW0001 là lỗi password cũ vừa nhập không trùng password lưu trong db hiện tại
					return result;
				}
				dto.setPassword(changePasswordDto.getPasswordNew());
				doctorRep.updateDoctor(dto);
				result.setResult("PW1001"); // PW1001 là mã thay đổi password thành công.
			}
			// change password with patient
			if(userLogin.getIsPatient()){
				PatientDto dto = finderPatient.getPatient(userLogin.getUserId());
				if(!changePasswordDto.getPasswordOld().equals(dto.getPassword())){
					result.setResult("PW0001");
					return result;
				}
				dto.setPassword(changePasswordDto.getPasswordNew());
				patientRep.updatePatient(dto);
				result.setResult("PW1001"); 
			}
			//change password with nurse
			if(userLogin.getIsNurse()){
				NurseDto dto = finderNurse.getNurse(userLogin.getNurseId());
				if(!changePasswordDto.getPasswordOld().equals(dto.getPassword())){
					result.setResult("PW0001");
					return result;
				}
				dto.setPassword(changePasswordDto.getPasswordNew());
				nurseRep.updateNurse(dto);
				result.setResult("PW1001");
			}
		}else{
			result.setResult("PW1111");// bạn chưa đăng nhập
		}
		
		return result;
	}

}
