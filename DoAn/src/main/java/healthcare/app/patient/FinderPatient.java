package healthcare.app.patient;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.patient.PatientDto;
import healthcare.domain.patient.PatientRepository;
@Stateless
public class FinderPatient {
	@Inject
	private PatientRepository patientRep;
	public List<PatientDto> getAllPatient(){
		return patientRep.getAllPatient();
	}
	public PatientDto getPatient(String userId){
		return patientRep.getPatient(userId);
	}

}
