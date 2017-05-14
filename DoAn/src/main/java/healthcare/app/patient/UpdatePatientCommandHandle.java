package healthcare.app.patient;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.app.common.UserLogin;
import healthcare.domain.patient.PatientDto;
import healthcare.domain.patient.PatientRepository;
import healthcare.domain.patient.PatientResultInsert;
@Stateless
public class UpdatePatientCommandHandle {
	@Inject
	private UserLogin userLogin;
	@Inject 
	private FinderPatient finder;
	@Inject 
	private PatientRepository patientRep;
	public PatientResultInsert handle(UpdatePatientCommand command){
		PatientResultInsert result = new PatientResultInsert();
		PatientDto dto = finder.getPatient(userLogin.getUserId());
		
		if(command.getPasswordOld().equals(dto.getPassword())){
			if(command.getPassword().equals(command.getPasswordComfirm())){
				dto.setPassword(command.getPassword());
				patientRep.updatePatient(dto);
				result.setResult("Thay đổi mật khẩu thành công");
			}else{
				result.setResult("Mật khẩu mới với mật khẩu nhập lại không giống nhau. VUi lòng nhập lại!");
			}
		}else{
			result.setResult("Bạn nhập sai mật khẩu cũ.Vui lòng nhập lại!");
		}
		return result;
	}
}
