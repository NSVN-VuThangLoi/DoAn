package healthcare.app.patient;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.patient.PatientDto;
import healthcare.domain.patient.PatientRepository;
import healthcare.domain.patient.PatientResultInsert;

@Stateless
public class InsertPatientCommandHandle {
	@Inject
	private PatientRepository patientRep;
	
	public PatientResultInsert handle(InsertPatientCommand command){
		PatientResultInsert result = new PatientResultInsert();
		PatientDto patientDto = new PatientDto();
		PatientDto dto = new PatientDto();
		boolean isInsert = true;
		patientDto = patientRep.getPatient(command.getUserId());
			if(patientDto != null){
				 isInsert = false;
				if(command.getPassword() == null){
					command.setPassword(patientDto.getPassword());
				}
				dto.setVersion(patientDto.getVersion());
			}
		
		if(isInsert){
			try {
				
				dto.setUserId(command.getUserId());
				dto.setBirthDay(command.getBirthDay());
				dto.setEmail(command.getEmail());
				dto.setName(command.getName());
				dto.setPassword(command.getPassword());
				dto.setAddress(command.getAddress());
				dto.setPhoneNumber(command.getPhoneNumber());
				dto.setSex(command.getSex());
				patientRep.insertPatient(dto);
				result.setResult("Đăng kí thành công DoctorId = " + command.getUserId());
				result.setUserId(command.getUserId());
			} catch (Exception e) {
				// TODO: handle exception
				result.setResult("Đăng kí thất bại DoctorId = " + command.getUserId());
				result.setUserId(command.getUserId());
			}
		}else{
			try {
				
				dto.setUserId(command.getUserId());
				dto.setBirthDay(command.getBirthDay());
				dto.setEmail(command.getEmail());
				dto.setName(command.getName());
				dto.setPassword(command.getPassword());
				dto.setAddress(command.getAddress());
				dto.setPhoneNumber(command.getPhoneNumber());
				dto.setSex(command.getSex());
				patientRep.insertPatient(dto);
				result.setResult("Update thành công DoctorId = " + command.getUserId());
				result.setUserId(command.getUserId());
			} catch (Exception e) {
				// TODO: handle exception
				result.setResult("Update thất bại DoctorId = " + command.getUserId());
				result.setUserId(command.getUserId());
			}
		}
		
		return result;
	}

}
