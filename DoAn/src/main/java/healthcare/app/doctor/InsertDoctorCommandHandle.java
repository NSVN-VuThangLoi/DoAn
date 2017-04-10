package healthcare.app.doctor;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.doctor.DoctorDto;
import healthcare.domain.doctor.DoctorRepository;

@Stateless
public class InsertDoctorCommandHandle {
	@Inject
	private DoctorRepository doctorRep;
	
	public void handle(InsertDoctorCommand command){
		DoctorDto dto = new DoctorDto();
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
	}

}
