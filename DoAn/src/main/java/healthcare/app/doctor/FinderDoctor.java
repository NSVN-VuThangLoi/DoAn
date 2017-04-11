package healthcare.app.doctor;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.doctor.DoctorDto;
import healthcare.domain.doctor.DoctorRepository;
@Stateless
public class FinderDoctor {
	@Inject
	private DoctorRepository doctorRep;
	public List<DoctorDto> getAllDoctor(){
		return doctorRep.getAllDoctor();
	}
	public DoctorDto getDoctor(String userId){
		return doctorRep.getDoctor(userId);
	}

}
