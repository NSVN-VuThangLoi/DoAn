package healthcare.app.login;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.app.common.UserLogin;
import healthcare.app.doctor.FinderDoctor;
import healthcare.domain.doctor.DoctorDto;

@Stateless
public class LoginProcess {
	@Inject
	private FinderDoctor finderDoctor;
	
	@Inject 
	private UserLogin userInfoLogin;
	public LoginResult handle(LoginQuery query) {	
		LoginResult result = new LoginResult();
		DoctorDto dto = finderDoctor.getDoctor(query.getUserId());
		
		if(dto != null){
			if(dto.getPassword().equals(query.getPassword())){
				result.setNameNotice("Đăng nhập thành công");
				userInfoLogin.setUserId(dto.getDoctorId());
			}else{
				result.setNameNotice("Bạn nhập sai mật khẩu");
			}
		}else{
			result.setNameNotice("Bạn nhập sai:" + query.getUserId());
		}
		return result;
	}

}
