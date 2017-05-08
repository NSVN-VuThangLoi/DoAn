package healthcare.app.bloodtest;

import java.text.SimpleDateFormat;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.app.common.UserLogin;
import healthcare.domain.bloodtest.BloodTestDto;
import healthcare.domain.bloodtest.BloodTestRepository;
import healthcare.domain.bloodtest.ResultBloodTest;
@Stateless
public class InsertBloodTestCommandHandle {
	@Inject
	private UserLogin userLogin;
	@Inject
	private BloodTestRepository bloodTest;
	
	public ResultBloodTest handle(InsertBloodTestCommand command){
		ResultBloodTest result = new ResultBloodTest();
		BloodTestDto dto = new BloodTestDto();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyymmddhhmmss"); 
		
		dto.setBloodtestId(command.getUserId() + formatDate.format(command.getDayCare()));
		dto.setUserId(command.getUserId());
		dto.setDoctorId(userLogin.getDoctorId());
		dto.setAddress(command.getAddress());
		dto.setDiagnose(command.getDiagnose());
		dto.setGender(command.getGender());
		dto.setIsBloodTest(false);
		dto.setIsResult(false);
		dto.setDayCare(command.getDayCare());
		dto.setName(command.getName());
		result = bloodTest.Insert(dto);
		return result;
		
	}

}
