package healthcare.app.doctor;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.doctor.DoctorDto;
import healthcare.domain.doctor.DoctorRepository;
import healthcare.domain.doctor.ResultInsert;

@Stateless
public class InsertDoctorCommandHandle {
	@Inject
	private DoctorRepository doctorRep;
	
	public ResultInsert handle(InsertDoctorCommand command){
		ResultInsert result = new ResultInsert();
		DoctorDto doctorDto = new DoctorDto();
		DoctorDto dto = new DoctorDto();
		boolean isInsert = true;
		doctorDto = doctorRep.getDoctor(command.getDoctorId());
			if(doctorDto != null){
				 isInsert = false;
				if(command.getPassword() == null){
					command.setPassword(doctorDto.getPassword());
				}
				dto.setVersion(doctorDto.getVersion());
			}
		
		if(isInsert){
			try {
				
				dto.setDoctorId(command.getDoctorId());
				dto.setBirthDay(command.getBirthDay());
				dto.setEmail(command.getEmail());
				dto.setName(command.getName());
				dto.setPassword(command.getPassword());
				dto.setAddressWord(command.getAddressWord());
				dto.setPhoneNumber(command.getPhoneNumber());
				dto.setPosition(command.getPosition());
				dto.setSex(command.getSex());
				doctorRep.insertDoctor(dto);
				result.setResult("Đăng kí thành công DoctorId = " + command.getDoctorId());
				result.setDoctorId(command.getDoctorId());
			} catch (Exception e) {
				// TODO: handle exception
				result.setResult("Đăng kí thất bại DoctorId = " + command.getDoctorId());
				result.setDoctorId(command.getDoctorId());
			}
		}else{
			try {
				
				dto.setDoctorId(command.getDoctorId());
				dto.setBirthDay(command.getBirthDay());
				dto.setEmail(command.getEmail());
				dto.setName(command.getName());
				dto.setPassword(command.getPassword());
				dto.setAddressWord(command.getAddressWord());
				dto.setPhoneNumber(command.getPhoneNumber());
				dto.setPosition(command.getPosition());
				dto.setSex(command.getSex());
				doctorRep.insertDoctor(dto);
				result.setResult("Update thành công DoctorId = " + command.getDoctorId());
				result.setDoctorId(command.getDoctorId());
			} catch (Exception e) {
				// TODO: handle exception
				result.setResult("Update thất bại DoctorId = " + command.getDoctorId());
				result.setDoctorId(command.getDoctorId());
			}
		}
		
		return result;
	}

}
