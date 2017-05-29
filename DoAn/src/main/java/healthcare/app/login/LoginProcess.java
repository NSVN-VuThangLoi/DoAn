package healthcare.app.login;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.app.common.UserLogin;
import healthcare.app.doctor.FinderDoctor;
import healthcare.app.nurse.FinderNurse;
import healthcare.app.patient.FinderPatient;
import healthcare.domain.doctor.DoctorDto;
import healthcare.domain.nurse.NurseDto;
import healthcare.domain.patient.PatientDto;

@Stateless
public class LoginProcess {
	@Inject
	private FinderDoctor finderDoctor;
	
	@Inject
	private FinderPatient finderPatient;
	
	@Inject
	private FinderNurse finderNurse;
	
	@Inject 
	private UserLogin userInfoLogin;
	
	//login doctor
	public LoginResult handle(LoginQuery query) {	
		LoginResult result = new LoginResult();
		DoctorDto dto = finderDoctor.getDoctor(query.getUserId());
		
		if(dto != null){
			if(dto.getPassword().equals(query.getPassword())){
				result.setNameNotice("Đăng nhập thành công");
				userInfoLogin.setDoctorId(dto.getDoctorId());
				userInfoLogin.setIsDoctor(true);
				userInfoLogin.setUserId(null);
				userInfoLogin.setIsPatient(false);
				userInfoLogin.setIsNurse(null);
				userInfoLogin.setIsNurse(false);
			}else{
				result.setNameNotice("Bạn nhập sai mật khẩu");
			}
		}else{
			result.setNameNotice("Bạn nhập sai mã bác sĩ:" + query.getUserId());
		}
		return result;
	}
	// login Patient
	public LoginResult loginPatient(LoginQuery query) {	
		LoginResult result = new LoginResult();
		PatientDto dto = finderPatient.getPatient(query.getUserId());
		
		if(dto != null){
			if(dto.getPassword().equals(query.getPassword())){
				result.setNameNotice("Đăng nhập thành công");
				userInfoLogin.setUserId(dto.getUserId());
				userInfoLogin.setIsPatient(true);
				userInfoLogin.setDoctorId(null);
				userInfoLogin.setIsDoctor(false);
				userInfoLogin.setNurseId(null);
				userInfoLogin.setIsNurse(false);
			}else{
				result.setNameNotice("Bạn nhập sai mật khẩu");
			}
		}else{
			result.setNameNotice("Bạn nhập sai mã bệnh nhân:" + query.getUserId());
		}
		return result;
	}
	// login Nurse
		public LoginResult loginNurse(LoginQuery query) {	
			LoginResult result = new LoginResult();
			NurseDto dto = finderNurse.getNurse(query.getUserId());
			
			if(dto != null){
				if(dto.getPassword().equals(query.getPassword())){
					result.setNameNotice("Đăng nhập thành công");
					userInfoLogin.setUserId(null);
					userInfoLogin.setIsPatient(true);
					userInfoLogin.setDoctorId(null);
					userInfoLogin.setIsDoctor(false);
					userInfoLogin.setNurseId(query.getUserId());
					userInfoLogin.setIsNurse(true);
				}else{
					result.setNameNotice("Bạn nhập sai mật khẩu");
				}
			}else{
				result.setNameNotice("Bạn nhập sai mã y tá:" + query.getUserId());
			}
			return result;
		}

}
